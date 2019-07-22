package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;

public abstract class DefaultValidateCodeGenerator implements ValidateCodeGenerator {
	
	protected String validateCodeType;
	
	protected ValidateCodeManager manager;
	
	public DefaultValidateCodeGenerator(String validateCodeType, ValidateCodeManager manager) {
		this.validateCodeType = validateCodeType;
		this.manager = manager;
	}

	@Override
	public String generateAndSend(final String key, HttpServletRequest request, HttpServletResponse response) throws SendCodeException {
		String code = generate(key, request);
		String result =  send(key, code, response);
		saveCode(key, code);
		return result;
	}
	
	@Override
	public String getCodeType() {
		return validateCodeType;
	}
	
	private void saveCode(String key, String code) {
		if (manager != null) {
			manager.save(key, code);
		}
	}
	
	protected abstract String send(String key, String code, HttpServletResponse response) throws SendCodeException;
	
	protected abstract String generate(String key, HttpServletRequest request);
}
