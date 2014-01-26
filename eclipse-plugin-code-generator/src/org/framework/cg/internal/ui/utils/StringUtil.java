package org.framework.cg.internal.ui.utils;

import java.lang.reflect.Field;

public class StringUtil {
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str) || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static String createClassPath(String path) {
		if(isEmpty(path)) {
			throw new IllegalArgumentException("Illegal argument!");
		}
		String[] str = path.split("/src/");
		if(str.length != 2) {
			str = path.split("/src/main/java/");
			if(str.length != 2){
				throw new RuntimeException("Error file!");
			}
		}
		String result = str[1].replace(".java", "");
		result = result.replace("/", ".");
		return result;
	}
	public static void main(String[] args) {
		try {
			Class a = Class.forName(createClassPath("/eclipse_plugin_code_generator/src/eclipse_plugin_code_generator/A01.java"));
			System.out.println(a.getSimpleName());
			System.out.println(a.getSimpleName().substring(1));
			Field[] fields = a.getDeclaredFields();
			for(Field field : fields) {
				System.out.println(field.getName());
				System.out.println(field.getType());
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
