package org.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.security.core.exception.SendCodeException;
import com.security.core.validatecode.InMemoryValidateCodeGenerator;
import com.security.core.validatecode.ValidateCodeManager;

public class A extends InMemoryValidateCodeGenerator {

	public A(ValidateCodeManager manager, String loginUri, String ValidateCodeType) {
		super(manager, loginUri, ValidateCodeType);
	}

	@Override
	protected String send(String key, String code, HttpServletResponse response) throws SendCodeException {
		System.out.println("key [" + key + "] code [" + code + "] sending...");
		return "发送成功";
	}

	@Override
	protected String generate(String key, HttpServletRequest request) {
		return "1234";
	}
}
