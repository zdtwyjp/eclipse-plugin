<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cs" uri="/cs" %>
<cs:html title="${appname}">
	<cs:pannel cols="4">
	<#assign text=fields />
	<#assign jsonFields=text?eval />
	<#list jsonFields as item>
		<#if item.tagType?default("")=="input">
		<cs:input<#rt/>
		</#if>
		<#if item.tagType?default("")=="select">
		<cs:select<#rt/>
		</#if>
		<#if item.tagType?default("")=="datePicker">
		<cs:datePicker<#rt/>
		</#if>
		<#if item.tagType?default("")=="textarea">
		<cs:textarea<#rt/>
		</#if>
		<#if item.tagType?default("")=="multiSelect" || item.tagType?default("")=="multiSelect1.1">
		<cs:multiSelect<#rt/>
		</#if>
<#if item.tagType?default("")=="multiSelect1.1">
 version="1.1" <#rt/>
</#if>
 name="${lowerCaseClassName}.${item.fieldName}"<#rt/>
 label="${item.label}"<#rt/>
 colspan="2"<#rt/>
<#if item.nullable?default(true)==false>
 validator="{required:true}"<#rt/>
</#if>
 />
	</#list>
  	</cs:pannel>
  	
  	<cs:buttonPannel type="detail">
  		<cs:button id="save" value="保存" cssClass="button-nomal" btnModel="ajax" action="${prePath}/save.do" type="submit" validate="true"></cs:button>
  	</cs:buttonPannel>
  	
	<cs:hidden name="${lowerCaseClassName}.${lowerCaseClassName}Id"></cs:hidden>
</cs:html>
