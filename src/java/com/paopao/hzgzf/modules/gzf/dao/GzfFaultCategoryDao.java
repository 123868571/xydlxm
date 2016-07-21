/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfFaultCategory;

/**
 * 故障类别DAO接口
 * @author Hongjun
 * @version 2016-01-02
 */
@MyBatisDao
public interface GzfFaultCategoryDao extends CrudDao<GzfFaultCategory> {
	
}