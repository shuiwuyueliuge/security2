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
	
	private ValidateCodeGeneratorHolder holder;
	
	private VaildateCodeFailureHandler failureHandler;
	
	private Set<RequestMatcher> requestMatcher;
	
	private String keyParameter;
	
	private String valueParameter;
	
	private final Log logger = LogFactory.getLog(getClass());
	
	public ValidateCodeFilter(ValidateCodeGeneratorHolder holder, VaildateCodeFailureHandler failureHandler, Set<RequestMatcher> requestMatcher, ValidateCodeProperties validateCodeProperties) {
		this.holder = holder;
		this.failureHandler = failureHandler;
		this.requestMatcher = requestMatcher;
		if (validateCodeProperties != null) {
			this.keyParameter = validateCodeProperties.getKeyParameter();
			this.valueParameter = validateCodeProperties.getValueParameter();
			return;
		}
		
		this.keyParameter = "key";
		this.valueParameter = "code";
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
			ValidateCodeGenerator generator = holder.getGeneratorByUri(uri);
		    if (generator == null) {
		    	logger.debug("验证码登陆url [" + uri + "]错误");
		    	return false;
		    }
		    
		    if (!generator.check(getParameter(request, keyParameter), getParameter(request, valueParameter))) {
		    	logger.debug("验证码登陆url [" + uri + "]验证码验证失败");
		    	return false;
		    }
		}
		
		return true;
	}

	private String getRequestURI(HttpServletRequest request) {
		return request.getRequestURI();
	}
	
	private String getParameter(HttpServletRequest request, String key) {
		return request.getParameter(key);
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
