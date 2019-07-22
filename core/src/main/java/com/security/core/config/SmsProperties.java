package com.security.core.config;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SmsProperties {

	private String processingUrl;
	
	private String mobile;
	
	@Tolerate
	public SmsProperties() {}
}
