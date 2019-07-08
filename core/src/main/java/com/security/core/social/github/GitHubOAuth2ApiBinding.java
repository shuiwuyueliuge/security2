package com.security.core.social.github;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHubOAuth2ApiBinding extends AbstractOAuth2ApiBinding implements GitHub {
	
	private String token;
	
	private static final String USER_INFO_URL = "https://api.github.com/user?access_token=%s"; 
	
	protected GitHubOAuth2ApiBinding(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
	}

	@Override
	public GitHubUserInfo getUserInfo() throws Exception {
		String result = this.getRestTemplate().getForObject(String.format(USER_INFO_URL, token), String.class);
		return new ObjectMapper().readValue(result, GitHubUserInfo.class);
	}
}
