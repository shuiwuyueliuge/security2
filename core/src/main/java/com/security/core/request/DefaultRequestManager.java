package com.security.core.request;

import java.util.Set;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class DefaultRequestManager implements RequestManager {

	private Set<RequestProvider> providers;

	public DefaultRequestManager(Set<RequestProvider> providers) {
		this.providers = providers;
	}

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		providers.forEach(provider -> provider.config(registry));
		registry.anyRequest().authenticated();
	}
}
