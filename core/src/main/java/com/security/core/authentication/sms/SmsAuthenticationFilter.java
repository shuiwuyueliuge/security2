package com.security.core.authentication.sms;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String SMS_KEY = "key";

	private String mobile = SMS_KEY;
	
	private Set<RequestMatcher> requiresAuthenticationRequestMatchers;
	
	private boolean postOnly = true;

	public SmsAuthenticationFilter(Set<String> loginUris) {
		super("");
		this.requiresAuthenticationRequestMatchers = new HashSet<RequestMatcher>();
		for (String string : loginUris) {
			this.requiresAuthenticationRequestMatchers.add(new AntPathRequestMatcher(string, "POST"));
		}
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);
		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();
		SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		for (RequestMatcher requestMatcher : requiresAuthenticationRequestMatchers) {
			if (requestMatcher.matches(request)) {
				return true;
			}
		}
		
		return false;
	}

	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobile);
	}

	protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setMobile(String mobile) {
		Assert.hasText(mobile, "mobile parameter must not be empty or null");
		this.mobile = mobile;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobile() {
		return mobile;
	}
}
