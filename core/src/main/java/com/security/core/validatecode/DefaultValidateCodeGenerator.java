package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;

public abstract class DefaultValidateCodeGenerator implements ValidateCodeGenerator {
	
	private String loginUri;
	
	private String ValidateCodeType;
	
	public DefaultValidateCodeGenerator(String loginUri, String ValidateCodeType) {
		this.loginUri = loginUri;
		this.ValidateCodeType = ValidateCodeType;
	}

	@Override
	public String generateAndSend(String key, HttpServletRequest request, HttpServletResponse response) throws SendCodeException {
		String code = generate(key, request);
		String result = send(key, code, response);
		return result;
	}
	
	@Override
	public boolean check(String key, String value) {
		String cached = getCached(key);
		return value.equals(cached) ? true : false;
	}
	
	protected abstract String send(String key, String code, HttpServletResponse response) throws SendCodeException;
	
	protected abstract String generate(String key, HttpServletRequest request);
	
	protected abstract String getCached(String key);

	public String getLoginUri() {
		return loginUri;
	}

	public String getValidateCodeType() {
		return ValidateCodeType;
	}
}
