package com.security.core.social.qq;

import java.nio.charset.Charset;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class QQOAuth2Template extends OAuth2Template {

	public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		this.setUseParametersForClientAuthentication(true);
	}
	
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		accessTokenUrl = accessTokenUrl + "?client_id=" + parameters.getFirst("client_id") + "&grant_type=authorization_code&redirect_uri=" + parameters.getFirst("redirect_uri") + "&client_secret=" + parameters.getFirst("client_secret") + "&code=" + parameters.getFirst("code");
		ResponseEntity<String> result = getRestTemplate().getForEntity(accessTokenUrl, String.class);
		String[] data = result.getBody().split("&");
		return new AccessGrant(data[0].split("=")[1], null, data[2].split("=")[1], Long.valueOf(data[1].split("=")[1]));
	}
	
	@Override
	protected RestTemplate getRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
	}
}