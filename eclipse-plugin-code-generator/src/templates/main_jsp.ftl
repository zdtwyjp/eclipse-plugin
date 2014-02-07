<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cs" uri="/cs" %>
<cs:html title="${appname}">
	<cs:buttonPannel>
	    <cs:button value="新增"  cssClass="button-add" btnModel="dialog" action="${prePath}/add.do" onEnd="reloadGrid('gkGrid')" type="button" dialogHeight="460" dialogWidth="645"></cs:button>
		<cs:button value="编辑"  onStart="ckCheckBoxGridSel('gkGrid')" cssClass="button-edit" btnModel="ajax" action="${prePath}/detail.do" dialogHeight="460" dialogWidth="645" param="{id:$('#gkGrid').get(0).g.getSelected()}" type="button"  onEnd="reloadGrid('gkGrid')"></cs:button>
		<cs:button value="删除" cssClass="button-del" btnModel="delete" action="${prePath}/multiDelete.do" type="button" onStart="ckGridSel('gkGrid')" onEnd="reloadGrid('gkGrid')" param="{id:$('#gkGrid').get(0).g.getSelected()}"></cs:button>
	</cs:buttonPannel>
	<cs:pannel cols="6">
		<cs:placeHolder colspan="3" haveLabel="false" cssStyle="text-align:right;">
			<cs:button id="search" cssClass="button-ser" validate="false" type="submit" btnModel="search" searchParam="{gkGrid:[$('#dah'),$('#jyz'),$('#xmmc'),$('#xmbh'),$('#fam')]}" value="搜索"></cs:button>
		</cs:placeHolder>
	</cs:pannel>
	<cs:grid grid="${gkGrid}"></cs:grid>
</cs:html>

