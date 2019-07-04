package com.security.core.validatecode;

public interface ValidateCodeGeneratorHolder {

	ValidateCodeGenerator getGenerator(String validateType);
	
	int size();
	
	void addGenerator(ValidateCodeGenerator generator);
}
