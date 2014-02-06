<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cs" uri="/cs" %>
<cs:html title="${appname}">

	<script type="text/javascript">
	    function initData(data){
	    	$("#kb01_jgdm").val(data.jgdm);
	    }
	    
	   
	</script>

	<cs:pannel cols="4">
		<cs:select name="kb01.kb01BySjid.kb01Id" label="上级单位" list="${unitMap}" colspan="4" ></cs:select>
		<cs:input name="kb01.dwmc" label="单位名称" colspan="2" validator="{required:true,rangelength:[2,30]}"></cs:input>
		<cs:input name="kb01.jgdm" label="机构代码" colspan="1" validator="{required:true,rangelength:[2,10]}"></cs:input>
		
		<cs:placeHolder colspan="1" haveLabel="false" cssStyle="text-align:left;">
	  		<cs:button value="自动生成" cssClass="button-nomal" btnModel="ajax_msg" validate="false" action="/admin/k/kb01/autoCreateJgdm.do" onEnd="initData" ></cs:button>
		</cs:placeHolder>
		
		<cs:checkboxs name="kb01.dwlx" colspan="4" type="radio" list="${syscode.单位类型}" label="单位类型"></cs:checkboxs>
		<cs:textarea name="kb01.dz" label="地址" colspan="4" validator="{rangelength:[0,120]}" ></cs:textarea>
		<cs:input name="kb01.fzr" label="负责人" colspan="2" validator="{rangelength:[0,5]}"></cs:input>
		<cs:input name="kb01.dh" label="电话" colspan="2" validator="{required:false,rangelength:[0,20]}"></cs:input>
		<cs:input name="kb01.ch" label="传真" colspan="2" validator="{required:false,rangelength:[0,13]}"></cs:input>
		<cs:checkboxs name="kb01.sfjy" colspan="2" type="radio" list="${syscode.是否禁用}"  label="是否禁用"></cs:checkboxs>
		<cs:textarea name="kb01.bz" label="备注" colspan="4"></cs:textarea>
		<cs:input type="label" name="kb01.cjsj" label="创建时间" colspan="2"></cs:input>
		<cs:input type="label" name="kb01.kc01.dlm" label="创建人" colspan="2"></cs:input>
  	</cs:pannel>
  	
  	<cs:buttonPannel type="detail">
  		<cs:button id="save" value="保存" cssClass="button-nomal" btnModel="ajax" action="/admin/k/kb01/save.do" type="submit" validate="true"></cs:button>
  		<cs:button value="删除" btnModel="delete" cssClass="button-del" deletekey="kb01.kb01Id" action="/admin/k/kb01/delete.do" type="button" ></cs:button>
  	</cs:buttonPannel>
  	
	<cs:hidden name="kb01.kb01Id"></cs:hidden>
</cs:html>