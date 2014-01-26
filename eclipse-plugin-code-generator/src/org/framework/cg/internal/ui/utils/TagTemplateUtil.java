package org.framework.cg.internal.ui.utils;

import java.util.List;

import org.framework.cg.internal.ui.vo.JavaModel;

public class TagTemplateUtil {
	
	public static String prePage(){
		StringBuffer sb = new StringBuffer();
		sb.append("<%@ page language=\"java\" pageEncoding=\"UTF-8\"%>\n");
		sb.append("<%@ taglib prefix=\"cs\" uri=\"/cs\" %>\n");
		sb.append("<cs:html title=\"${appname}\">\n");
		sb.append("\t<cs:pannel cols=\"4\">\n");
		return sb.toString();
	}
	
	public static String endPage(){
		StringBuffer sb = new StringBuffer();
		sb.append("\t</cs:pannel>\n");
		sb.append("\t\t<div class=\"detail-box blue-detail-box blue-btnbox\">\n");
		sb.append("\t\t\t<cs:button id=\"save\" value=\"保存\" cssClass=\"button-nomal\" btnModel=\"ajax\" action=\"/admin/a/a01/save.do\" type=\"submit\" validate=\"true\"></cs:button>\n");
		sb.append("\t\t</div>\n");
		sb.append("\t<cs:hidden name=\"a01.a01Id\"></cs:hidden>\n");
		sb.append("</cs:html>\n");
		return sb.toString();
	}
	
	public static String createTagFromTemplate(String tag){
		String result = "";
		if("input".equals(tag)){
			result = "\t\t\t<cs:input name=\"a01.htbh\" label=\"合同编号\" colspan=\"4\"></cs:input>\n";
		}else if("select".equals(tag)){
			result = "\t\t\t<cs:select name=\"a01.b01.b01Id\" label=\"关联项目\" list=\"${glxmMap}\" colspan=\"2\"></cs:select>\n";
		}else if("textarea".equals(tag)){
			result = "\t\t\t<cs:textarea name=\"a01.bz\" label=\"备注\" colspan=\"4\" validator=\"{maxlength:[300]}\"></cs:textarea>\n";
		}
		return result;
	}
	
	public static String generateJspPage(String className, List<JavaModel> list){
		StringBuffer jspPage = new StringBuffer();
		jspPage.append(prePage());
		for(JavaModel jm : list) {
			if("java.lang.String".equals(jm.getFieldType())){
				jspPage.append(createTagFromTemplate("input"));
			}
		}
		jspPage.append(endPage());
		return jspPage.toString();
	}
}
