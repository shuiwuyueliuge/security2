package com.security.core.validatecode;

public enum ValidateCodeTypeEnum {

	IMG("img"), SMS("sms");
	
	private String type;
	
	private ValidateCodeTypeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
