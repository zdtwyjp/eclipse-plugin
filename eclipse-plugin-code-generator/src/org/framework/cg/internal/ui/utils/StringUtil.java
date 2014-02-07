package org.framework.cg.internal.ui.utils;


public class StringUtil {
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str) || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static String convertFieldType(String fieldType) {
		if(isEmpty(fieldType)) {
			return "";
		}
		String result = "";
		if(fieldType.contains("String")) {
			result = "String";
		}else if(fieldType.contains("Integer") || fieldType.contains("int")) {
			result = "Integer";
		}else if(fieldType.contains("Date")) {
			result = "Date";
		}else if(fieldType.contains("Long") || fieldType.contains("long")) {
			result = "Long";
		}else if(fieldType.contains("Double") || fieldType.contains("double")) {
			result = "Double";
		}
		return result;
	}
	
	public static String getClassName(String fileName){
		if(isEmpty(fileName)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String[] arr = fileName.split("\\.");
		if(arr.length != 2){
			throw new IllegalArgumentException("Illegal fileName!");
		}
		return arr[0];
	}
	
	public static String createPrepath(String className){
		if(isEmpty(className)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String pre = className.substring(0, 1).toLowerCase();
		className = pre + className.substring(1);
		return Constants.PRE_PATH + "/" + pre + "/" + className ;
	}
	
	public static String classNameToLowerCase(String className){
		if(isEmpty(className)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String pre = className.substring(0, 1).toLowerCase();
		className = pre + className.substring(1);
		return className;
	}
	
	public static String getPackageNameFromClassName(String className){
		if(isEmpty(className)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String pre = className.substring(0, 1).toLowerCase();
		return pre;
	}

	// public static String createClassPath(String path) {
	// if(isEmpty(path)) {
	// throw new IllegalArgumentException("Illegal argument!");
	// }
	// String[] str = path.split("/src/");
	// if(str.length != 2) {
	// str = path.split("/src/main/java/");
	// if(str.length != 2){
	// throw new RuntimeException("Error file!");
	// }
	// }
	// String result = str[1].replace(".java", "");
	// result = result.replace("/", ".");
	// return result;
	// }
	public static void main(String[] args) {
		System.out.println(createPrepath("A01"));
		System.out.println(classNameToLowerCase("A01"));
		// try {
		// Class a = Class.forName(createClassPath("/eclipse_plugin_code_generator/src/eclipse_plugin_code_generator/A01.java"));
		// System.out.println(a.getSimpleName());
		// System.out.println(a.getSimpleName().substring(1));
		// Field[] fields = a.getDeclaredFields();
		// for(Field field : fields) {
		// System.out.println(field.getName());
		// System.out.println(field.getType());
		// }
		//
		// }catch(ClassNotFoundException e) {
		// e.printStackTrace();
		// }
	}
}
