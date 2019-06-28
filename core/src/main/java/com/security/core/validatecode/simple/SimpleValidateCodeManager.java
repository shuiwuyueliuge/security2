package com.security.core.validatecode.simple;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import com.security.core.validatecode.ValidateCodeManager;

public class SimpleValidateCodeManager implements ValidateCodeManager {

	@Override
	@CachePut(value = "validateCodeCache", key = "#key")
	public String save(String key, String code) {
		return code;
	}

	@Override
	@Cacheable(value = "validateCodeCache", key = "#key")
	public String get(String key) {
		return null;
	}

	@Override
	@CacheEvict(value = "validateCodeCache", key = "#key")
	public String remove(String key) {
		return null;
	}
}
