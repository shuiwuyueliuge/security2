package com.security.core.validatecode;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultValidateCodeGeneratorHolder implements ValidateCodeGeneratorHolder {
	
	private Map<String, ValidateCodeGenerator> generators = new ConcurrentHashMap<String, ValidateCodeGenerator>();
	
	public DefaultValidateCodeGeneratorHolder(Set<ValidateCodeGenerator> set) {
		set.forEach(generator -> {
			System.out.println(generator.getValidateCodeType());
			System.out.println(generator.getLoginUri());
			generators.put(generator.getValidateCodeType(), generator);
		});
	}

	@Override
	public ValidateCodeGenerator getGenerator(String validateType) {
		return generators.get(validateType);
	}

	@Override
	public int size() {
		return generators.size();
	}

	@Override
	public void addGenerator(ValidateCodeGenerator generator) {
		generators.put(generator.getValidateCodeType(), generator);
	}	
}
