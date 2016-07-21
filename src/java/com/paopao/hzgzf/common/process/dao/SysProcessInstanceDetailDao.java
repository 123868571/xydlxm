/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.common.process.entity.SysProcessInstanceDetail;

/**
 * 流程实例明细DAO接口
 * @author zhoudk
 * @version 2016-06-23
 */
@MyBatisDao
public interface SysProcessInstanceDetailDao extends CrudDao<SysProcessInstanceDetail> {
	
}