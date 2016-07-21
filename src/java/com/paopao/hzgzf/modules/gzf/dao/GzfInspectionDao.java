/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspection;

/**
 * 巡查设备DAO接口
 * @author Hongjun
 * @version 2016-03-15
 */
@MyBatisDao
public interface GzfInspectionDao extends CrudDao<GzfInspection> {
	
}