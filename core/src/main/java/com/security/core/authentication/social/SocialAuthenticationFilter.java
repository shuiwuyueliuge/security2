package com.security.core.authentication.social;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

public class SocialAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String PROVIDER_ID = "provider_id";
	
	public static final String PROVIDER_USER_ID = "provider_user_id";
	
	private String providerId = PROVIDER_ID;
	private String providerUserId = PROVIDER_USER_ID;
	private boolean postOnly = true;

	public SocialAuthenticationFilter() {
		super(new AntPathRequestMatcher("/social", "POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String providerId = obtainProviderId(request);
		String providerUserId = obtainProviderUserId(request);
		
		if (providerId == null) {
			providerId = "";
		}
		
		if (providerUserId == null) {
			providerUserId = "";
		}

		providerId = providerId.trim();
		providerUserId = providerUserId.trim();
		SocialAuthenticationToken authRequest = new SocialAuthenticationToken(providerId, providerUserId);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainProviderId(HttpServletRequest request) {
		return request.getParameter(providerId);
	}
	
	protected String obtainProviderUserId(HttpServletRequest request) {
		return request.getParameter(providerUserId);
	}

	protected void setDetails(HttpServletRequest request, SocialAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setProviderId(String providerId) {
		Assert.hasText(providerId, "providerId parameter must not be empty or null");
		this.providerId = providerId;
	}
	
	public void setProviderUserId(String providerUserId) {
		Assert.hasText(providerUserId, "providerUserId parameter must not be empty or null");
		this.providerUserId = providerUserId;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getProviderId() {
		return providerId;
	}
	
	public final String getProviderUserId() {
		return providerUserId;
	}
}
