package com.javaweb.utils;

import java.util.List;

public class ListUtil {
public static boolean listIsNull(List<String> data) {
	if (data != null && data.size() > 0) {
		return true;
	}
	
	return false;
}


}
