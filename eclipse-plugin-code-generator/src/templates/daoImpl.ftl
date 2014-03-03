package com.tibet.app.${package}.dao.impl;


import org.springframework.stereotype.Repository;

import com.ctl.sys.common.dao.impl.HibernateDaoImpl;
import com.tibet.app.${package}.dao.${className}Dao;
import com.tibet.app.entity.${className};


@Repository("${lowerCaseClassName}Dao")
public class ${className}DaoImpl extends HibernateDaoImpl<${className}, Long> implements ${className}Dao {

}
