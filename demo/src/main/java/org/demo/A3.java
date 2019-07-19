package org.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.security.core.exception.SendCodeException;
import com.security.core.validatecode.InMemoryValidateCodeGenerator3;
import com.security.core.validatecode.ValidateCodeManager;

public class A3 extends InMemoryValidateCodeGenerator3 {

	public A3(ValidateCodeManager manager, String loginUri, String username, String validateCodeType) {
		super(manager, loginUri, username, validateCodeType);
	}

	@Override
	protected String send(String key, String code, HttpServletResponse response) throws SendCodeException {
		return null;
	}

	@Override
	protected String generate(String key, HttpServletRequest request) {
		return null;
	}
}
