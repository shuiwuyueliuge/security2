package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;

public abstract class DefaultValidateCodeGenerator implements ValidateCodeGenerator {
	
	//private String loginUri;
	
	//private String username;
	
	private String validateCodeType;
	
	private boolean usernameOnly;
	
	public DefaultValidateCodeGenerator(String loginUri, String username, String validateCodeType) {
		//this.loginUri = loginUri;
		//this.username = username;
		this.validateCodeType = validateCodeType;
		this.usernameOnly = true;
	}
	
	public DefaultValidateCodeGenerator(String loginUri, String validateCodeType) {
		//this.loginUri = loginUri;
		this.validateCodeType = validateCodeType;
		this.usernameOnly = false;
	}

	@Override
	public String generateAndSend(String key, HttpServletRequest request, HttpServletResponse response) throws SendCodeException {
		String code = generate(key, request);
		String result = send(key, code, response);
		return result;
	}
	
//	@Override
//	public boolean check(String key, String value) {
//		String cached = getCached(key);
//		return value.equals(cached) ? true : false;
//	}
	
	protected abstract String send(String key, String code, HttpServletResponse response) throws SendCodeException;
	
	protected abstract String generate(String key, HttpServletRequest request);
	
	//protected abstract String getCached(String key);

	//public String getLoginUri() {
		//return loginUri;
	//}

	//public String getUsername() {
		//return username;
	//}
	
	public String getCodeType() {
		return validateCodeType;
	}
	
	@Override
	public boolean isUsernameOnly() {
		return usernameOnly;
	}
}
