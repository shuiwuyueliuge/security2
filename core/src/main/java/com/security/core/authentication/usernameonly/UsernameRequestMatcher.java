package com.security.core.authentication.usernameonly;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import lombok.Data;

@Data
public class UsernameRequestMatcher implements RequestMatcher{

	private RequestMatcher matcher;
	
	private String username;
	
	public UsernameRequestMatcher(String username, String pattern) {
		this.matcher = new AntPathRequestMatcher(pattern, "POST");
		this.username = username;
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		return matcher.matches(request);
	}
}
