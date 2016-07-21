/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.luoo.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.luoo.entity.MyTest;

/**
 * helloDAO接口
 * @author luoo
 * @version 2016-07-21
 */
@MyBatisDao
public interface MyTestDao extends CrudDao<MyTest> {
	
}