/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.test.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.test.entity.LuooTest;

/**
 * testDAO接口
 * @author luoo
 * @version 2016-07-22
 */
@MyBatisDao
public interface LuooTestDao extends CrudDao<LuooTest> {
	
}