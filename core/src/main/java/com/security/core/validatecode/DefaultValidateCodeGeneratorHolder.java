package com.security.core.validatecode;

import java.util.Map;

public class DefaultValidateCodeGeneratorHolder implements ValidateCodeGeneratorHolder {
	
	private Map<String, ValidateCodeGenerator> generators;
	
	public DefaultValidateCodeGeneratorHolder(Map<String, ValidateCodeGenerator> generators) {
		this.generators = generators;
	}

	@Override
	public ValidateCodeGenerator getGenerator(String validateType) {
		return generators.get(validateType);
	}
}
