package com.security.core.config;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class LogoutProperties {
	
	private String url;

	@Tolerate
	public LogoutProperties() {}
}
