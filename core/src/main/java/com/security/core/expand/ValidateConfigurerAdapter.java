package com.security.core.expand;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeFilter;
import com.security.core.validatecode.ValidateCodeGeneratorHolder;

public class ValidateConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private ValidateCodeGeneratorHolder holder;
	
	private VaildateCodeFailureHandler failHandler;
	
	public ValidateConfigurerAdapter(ValidateCodeGeneratorHolder holder, VaildateCodeFailureHandler failHandler) {
		this.holder = holder;
		this.failHandler = failHandler;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		builder.addFilterBefore(new ValidateCodeFilter(holder, failHandler), UsernamePasswordAuthenticationFilter.class);
	}
}
