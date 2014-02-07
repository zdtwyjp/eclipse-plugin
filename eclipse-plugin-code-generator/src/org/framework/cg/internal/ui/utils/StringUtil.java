package org.framework.cg.internal.ui.utils;

public class StringUtil {
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str) || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
