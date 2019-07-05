package com.security.core.config;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SessionProperties {
	
	private int maximum;
	
	private boolean maxSessionsPreventsLogin;

	@Tolerate
	public SessionProperties() {
		this.maximum = 1;
		this.maxSessionsPreventsLogin = Boolean.FALSE;
	}
}
