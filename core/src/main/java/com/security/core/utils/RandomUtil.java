package com.security.core.utils;

import java.util.Random;

public class RandomUtil {
	
	public static String validateCoed(int len) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < len; i++) {
			builder.append(random.nextInt(9));
		}

		return builder.toString();
	}
}
