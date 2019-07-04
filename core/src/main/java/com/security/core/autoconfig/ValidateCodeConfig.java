package com.security.core.autoconfig;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.security.core.expand.SmsConfigurerAdapter;
import com.security.core.expand.ValidateConfigurerAdapter;
import com.security.core.validatecode.DefaultValidateCodeGeneratorHolder;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeGenerator;
import com.security.core.validatecode.ValidateCodeGeneratorHolder;
import com.security.core.validatecode.ValidateCodeManager;
import com.security.core.validatecode.ValidateCodeTypeEnum;
import com.security.core.validatecode.ValidateController;

@Import({ ValidateController.class })
public class ValidateCodeConfig {
	
	@Autowired(required = false)
	private VaildateCodeFailureHandler failHandler;
	
	@Autowired(required = false)
	private LoginProperties loginProperties;
	
	@Autowired(required = false)
	private ValidateCodeManager validateCodeManager;
	
	@Autowired(required = false)
	private AuthenticationSuccessHandler success;
	
	@Autowired(required = false)
	private AuthenticationFailureHandler failer;

	@Bean
	public ValidateCodeGeneratorHolder generatorHolder(Set<ValidateCodeGenerator> generators) {
		return new DefaultValidateCodeGeneratorHolder(generators);
	}
	
	@Bean
	public ValidateConfigurerAdapter validateConfigurerAdapter(ValidateCodeGeneratorHolder holder) {
		ValidateConfigurerAdapter adapter = new ValidateConfigurerAdapter();
		adapter.setValidateCodeManager(validateCodeManager);
		adapter.setLoginPage(loginProperties);
		adapter.setFailHandler(failHandler);
		adapter.setHolder(holder);
		adapter.setLoginUrl(loginProperties);
		return adapter;
	}
	
	@Bean
	public SmsConfigurerAdapter smsConfigurerAdapter(UserDetailsService user, ValidateCodeGeneratorHolder holder) {
		ValidateCodeGenerator generator = holder.getGenerator(ValidateCodeTypeEnum.SMS.getType());
		if (generator != null) {
			return new SmsConfigurerAdapter(success, failer, user, generator.getLoginUri());
		}
		
		return new SmsConfigurerAdapter(success, failer, user, null);
	}
}
