package com.tibet.app.${package}.mng.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ctl.sys.common.dao.impl.HibernateDaoImpl;
import com.ctl.sys.common.mng.impl.BaseGkGridMngImpl;
import com.ctl.sys.common.vo.GkGridParams;
import com.ctl.sys.excetion.AppException;
import com.ctl.sys.hibernate4.Updater;
import com.tibet.app.a.dao.${className}Dao;
import com.tibet.app.a.mng.${className}Mng;
import com.tibet.app.entity.${className};
import com.tibet.app.k.mng.Kc01Mng;


<#assign text=fields />
<#assign jsonFields=text?eval />
@Service
public class ${className}MngImpl extends BaseGkGridMngImpl<${className}, Long> implements ${className}Mng {
	private ${className}Dao ${lowerCaseClassName}Dao;
	
	@Resource(name = "${lowerCaseClassName}Dao")
	@Override
	public void setHibernateDao(HibernateDaoImpl<${className}, Long> hibernateDao) {
		super.hibernateDao = hibernateDao;
		this.${lowerCaseClassName}Dao = (${className}Dao)hibernateDao;
	}

	@Override
	public ${className} save${className}(${className} bean) throws AppException {
		<#list jsonFields as item>
		<#if item.unique?default(false)==true>
		this.${lowerCaseClassName}Dao.checkExists(bean, "${item.fieldName}:${item.label}");
		</#if>
		</#list>
		
		if(bean.get${className}Id() != null) {
			<#--
			bean.setGxsj(new Date());
			-->
			Updater<${className}> updater = new Updater<${className}>(bean);
			<#--
			updater.exclude("cjsj");
			-->
			bean = (${className})this.${lowerCaseClassName}Dao.updateByUpdater(updater);
		}else {
			<#--
			bean.setCjsj(new Date());
			-->
			this.saveOrUpdate(bean);
		}
		return bean;
	}

	@Override
	public void ajaxGrid(HttpServletRequest request,
			HttpServletResponse response, Object... args) {
		String sql = "${ajaxGridSql}";
		GkGridParams pa = new GkGridParams(request, response, sql);
		this.loadGkGrid(pa);		
	}
}