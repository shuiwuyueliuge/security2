package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;

public abstract class InMemoryValidateCodeGenerator implements ValidateCodeGenerator {

	private ValidateCodeManager manager;

	private String loginUri;

	private String ValidateCodeType;

	public InMemoryValidateCodeGenerator(ValidateCodeManager manager, String loginUri, String ValidateCodeType) {
		this.manager = manager;
		this.loginUri = loginUri;
		this.ValidateCodeType = ValidateCodeType;
	}

	@Override
	public String generateAndSend(String key, HttpServletRequest request, HttpServletResponse response)
			throws SendCodeException {
		String code = generate(key, request);
		String result = send(key, code, response);
		manager.save(key, code);
		return result;
	}

	@Override
	public boolean check(String key, String value) {
		String cached = manager.get(key);
		manager.remove(key);
		return value.equals(cached) ? true : false;
	}

	protected abstract String send(String key, String code, HttpServletResponse response) throws SendCodeException;

	protected abstract String generate(String key, HttpServletRequest request);
	
	public String getLoginUri() {
		return loginUri;
	}

	public String getValidateCodeType() {
		return ValidateCodeType;
	}
}
