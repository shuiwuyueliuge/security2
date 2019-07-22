package com.security.core.validatecode;

import com.security.core.validatecode.simple.SimpleValidateCodeManager;

public abstract class LocalCacheValidateCodeGenerator extends DefaultValidateCodeGenerator {
	
	public LocalCacheValidateCodeGenerator(String validateCodeType) {
		super(validateCodeType, SimpleValidateCodeManager.getInstance());
	}
	
	public LocalCacheValidateCodeGenerator(String validateCodeType, ValidateCodeManager manager) {
		super(validateCodeType, manager);
	}
}
