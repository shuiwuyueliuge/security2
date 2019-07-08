package com.security.core.social.github;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class GitHubOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<GitHub> {
	
    private static final String AUTHORIZE_URL = "https://github.com/login/oauth/authorize";
	
	private static final String TOKEN_URL = "https://github.com/login/oauth/access_token";

	public GitHubOAuth2ServiceProvider(String clientId, String clientSecret) {
		super(new GitHubOAuth2Template(clientId, clientSecret, AUTHORIZE_URL, TOKEN_URL));
	}

	@Override
	public GitHub getApi(String accessToken) {
		return new GitHubOAuth2ApiBinding(accessToken);
	}
}
