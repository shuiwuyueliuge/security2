package com.security.core.validatecode;

import java.io.IOException;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import com.security.core.config.ValidateCodeProperties;
import com.security.core.exception.ValidateCodeException;

public class ValidateCodeFilter extends OncePerRequestFilter {
	
	private ValidateCodeManager manager;
	
	private VaildateCodeFailureHandler failureHandler;
	
	private Set<RequestMatcher> requestMatcher;
	
	private String smsKeyParameter;
	
	private String keyParameter;
	
	private String valueParameter;
	
	private final Log logger = LogFactory.getLog(getClass());
	
	public ValidateCodeFilter(ValidateCodeManager manager, VaildateCodeFailureHandler failureHandler, Set<RequestMatcher> requestMatcher, ValidateCodeProperties validateCodeProperties, String smsKeyParameter) {
		this.manager = manager;
		this.failureHandler = failureHandler;
		this.requestMatcher = requestMatcher;
		this.keyParameter = validateCodeProperties.getKeyParameter();
		this.valueParameter = validateCodeProperties.getValueParameter();
		this.smsKeyParameter = smsKeyParameter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (!verify(request)) {
			failureHandler.onCheckCodeFailure(request, response, new ValidateCodeException("code error"));
			return;
		}
		
		filterChain.doFilter(request, response);
	}
	
	private boolean verify(HttpServletRequest request) {
		String uri = getRequestURI(request);
		if (matches(request)) {
			logger.debug("验证码登陆url [" + 	uri + "]");
		    if (!manager.check(getParameter(request), getValueParameter(request))) {
		    	logger.debug("验证码登陆url [" + uri + "]验证码验证失败");
		    	return false;
		    }
		}
		
		return true;
	}

	private String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI();
	}
	
	private String getParameter(HttpServletRequest request) {
		String parameter = request.getParameter(keyParameter);
		if (parameter == null) {
			parameter = request.getParameter(smsKeyParameter);
		}
		
		return parameter;
	}
	
	private String getValueParameter(HttpServletRequest request) {
		return request.getParameter(valueParameter);
	}
	
	private boolean matches(HttpServletRequest request) {
		for (RequestMatcher antPathRequestMatcher : requestMatcher) {
			if (antPathRequestMatcher.matches(request)) {
				return true;
			}
		}
		
		return false;
	}
}
