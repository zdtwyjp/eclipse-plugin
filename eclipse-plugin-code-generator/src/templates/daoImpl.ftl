package com.tibet.app.${package}.dao.impl;


import org.springframework.stereotype.Repository;

import com.ctl.sys.common.dao.impl.HibernateDaoImpl;
import com.ctl.sys.util.StringUtil;
import com.tibet.app.a.dao.${className}Dao;
import com.tibet.app.entity.${className};
import com.tibet.app.util.ServiceUtil;


@Repository("${lowerCaseClassName}Dao")
public class ${className}DaoImpl extends HibernateDaoImpl<${className}, Long> implements ${className}Dao {

}
