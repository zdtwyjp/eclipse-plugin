<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cs" uri="/cs" %>
<cs:html title="${appname}">
	<cs:buttonPannel>
		<cs:button value="新增" btnModel="dialog" cssClass="button-add" action="/admin/k/kb01/add.do" onEnd="reloadGrid('gkGrid')" type="button"></cs:button>
		<cs:button value="编辑" btnModel="dialog" cssClass="button-edit" action="/admin/k/kb01/detail.do" param="{kb01Id:$('#gkGrid').get(0).g.getSelected()}" type="button" onStart="ckCheckBoxGridSel('gkGrid')" onEnd="reloadGrid('gkGrid')"></cs:button>
		<cs:button value="删除" cssClass="button-del" description="批量删除" btnModel="ajax" confirm="确定要删除选中数据？" action="/admin/k/kb01/multiDelete.do" type="button" onStart="ckGridSel('gkGrid')" onEnd="reloadGrid('gkGrid')" param="{id:$('#gkGrid').get(0).g.getSelected()}"></cs:button>
	</cs:buttonPannel>
	<cs:pannel cols="6">
		<cs:placeHolder colspan="6" haveLabel="false" cssStyle="text-align:right;">
			<cs:button id="search" validate="false" cssClass="button-ser" type="submit" btnModel="search" searchParam="{gkGrid:[$('#dwmc')]}" value="搜索"></cs:button>
		</cs:placeHolder>
  	</cs:pannel>
  	<cs:grid grid="${gkGrid}"></cs:grid>
</cs:html>