package com.security.core.config;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ValidateCodeProperties {
	
	private String keyParameter;
	
	private String valueParameter;

	@Tolerate
	public ValidateCodeProperties() {}
}
