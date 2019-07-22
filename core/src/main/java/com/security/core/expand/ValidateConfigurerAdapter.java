package com.security.core.expand;

import java.util.HashSet;
import java.util.Set;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.security.core.config.LoginProperties;
import com.security.core.config.SmsProperties;
import com.security.core.config.ValidateCodeProperties;
import com.security.core.validatecode.VaildateCodeFailureHandler;
import com.security.core.validatecode.ValidateCodeFilter;
import com.security.core.validatecode.ValidateCodeGeneratorHolder;
import com.security.core.validatecode.ValidateCodeManager;
import com.security.core.validatecode.simple.SimpleImgValidateCodeGenerator;
import com.security.core.validatecode.simple.SimpleValidateCodeManager;

public class ValidateConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	@Nullable
	private VaildateCodeFailureHandler failHandler;
	
	private String loginPage;
	
	private String loginUrl;
	
	private SmsProperties smsProperties;

	private Set<RequestMatcher> requestMatcher;
	
	private ValidateCodeManager validateCodeManager;
	
	private ValidateCodeProperties validateCodeProperties;

	public void setValidateCodeProperties(ValidateCodeProperties validateCodeProperties) {
		if (validateCodeProperties == null) {
			validateCodeProperties = ValidateCodeProperties.builder().keyParameter("key").valueParameter("code").build();
		}
		
		this.validateCodeProperties = validateCodeProperties;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		builder.addFilterBefore(new ValidateCodeFilter(validateCodeManager, failHandler, requestMatcher, validateCodeProperties, smsProperties.getMobile()), UsernamePasswordAuthenticationFilter.class);
	}

	public void setHolder(ValidateCodeGeneratorHolder holder) {
		if (holder.size() == 0) {
			holder.addGenerator(new SimpleImgValidateCodeGenerator("img"));
		}
		
		this.requestMatcher = new HashSet<RequestMatcher>();
		this.requestMatcher.add(new AntPathRequestMatcher(loginUrl, "POST"));
		this.requestMatcher.add(new AntPathRequestMatcher(smsProperties.getProcessingUrl(), "POST"));
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
		
		if (loginProperties != null) {
			loginUrl = loginProperties.getProcessingUrl();
		} else {
			loginUrl = "/login";
		}
	}
	
	public void setValidateCodeManager(ValidateCodeManager validateCodeManager) {
		if (validateCodeManager == null) {
			validateCodeManager = SimpleValidateCodeManager.getInstance();
		}
		
		this.validateCodeManager = validateCodeManager;
	}
	
	public void setSmsLoginUrl(SmsProperties properties) {
		if (properties == null) {
			properties = SmsProperties.builder().mobile("mobile").processingUrl("/login/sms").build();
		}
		
		this.smsProperties = properties;
	}
}
