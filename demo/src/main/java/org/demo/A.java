package org.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;
import com.security.core.validatecode.InMemoryValidateCodeGenerator;

public class A extends InMemoryValidateCodeGenerator {

	public A(String loginUri, String username, String type) {
		super(loginUri, username, type);
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
