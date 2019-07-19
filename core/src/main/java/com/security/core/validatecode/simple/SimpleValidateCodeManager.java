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
	
	public static ValidateCodeManager getInstance() {
		return ManagerEnum.SINGLETON.simpleValidateCodeManager;
	}

	@Override
	public String save(String key, String code) {
		cacheMap.put(key, code);
		return code;
	}
	
	@Override
	public boolean check(String key, String code) {
		String cached = cacheMap.remove(key);
		return code.equals(cached);
	}
	
	private enum ManagerEnum {
		SINGLETON;
		
		private SimpleValidateCodeManager simpleValidateCodeManager;
		
		private ManagerEnum() {
			this.simpleValidateCodeManager = new SimpleValidateCodeManager();
		}
	}
}
