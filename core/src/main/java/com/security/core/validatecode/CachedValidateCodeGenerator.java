package com.security.core.validatecode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;

public abstract class CachedValidateCodeGenerator implements ValidateCodeGenerator {
	
	private ValidateCodeManager manager;
	
	public CachedValidateCodeGenerator(ValidateCodeManager manager) {
		this.manager = manager;
	}
	
	@Override
	public Object generateAndSend(String key, HttpServletRequest request, HttpServletResponse response) throws SendCodeException {
		String code = generate(key, request);
		Object result = send(key, code, response);
		manager.save(key, code);
		return result;
	}
	
	@Override
	public boolean check(String key, String value) {
		String cached = manager.get(key);
		manager.remove(key);
		return value.equals(cached) ? true : false;
	}
	
	protected abstract Object send(String key, String code, HttpServletResponse response) throws SendCodeException;
	
	protected abstract String generate(String key, HttpServletRequest request);
}
