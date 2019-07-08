package com.security.core.social.qq;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String clientId, String clientSecret) {
		super(providerId, new QQOAuth2ServiceProvider(clientId, clientSecret), new QQApiAdapter());
	}
}
