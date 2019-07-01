package com.security.core.validatecode.simple;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import com.security.core.validatecode.ValidateCodeManager;

public class SimpleValidateCodeManager implements ValidateCodeManager {
	
	private static Map<String, String> cacheMap;
	
	static {
		cacheMap = Collections.synchronizedMap(new WeakHashMap<String, String>());
	}

	@Override
	public String save(String key, String code) {
		cacheMap.put(key, code);
		return code;
	}

	@Override
	public String get(String key) {
		return cacheMap.get(key);
	}

	@Override
	public String remove(String key) {
		return cacheMap.remove(key);
	}
}
