package com.securityframework.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import com.security.core.config.LoginProperties;
import com.security.core.config.LogoutProperties;
import com.security.core.config.SmsProperties;
import com.security.core.request.RequestProvider;

public class BrowserRequestProvider implements RequestProvider {
	
	@Autowired(required = false)
	private LoginProperties loginProperties;
	
	@Autowired(required = false)
	private LogoutProperties logoutProperties;
	
	@Autowired(required = false)
	private SmsProperties smsProperties;

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		if (loginProperties != null) {
			registry.antMatchers(loginProperties.getPage(), loginProperties.getProcessingUrl()).permitAll();
		}
		
		if (logoutProperties != null) {
			registry.antMatchers(logoutProperties.getUrl()).permitAll();
		}
		
		if (smsProperties != null) {
			registry.antMatchers(smsProperties.getProcessingUrl()).permitAll();
		} else {
			registry.antMatchers("/login/sms").permitAll();
		}
		
		registry.antMatchers("/connect/**").permitAll();
	}
}
