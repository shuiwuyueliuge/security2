package com.security.core.authentication.social;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;

public class SocialAuthenticationProvider implements AuthenticationProvider {

	private SocialUserDetailsService userDetailsService;
	
	private UsersConnectionRepository repository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SocialAuthenticationToken authenticationToken = (SocialAuthenticationToken) authentication;
		Set<String> set = new HashSet<String>();
		set.add((String) authenticationToken.getCredentials());
		//userId
		Set<String> uIds = getRepository().findUserIdsConnectedTo((String) authenticationToken.getPrincipal(), set);
		if (uIds == null || uIds.isEmpty()) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}

		UserDetails user = userDetailsService.loadUserByUserId((String) uIds.toArray()[0]);
		SocialAuthenticationToken authenticationResult = new SocialAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SocialAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public SocialUserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(SocialUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public UsersConnectionRepository getRepository() {
		return repository;
	}

	public void setRepository(UsersConnectionRepository repository) {
		this.repository = repository;
	}
}