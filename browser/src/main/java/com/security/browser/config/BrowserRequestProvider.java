package com.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import com.security.core.autoconfig.LoginProperties;
import com.security.core.autoconfig.LogoutProperties;
import com.security.core.request.RequestProvider;

public class BrowserRequestProvider implements RequestProvider {
	
	@Autowired(required = false)
	private LoginProperties loginProperties;
	
	@Autowired(required = false)
	private LogoutProperties logoutProperties; 

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		if (loginProperties != null) {
			registry.antMatchers(loginProperties.getPage(), loginProperties.getProcessingUrl()).permitAll();
		}
		
		if (logoutProperties != null) {
			registry.antMatchers(logoutProperties.getUrl()).permitAll();
		}
	}
}
