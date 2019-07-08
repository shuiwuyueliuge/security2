package com.security.core.social.qq;

import lombok.Data;

@Data
public class QQToken {

	private String access_token;
	
	private String expires_in;
	
	private String refresh_token;
	
}
