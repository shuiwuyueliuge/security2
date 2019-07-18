package com.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer.ConcurrencyControlConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import com.security.core.config.LoginProperties;
import com.security.core.config.LogoutProperties;
import com.security.core.config.RememberMeProperties;
import com.security.core.config.SessionProperties;
import com.security.core.expand.UsernameOnlyConfigurerAdapter;
import com.security.core.expand.ValidateConfigurerAdapter;
import com.security.core.request.RequestManager;

@EnableWebSecurity
public class BrowserSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RequestManager requestManager;
	
	@Autowired(required = false)
	private LoginProperties loginProperties;
	
	@Autowired(required = false)
	private LogoutProperties logoutProperties;
	
	@Autowired(required = false)
	private RememberMeProperties rememberMeProperties;
	
	@Autowired(required = false)
	private PersistentTokenRepository persistentTokenRepository;
	
	@Autowired
	private SessionProperties sessionProperties;
	
	@Autowired(required = false)
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired(required = false)
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired(required = false)
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired(required = false)
	private SessionInformationExpiredStrategy expiredStrategy;
	
	@Autowired(required = false)
	private InvalidSessionStrategy invalidSessionStrategy;
	
	@Autowired(required = false)
	private ValidateConfigurerAdapter validateConfigurerAdapter;
	
	@Autowired(required = false)
	private UsernameOnlyConfigurerAdapter smsConfigurerAdapter;
	
	@Autowired(required = false)
	private SpringSocialConfigurer socialConfigurer;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		requestManager.config(http.authorizeRequests());
		http.csrf().disable();
		loginConfigure(http.formLogin());
		logoutConfigure(http.logout());
		sessionConfigure(http.sessionManagement());
		rememberMeConfigure(http);
		if (validateConfigurerAdapter != null) {
			http.apply(validateConfigurerAdapter);
		}
		
		if (smsConfigurerAdapter != null) {
			http.apply(smsConfigurerAdapter);
		}
		
		if (socialConfigurer != null) {
			http.apply(socialConfigurer);
		}
	}
	
	private void rememberMeConfigure(HttpSecurity http) throws Exception {
		if (rememberMeProperties != null) {
			http.rememberMe()
			    .tokenRepository(persistentTokenRepository)
			    .tokenValiditySeconds(rememberMeProperties.getTokenValiditySeconds());             
		}
	}

	private void sessionConfigure(SessionManagementConfigurer<HttpSecurity> configurer) {
		@SuppressWarnings("rawtypes")
		ConcurrencyControlConfigurer controlConfigurer = configurer.maximumSessions(sessionProperties.getMaximum()).maxSessionsPreventsLogin(sessionProperties.isMaxSessionsPreventsLogin());
	    if (expiredStrategy != null) {//并发登陆
	    	controlConfigurer.expiredSessionStrategy(expiredStrategy);
	    }
	    
	    if (invalidSessionStrategy != null) {//无效session
	    	configurer.invalidSessionStrategy(invalidSessionStrategy);
	    }
	}

	private void logoutConfigure(LogoutConfigurer<HttpSecurity> configurer) {
		if (logoutProperties != null) {
			configurer.logoutUrl(logoutProperties.getUrl());
		}
		
		if (logoutSuccessHandler != null) {
			configurer.logoutSuccessHandler(logoutSuccessHandler);
		}
	}

	private void loginConfigure(FormLoginConfigurer<HttpSecurity> configurer) {
		if (loginProperties != null) {
			configurer.loginPage(loginProperties.getPage())
	                  .loginProcessingUrl(loginProperties.getProcessingUrl())
	                  .usernameParameter(loginProperties.getUsernameParameter())
	                  .passwordParameter(loginProperties.getPasswordParameter());
		}
		
		if (authenticationSuccessHandler != null) {
			configurer.successHandler(authenticationSuccessHandler);
		}
		
		if (authenticationFailureHandler != null) {
			configurer.failureHandler(authenticationFailureHandler);
		}
	}
}
