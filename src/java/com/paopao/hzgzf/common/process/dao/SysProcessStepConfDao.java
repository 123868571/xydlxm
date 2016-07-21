/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.common.process.entity.SysProcessStepConf;

/**
 * 流程步骤分配DAO接口
 * @author zhoudk
 * @version 2016-06-22
 */
@MyBatisDao
public interface SysProcessStepConfDao extends CrudDao<SysProcessStepConf> {
	
}