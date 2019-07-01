package com.security.core.expand;

import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.security.core.autoconfig.LoginProperties;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeFilter;
import com.security.core.validatecode.ValidateCodeGeneratorHolder;
import com.security.core.validatecode.ValidateCodeManager;
import com.security.core.validatecode.simple.SimpleImgValidateCodeGenerator;
import com.security.core.validatecode.simple.SimpleValidateCodeManager;

public class ValidateConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private ValidateCodeGeneratorHolder holder;
	
	@Nullable
	private VaildateCodeFailureHandler failHandler;
	
	private String loginPage;
	
	@Nullable
	private ValidateCodeManager validateCodeManager;

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		builder.addFilterBefore(new ValidateCodeFilter(holder, failHandler), UsernamePasswordAuthenticationFilter.class);
	}

	public void setHolder(ValidateCodeGeneratorHolder holder) {
		if (holder.size() == 0) {
			holder.addGenerator("img", new SimpleImgValidateCodeGenerator(validateCodeManager));
		}
		
		this.holder = holder;
	}

	public void setFailHandler(VaildateCodeFailureHandler failHandler) {
		if (failHandler == null) {
			failHandler = (request, response, exception) -> {
				response.sendRedirect(loginPage);
			};
		}
		
		this.failHandler = failHandler;
	}

	public void setLoginPage(LoginProperties loginProperties) {
		if (loginProperties != null) {
			loginPage = loginProperties.getPage();
		} else {
			loginPage = "/login";
		}
	}

	public void setValidateCodeManager(ValidateCodeManager validateCodeManager) {
		if (validateCodeManager == null) {
			validateCodeManager = new SimpleValidateCodeManager();
		}
		
		this.validateCodeManager = validateCodeManager;
	}
}
