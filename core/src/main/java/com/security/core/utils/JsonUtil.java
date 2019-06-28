package com.security.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();;

	public static String writeValueAsString(Object obj) throws Exception {
		return MAPPER.writeValueAsString(obj);
	}
}
