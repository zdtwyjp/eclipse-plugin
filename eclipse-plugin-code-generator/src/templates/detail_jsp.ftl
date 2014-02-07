<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cs" uri="/cs" %>
<cs:html title="${appname}">
	<cs:pannel cols="4">
	<#assign text=fields />
	<#assign jsonFields=text?eval />
	<#list jsonFields as item>
		<#if item.fieldType?default("")=="String">
		<cs:input name="${lowerCaseClassName}.${item.fieldName}" label="${item.fieldName}" colspan="2"></cs:input>
		</#if>
	</#list>
  	</cs:pannel>
  	
  	<cs:buttonPannel type="detail">
  		<cs:button id="save" value="保存" cssClass="button-nomal" btnModel="ajax" action="${prePath}/save.do" type="submit" validate="true"></cs:button>
  	</cs:buttonPannel>
  	
	<cs:hidden name="${lowerCaseClassName}.${lowerCaseClassName}Id"></cs:hidden>
</cs:html>