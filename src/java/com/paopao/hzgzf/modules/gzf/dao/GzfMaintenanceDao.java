/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfMaintenance;

/**
 * 维修修管理DAO接口
 * @author Hongjun
 * @version 2016-01-18
 */
@MyBatisDao
public interface GzfMaintenanceDao extends CrudDao<GzfMaintenance> {
	
}