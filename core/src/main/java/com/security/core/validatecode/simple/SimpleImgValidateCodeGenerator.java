package com.security.core.validatecode.simple;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.security.core.exception.SendCodeException;
import com.security.core.util.ImgCodeUtil;
import com.security.core.util.RandomUtil;
import com.security.core.validatecode.InMemoryValidateCodeGenerator;
import com.security.core.validatecode.ValidateCodeManager;

public class SimpleImgValidateCodeGenerator extends InMemoryValidateCodeGenerator {

	public SimpleImgValidateCodeGenerator(ValidateCodeManager manager, String uri, String ValidateCodeType) {
		super(manager, uri, ValidateCodeType);
	}

	@Override
	protected String send(String key, String code, HttpServletResponse response) throws SendCodeException {
		try {
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setIntHeader("Expires", -1);
			ImgCodeUtil.write(code, response.getOutputStream());
		} catch (Exception e) {
			throw new SendCodeException(e);
		}
		
		return "send img code success";
	}

	@Override
	protected String generate(String key, HttpServletRequest request) {
		return RandomUtil.validateCoed(4);
	}
}
