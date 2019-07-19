package com.security.core.validatecode;

//import java.util.Set;
//import org.springframework.security.web.util.matcher.RequestMatcher;

public interface ValidateCodeGeneratorHolder {

	ValidateCodeGenerator getGeneratorByType(String validateType);
	
	//ValidateCodeGenerator getGeneratorByUri(String uri);
	
	int size();
	
	void addGenerator(ValidateCodeGenerator generator);
	
	//Set<RequestMatcher> toAntPathRequestMatcher();
	
	//Set<RequestMatcher> toUsernameRequestMatcher();
	
	//Set<String> loginUris();
}
