<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cs" uri="/cs" %>
<cs:html title="${appname}">
	<cs:buttonPannel>
	    <cs:button value="新增"  cssClass="button-add" btnModel="dialog" action="${prePath}/add.do" onEnd="reloadGrid('gkGrid')" type="button" dialogHeight="460" dialogWidth="645"></cs:button>
		<cs:button value="编辑"  onStart="ckCheckBoxGridSel('gkGrid')" cssClass="button-edit" btnModel="ajax" action="${prePath}/detail.do" dialogHeight="460" dialogWidth="645" param="{id:$('#gkGrid').get(0).g.getSelected()}" type="button"  onEnd="reloadGrid('gkGrid')"></cs:button>
		<cs:button value="删除" cssClass="button-del" btnModel="delete" action="${prePath}/multiDelete.do" type="button" onStart="ckGridSel('gkGrid')" onEnd="reloadGrid('gkGrid')" param="{id:$('#gkGrid').get(0).g.getSelected()}"></cs:button>
	</cs:buttonPannel>
	<cs:pannel cols="6">
	<#assign text=fields />
	<#assign jsonFields=text?eval />
	<#list jsonFields as item>
	<#if item.queryable?default(false)==true>
		<#if item.tagType?default("")=="input" || item.tagType?default("")=="textarea">
		<cs:input<#rt/>
		</#if>
		<#if item.tagType?default("")=="select">
		<cs:select<#rt/>
		</#if>
		<#if item.tagType?default("")=="datePicker">
		<cs:datePicker<#rt/>
		</#if>
		<#if item.tagType?default("")=="multiSelect" || item.tagType?default("")=="multiSelect1.1">
		<cs:multiSelect<#rt/>
		</#if>
<#if item.tagType?default("")=="multiSelect1.1">
 version="1.1" <#rt/>
</#if>
 name="${item.fieldName}"<#rt/>
 label="${item.label}"<#rt/>
 colspan="3"<#rt/>
 />
	</#if>
	</#list>
    	
		<cs:placeHolder colspan="3" haveLabel="false" cssStyle="text-align:right;">
			<cs:button id="search" cssClass="button-ser" validate="false" type="submit" btnModel="search" searchParam="{gkGrid:[$('#dah'),$('#jyz'),$('#xmmc'),$('#xmbh'),$('#fam')]}" value="搜索"></cs:button>
		</cs:placeHolder>
	</cs:pannel>
	<cs:grid grid="${gkGrid}"></cs:grid>
</cs:html>

