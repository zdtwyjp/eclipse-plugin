package com.tibet.app.${package}.act;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctl.lib.gkgrid.ColModel;
import com.ctl.lib.gkgrid.GkGrid;
import com.ctl.sys.JsonModel;
import com.ctl.sys.common.act.BaseAct;
import com.ctl.sys.common.vo.GkGridParams;
import com.ctl.sys.excetion.AppException;
import com.ctl.sys.util.StringUtil;
import com.tibet.app.entity.${className};
import com.tibet.app.k.mng.${className}Mng;
import com.tibet.app.util.ServiceUtil;


@Controller
@RequestMapping(value = "${mappingPath}")
public class ${className}Act extends BaseAct{
	@Autowired
	${className}Mng ${lowerCaseClassName}Mng;

	@InitBinder
	public void bindEditor(WebDataBinder dataBinder) throws Exception {
		// dataBinder.registerCustomEditor(Date.class, "csrq", new DateTypeEditor(DateTypeEditor.DF_SHORT));
	}

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main(Model m) {
		GkGrid grid = new GkGrid("gkGrid");
		grid.config.setTitle("Title");
		grid.config.setUrl("ajaxGrid.do");
		grid.config.setBox("checkbox");
		grid.config.setId("${lowerCaseClassName}_id");
		ColModel cm = null;
		cm = new ColModel("标题", "bt");
		grid.colModels.add(cm);
		String g = grid.render();
		m.addAttribute("gkGrid", g);
		return "${mappingPath}/main";
	}

	@RequestMapping(value = "ajaxGrid")
	@ResponseBody
	public void ajaxGrid(HttpServletRequest request,
			HttpServletResponse response) {
		this.${lowerCaseClassName}Mng.ajaxGrid(request, response);
	}

	@RequestMapping(value = "add")
	public String add(Model m, ${className} bean) {
		m.addAttribute("bean", bean);
		bean.setCjsj(new Date());
		return "${mappingPath}/detail";
	}

	@RequestMapping(value = "detail")
	public String detail(Model m, ${className} bean, Long ${lowerCaseClassName}Id) {
		bean = this.${lowerCaseClassName}Mng.get(${lowerCaseClassName}Id);
		m.addAttribute("bean", bean);
		return "${mappingPath}/detail";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonModel save(${className} bean, JsonModel jm) throws AppException {
		bean = this.${lowerCaseClassName}Mng.save${className}(bean);
		jm.setMsg("保存成功!");
		jm.setMsgFunc("closeDialog");
		return jm;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public JsonModel delete(Long ${lowerCaseClassName}Id, JsonModel jm) {
		this.${lowerCaseClassName}Mng.delete(${lowerCaseClassName}Id);
		jm.setMsg("删除成功！");
		jm.setMsgFunc("closeDialog");
		return jm;
	}
	
	@RequestMapping(value = "multiDelete", method = RequestMethod.POST)
	@ResponseBody
	public JsonModel multiDelete(String id, JsonModel jm) throws AppException{
		String[] param = id.split(",");
		Long[] ids = new Long[param.length];
		String tmp = "";
		for(int i = 0; i < ids.length;i++) {
			ids[i] = Long.parseLong(param[i]);
			${className} ${lowerCaseClassName} = this.${lowerCaseClassName}Mng.get(ids[i]);
			if(${lowerCaseClassName} == null){
				throw new AppException("未找到相应记录！");
			}
			try {
				${lowerCaseClassName}Mng.delete(ids[i]);
			}catch(Exception e) {
				e.printStackTrace();
				tmp += ${lowerCaseClassName}.getBt() + ",";
			}
			
		}
		if(!StringUtil.isEmpty(tmp)){
			tmp = tmp.substring(0, tmp.length()-1);
			jm.setMsg("删除成功！(标题为："+tmp+"的系统通知存在数据关联不允许删除！)");
		}else{
			jm.setMsg("删除成功！");
		}
		jm.setMsgFunc("closeDialog");
		return jm;
	}
}