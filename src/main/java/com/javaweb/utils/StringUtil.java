package com.javaweb.utils;

public class StringUtil {
	public static boolean checkStringNull(String value) {
		if (value  != null && !value.equals("")) {
			return true;
		}
		return false;
	}
}
