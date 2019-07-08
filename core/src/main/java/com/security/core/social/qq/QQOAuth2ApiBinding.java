package com.security.core.social.qq;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

public class QQOAuth2ApiBinding extends AbstractOAuth2ApiBinding implements QQ {
	
	private String token;
	
	private static final String OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";
	
	protected QQOAuth2ApiBinding(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.token = accessToken;
	}

	@Override
	public QQUserInfo getUserInfo() throws Exception {
		String result = this.getRestTemplate().getForObject(String.format(USER_INFO_URL, token, "101575852", getOpenId()), String.class);
		return new ObjectMapper().readValue(result, QQUserInfo.class);
	}

	@Override
	public String getOpenId() throws Exception {
		String result = this.getRestTemplate().getForObject(String.format(OPEN_ID_URL, token), String.class);
		result = result.replace("callback( ", "").replace(" );", "");
		return new ObjectMapper().readValue(result, OpenId.class).openid;
	}
	
	@Data
	protected static class OpenId {
		
		private String client_id;
		private String openid;
	}
}
