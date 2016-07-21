/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.dlj.entity.ComWorkorder;

/**
 * 工单分配表DAO接口
 * @author pjs
 * @version 2016-06-02
 */
@MyBatisDao
public interface ComWorkorderDao extends CrudDao<ComWorkorder> {
	
}