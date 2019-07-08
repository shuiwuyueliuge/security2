package com.security.core.social.github;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class GitHubConnectionFactory extends OAuth2ConnectionFactory<GitHub> {

	public GitHubConnectionFactory(String providerId, String clientId, String clientSecret) {
		super(providerId, new GitHubOAuth2ServiceProvider(clientId, clientSecret), new GitHubApiAdapter());
	}
}
