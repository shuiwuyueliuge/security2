package com.security.core.validatecode;

public interface ValidateCodeManager {

	String save(String key, String code);

	String get(String key);
	
	String remove(String key);
}
