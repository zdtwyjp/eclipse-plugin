package org.framework.cg.internal.ui.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.framework.cg.internal.ui.vo.FieldModel;

public class ServiceUtil {
	
	public static String convertFieldType(String fieldType) {
		if(StringUtil.isEmpty(fieldType)) {
			return "";
		}
		String result = "";
		if(fieldType.contains("String")) {
			result = "String";
		}else if(fieldType.contains("Integer") || fieldType.contains("I")) {
			result = "Integer";
		}else if(fieldType.contains("Date")) {
			result = "Date";
		}else if(fieldType.contains("Long") || fieldType.contains("J")) {
			result = "Long";
		}else if(fieldType.contains("Double") || fieldType.contains("D")) {
			result = "Double";
		}else if(fieldType.contains("Short") || fieldType.contains("S")) {
			result = "Short";
		}
		return result;
	}
	
	public static String convertFieldTypeToTagType(String fieldType){
		if(StringUtil.isEmpty(fieldType)) {
			return "";
		}
		String result = Constants.TAG_NAME_INPUT;
		if(fieldType.equals("Date")){
			result = Constants.TAG_NAME_DATEPICKER;
		}
		return result;
	}
	
	public static String getClassName(String fileName){
		if(StringUtil.isEmpty(fileName)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String[] arr = fileName.split("\\.");
		if(arr.length != 2){
			throw new IllegalArgumentException("Illegal fileName!");
		}
		return arr[0];
	}
	
	public static String createPrepath(String className){
		if(StringUtil.isEmpty(className)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String pre = className.substring(0, 1).toLowerCase();
		className = pre + className.substring(1);
		return Constants.PRE_PATH + "/" + pre + "/" + className ;
	}
	
	public static String classNameToLowerCase(String className){
		if(StringUtil.isEmpty(className)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String pre = className.substring(0, 1).toLowerCase();
		className = pre + className.substring(1);
		return className;
	}
	
	public static String getPackageNameFromClassName(String className){
		if(StringUtil.isEmpty(className)){
			throw new IllegalArgumentException("Illegal arguments!");
		}
		String pre = className.substring(0, 1).toLowerCase();
		return pre;
	}
	
	public static List<FieldModel> createFieldModelFromClass(ICompilationUnit compilationUnit){
		Long no = 0L;
		List<FieldModel> list = new ArrayList<FieldModel>();
		String className = compilationUnit.getElementName();
		className = getClassName(className);
		FieldModel fm = null;
		try {
			IType[] allTypes = compilationUnit.getAllTypes();
			for(IType iType : allTypes) {
				IField[] tempFields = iType.getFields();
				for(IField field : tempFields) {
					String fieldName = field.getElementName();
					String typeSignature = field.getTypeSignature();
					if (fieldName.endsWith("Id")) {
						continue;
					}
					if (fieldName.equals("serialVersionUID")) {
						continue;
					}
					String fieldType = convertFieldType(typeSignature);
					if (StringUtil.isEmpty(fieldType)){
						continue;
					}
					no++;
					String tagType = convertFieldTypeToTagType(fieldType);
					fm = new FieldModel();
					fm.setNo(no);
					fm.setFieldName(fieldName);
					fm.setFieldType(fieldType);
					fm.setLabel(fieldName);
					fm.setNullable(true);
					fm.setTagType(tagType);
					fm.setQueryable(false);
					fm.setColumnDisplayable(true);
					fm.setUnique(false);
					list.add(fm);
				}
			}
		}catch(JavaModelException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	public static String createAjaxGridSql(List<FieldModel> list, String className){
		String id = classNameToLowerCase(className) + "_id";
		String sql = "select t." + id + ", " ;
		for(FieldModel fm : list) {
			if(fm.getColumnDisplayable()){
				sql += "t." + fm.getFieldName() + ", ";
			}
		}
		sql = sql.trim().substring(0, sql.length() - 2);
		sql += " from " + className + " t ";
		return sql;
	}
	
	public static String createQueryParam(List<FieldModel> list){
		String queryParam = "";
		for(FieldModel fm : list) {
			if(fm.getQueryable()){
				queryParam += "$('#"+fm.getFieldName()+"')" + ", ";
			}
		}
		if(queryParam.length() > 1){
			queryParam = queryParam.trim().substring(0, queryParam.length() - 2);
		}
		return queryParam;
	}
	

	public static void main(String[] args) {
		System.out.println(createPrepath("A01"));
		System.out.println(classNameToLowerCase("A01"));
	}
	
}
