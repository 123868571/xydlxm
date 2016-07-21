/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.dlj.entity.ComStaff;

/**
 * 员工DAO接口
 * @author panjs
 * @version 2016-05-26
 */
@MyBatisDao
public interface ComStaffDao extends CrudDao<ComStaff> {
	
}