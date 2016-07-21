/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.common.process.entity.SysProcessInstance;

/**
 * 流程实例管理DAO接口
 * @author zhoudk
 * @version 2016-06-23
 */
@MyBatisDao
public interface SysProcessInstanceDao extends CrudDao<SysProcessInstance> {
	
}