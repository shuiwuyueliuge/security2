package com.security.core.autoconfig;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class RememberMeProperties {
	
	private int tokenValiditySeconds;

	@Tolerate
	public RememberMeProperties() {}
}
