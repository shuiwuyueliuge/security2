package org.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.security.core.exception.SendCodeException;
import com.security.core.validatecode.LocalCacheValidateCodeGenerator;

public class A extends LocalCacheValidateCodeGenerator {

	public A(String validateCodeType) {
		super(validateCodeType);
	}

	@Override
	protected String send(String key, String code, HttpServletResponse response) throws SendCodeException {
		return "send success";
	}

	@Override
	protected String generate(String key, HttpServletRequest request) {
		return "1234";
	}
}
