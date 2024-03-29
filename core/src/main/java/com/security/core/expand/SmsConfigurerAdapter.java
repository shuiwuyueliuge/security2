package com.security.core.expand;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.core.authentication.sms.SmsAuthenticationFilter;
import com.security.core.authentication.sms.SmsAuthenticationProvider;
import com.security.core.validatecode.ValidateCodeFilter;

public class SmsConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

	@Nullable
	private AuthenticationSuccessHandler success;
	
	@Nullable
	private AuthenticationFailureHandler failer;
    
    private UserDetailsService user;
    
    private String smsProcessUrl;
    
    private String mobile;

    public SmsConfigurerAdapter(AuthenticationSuccessHandler success, AuthenticationFailureHandler failer,
			UserDetailsService user, String smsProcessUrl, String mobile) {
		this.success = success;
		this.failer = failer;
		this.user = user;
		this.mobile = mobile;
		this.smsProcessUrl = smsProcessUrl;
	}

	@Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsAuthenticationFilter smsCodeAuthenticationFilter = new SmsAuthenticationFilter(smsProcessUrl, mobile);
		smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        if (success != null) {
        	smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(success);
        }
        
        if (failer != null) {
        	smsCodeAuthenticationFilter.setAuthenticationFailureHandler(failer);
        }
        
        SmsAuthenticationProvider smsCodeAuthenticationProvider = new SmsAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(user);
        builder.authenticationProvider(smsCodeAuthenticationProvider)
               .addFilterAfter(smsCodeAuthenticationFilter, ValidateCodeFilter.class)
               .addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}