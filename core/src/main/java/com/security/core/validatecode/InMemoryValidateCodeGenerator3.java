package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;
import com.security.core.validatecode.simple.SimpleValidateCodeManager;

public abstract class InMemoryValidateCodeGenerator3 extends DefaultValidateCodeGenerator {
	
	private ValidateCodeManager manager;

	public InMemoryValidateCodeGenerator3(ValidateCodeManager manager, String loginUri, String username, String validateCodeType) {
		super(loginUri, username, validateCodeType);
		this.manager = manager;
	}

	public InMemoryValidateCodeGenerator3(ValidateCodeManager manager, String loginUri, String validateCodeType) {
		super(loginUri, validateCodeType);
		this.manager = manager;
	}
	
	public InMemoryValidateCodeGenerator3(String loginUri, String username, String validateCodeType) {
		super(loginUri, username, validateCodeType);
		this.manager = SimpleValidateCodeManager.getInstance();
	}

	public InMemoryValidateCodeGenerator3(String loginUri, String validateCodeType) {
		super(loginUri, validateCodeType);
		this.manager = SimpleValidateCodeManager.getInstance();
	}
	
	@Override
	public String generateAndSend(String key, HttpServletRequest request, HttpServletResponse response)
			throws SendCodeException {
		String code = super.generateAndSend(key, request, response);
		manager.save(key, code);
		return code;
	}
	
//	@Override
//	public boolean check(String key, String value) {
//		String cached = getCached(key);
//		return value.equals(cached);
//	}
//	
//	@Override
//	protected String getCached(String key) {
//		return manager.remove(key);
//	}
}
