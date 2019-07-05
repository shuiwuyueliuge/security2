package com.security.core.validatecode;

import java.util.List;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public interface ValidateCodeGeneratorHolder {

	ValidateCodeGenerator getGeneratorByType(String validateType);
	
	ValidateCodeGenerator getGeneratorByUri(String uri);
	
	int size();
	
	void addGenerator(ValidateCodeGenerator generator);
	
	List<AntPathRequestMatcher> toAntPathRequestMatcher();
}
