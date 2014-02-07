package com.tibet.app.${package}.mng;

import com.ctl.sys.common.mng.BaseGkGridMng;
import com.ctl.sys.excetion.AppException;
import com.tibet.app.entity.${className};


public interface ${className}Mng extends BaseGkGridMng<${className}, Long> {
	/**
	 * 
	* @Title: save${className}
	* @Description: 保存${className}
	* @param ${lowerCaseClassName}
	* @return
	* @throws AppException
	* @return ${className}
	* @throws
	*/
	${className} save${className}(${className} ${lowerCaseClassName}) throws AppException;
	
}
