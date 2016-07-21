/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.common.process.entity.SysProcessType;

/**
 * 流程类型管理DAO接口
 * @author zhoudk
 * @version 2016-06-29
 */
@MyBatisDao
public interface SysProcessTypeDao extends CrudDao<SysProcessType> {
	
}