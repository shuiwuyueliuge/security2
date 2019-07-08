package com.security.core.social.github;

import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;

public class GitHubOAuth2Template extends OAuth2Template {
	
	private String authorizeUrl;
	
	private String clientId;

	public GitHubOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		this.authorizeUrl = authorizeUrl;
		this.clientId = clientId;
	}
	
	@Override
	public String buildAuthenticateUrl(OAuth2Parameters parameters) {
		return authorizeUrl + "?client_id=" + clientId +  "&scope=user:email";
	}
}