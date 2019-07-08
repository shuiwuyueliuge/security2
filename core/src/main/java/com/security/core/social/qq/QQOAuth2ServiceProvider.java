package com.security.core.social.qq;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class QQOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
	
	private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";
	
	private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

	public QQOAuth2ServiceProvider(String clientId, String clientSecret) {
		super(new QQOAuth2Template(clientId, clientSecret, AUTHORIZE_URL, TOKEN_URL));
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQOAuth2ApiBinding(accessToken);
	}
}
