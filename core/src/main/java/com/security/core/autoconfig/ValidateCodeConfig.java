package com.security.core.autoconfig;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.security.core.expand.ValidateConfigurerAdapter;
import com.security.core.validatecode.DefaultValidateCodeGeneratorHolder;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeGenerator;
import com.security.core.validatecode.ValidateCodeGeneratorHolder;
import com.security.core.validatecode.ValidateController;

@Import({ ValidateController.class, SimpleValidateCodeConfig.class })
public class ValidateCodeConfig {

	@Bean
	public ValidateCodeGeneratorHolder generatorHolder(Map<String, ValidateCodeGenerator> generators) {
		return new DefaultValidateCodeGeneratorHolder(generators);
	}
	
	@Bean
	public ValidateConfigurerAdapter validateConfigurerAdapter(ValidateCodeGeneratorHolder holder,
			VaildateCodeFailureHandler failHandler) {
		return new ValidateConfigurerAdapter(holder, failHandler);
	}
}
