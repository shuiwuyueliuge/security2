package com.security.core.config;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SocialProperties {
	
	private String signupUrl;

	@Tolerate
	public SocialProperties() {}
}
