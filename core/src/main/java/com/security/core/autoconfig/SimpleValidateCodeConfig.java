package com.security.core.autoconfig;

//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeGenerator;
import com.security.core.validatecode.ValidateCodeManager;
import com.security.core.validatecode.simple.SimpleImgValidateCodeGenerator;
import com.security.core.validatecode.simple.SimpleValidateCodeManager;

//@ConditionalOnProperty(prefix = "security.simple-validate-code", name = "enable", havingValue = "true")
public class SimpleValidateCodeConfig {

	@Bean
	public ValidateCodeGenerator img(ValidateCodeManager manager) {
		return new SimpleImgValidateCodeGenerator(manager);
	}
	
	@Bean
	public ValidateCodeManager codeManager() {
		return new SimpleValidateCodeManager();
	}
	
	@Bean
	public VaildateCodeFailureHandler codeFailureHandler() {
		return (request, response, exception) -> {
			response.getWriter().write("Vaildate Code Failure");
		};
	}
}
