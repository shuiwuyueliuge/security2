package com.security.core.autoconfig;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import com.security.core.expand.ValidateConfigurerAdapter;
import com.security.core.validatecode.DefaultValidateCodeGeneratorHolder;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeGenerator;
import com.security.core.validatecode.ValidateCodeGeneratorHolder;
import com.security.core.validatecode.ValidateCodeManager;
import com.security.core.validatecode.ValidateController;

@Import({ ValidateController.class })
public class ValidateCodeConfig {
	
	@Autowired(required = false)
	private VaildateCodeFailureHandler failHandler;
	
	@Autowired(required = false)
	private LoginProperties loginProperties;
	
	@Autowired(required = false)
	private ValidateCodeManager validateCodeManager;

	@Bean
	public ValidateCodeGeneratorHolder generatorHolder(Map<String, ValidateCodeGenerator> generators) {
		return new DefaultValidateCodeGeneratorHolder(generators);
	}
	
	@Bean
	public ValidateConfigurerAdapter validateConfigurerAdapter(ValidateCodeGeneratorHolder holder) {
		ValidateConfigurerAdapter adapter = new ValidateConfigurerAdapter();
		adapter.setValidateCodeManager(validateCodeManager);
		adapter.setLoginPage(loginProperties);
		adapter.setFailHandler(failHandler);
		adapter.setHolder(holder);
		return adapter;
	}
}
