package com.security.core.validatecode;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultValidateCodeGeneratorHolder implements ValidateCodeGeneratorHolder {
	
	private Map<String, ValidateCodeGenerator> generators = new ConcurrentHashMap<String, ValidateCodeGenerator>();
	
	public DefaultValidateCodeGeneratorHolder(Set<ValidateCodeGenerator> set) {
		set.forEach(generator -> {
			generators.put(generator.getValidateCodeType(), generator);
		});
	}

	@Override
	public ValidateCodeGenerator getGeneratorByType(String validateType) {
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

	@Override
	public ValidateCodeGenerator getGeneratorByUri(String uri) {
		return generators.entrySet().stream().filter((e) -> e.getValue().getLoginUri().equals(uri)).findFirst().get().getValue();
	}	
}
