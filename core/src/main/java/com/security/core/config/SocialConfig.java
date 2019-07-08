package com.security.core.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.View;
import com.security.core.expand.SocialConfigAdapter;
import com.security.core.social.controller.SocialBindingController;
import com.security.core.social.controller.SocialConnectController;

public class SocialConfig {

	@Bean
	public SpringSocialConfigurer socialSecurityConfig() {
		SocialConfigAdapter springSocialConfigurer = new SocialConfigAdapter((request, response, authentication) -> {
			System.out.println(123);
		});
		//springSocialConfigurer.signupUrl("");
		return springSocialConfigurer;
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator,
			UsersConnectionRepository connectionRepository) {
		return new ProviderSignInUtils(factoryLocator, connectionRepository);
	}

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
	
	@Bean({ "connect/qqConnected", "connect/qqConnect" })
	@ConditionalOnMissingBean(name = "qqBindingView")
	public View qqBindingView() {
		return new SocialBindingController();
	}
	
	@Bean({ "connect/githubConnected", "connect/githubConnect" })
	@ConditionalOnMissingBean(name = "githubBindingView")
	public View githubBindingView() {
		return new SocialBindingController();
	}
	
	@Bean("connect/status")
	public View SocialConnectView() {
		return new SocialConnectController();
	}
}