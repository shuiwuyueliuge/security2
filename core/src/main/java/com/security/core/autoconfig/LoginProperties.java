package com.security.core.autoconfig;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class LoginProperties {
	
	private String page;
	
	private String processingUrl;
	
	private String usernameParameter;
	
	private String passwordParameter;

	@Tolerate
	public LoginProperties() {}
}
