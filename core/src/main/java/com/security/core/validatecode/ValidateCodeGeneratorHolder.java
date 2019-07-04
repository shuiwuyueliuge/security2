package com.security.core.validatecode;

public interface ValidateCodeGeneratorHolder {

	ValidateCodeGenerator getGeneratorByType(String validateType);
	
	ValidateCodeGenerator getGeneratorByUri(String uri);
	
	int size();
	
	void addGenerator(ValidateCodeGenerator generator);
}
