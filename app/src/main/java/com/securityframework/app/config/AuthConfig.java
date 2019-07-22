package com.securityframework.app.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired(required = false)
	private TokenStore store;
	
	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Autowired(required = false)
	@Qualifier("enhancer")
	private TokenEnhancer tokenEnhancer;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("aaa")
		                  .secret(new BCryptPasswordEncoder().encode("bbb"))
				          .authorizedGrantTypes("test1")
				          .scopes("all");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		// 允许表单认证
		oauthServer.allowFormAuthenticationForClients();
	}
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    	if (store != null && jwtAccessTokenConverter != null && tokenEnhancer != null) {
    		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList<TokenEnhancer>();
            enhancerList.add(tokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancerList);
            
            endpoints.accessTokenConverter(jwtAccessTokenConverter)
                     .tokenStore(store)
                     .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                     .tokenEnhancer(enhancerChain);
    	}
    }
}