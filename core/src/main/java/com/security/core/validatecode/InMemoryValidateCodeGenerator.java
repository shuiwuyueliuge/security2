package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;
import com.security.core.validatecode.simple.SimpleValidateCodeManager;

public abstract class InMemoryValidateCodeGenerator implements ValidateCodeGenerator {

	private ValidateCodeManager manager;

	private String loginUri;
	
	private String username;

	private String validateCodeType;
	
	private boolean usernameOnly;

	public InMemoryValidateCodeGenerator(ValidateCodeManager manager, String username, String loginUri, String validateCodeType) {
		this.manager = manager;
		this.loginUri = loginUri;
		this.username = username;
		this.validateCodeType = validateCodeType;
		usernameOnly = true;
	}
	
	public InMemoryValidateCodeGenerator(ValidateCodeManager manager, String loginUri, String validateCodeType) {
		this.manager = manager;
		this.loginUri = loginUri;
		this.validateCodeType = validateCodeType;
		usernameOnly = false;
	}
	
	public InMemoryValidateCodeGenerator(String loginUri, String validateCodeType) {
		this.manager = new SimpleValidateCodeManager();
		this.loginUri = loginUri;
		usernameOnly = false;
		this.validateCodeType = validateCodeType;
	}
	
	public InMemoryValidateCodeGenerator(String loginUri, String username, String validateCodeType) {
		this.manager = new SimpleValidateCodeManager();
		this.loginUri = loginUri;
		this.username = username;
		this.validateCodeType = validateCodeType;
		usernameOnly = true;
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

	public String getUsername() {
		return username;
	}
	
	public String getCodeType() {
		return validateCodeType;
	}
	
	@Override
	public boolean isUsernameOnly() {
		return usernameOnly;
	}
}
