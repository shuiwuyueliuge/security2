package com.securityframework.app.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.security.core.request.RequestManager;

@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

	//@Autowired
	//private RequestManager requestManager;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				try {
					response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
					response.getWriter().write("{\"errno\":\"1001\",\"msg\":\"you must login\"}");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}}).accessDeniedHandler(new AccessDeniedHandler() {

			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				System.out.println(111);
			}
			
		});
		
		//requestManager.config(http.authorizeRequests());
	}
}