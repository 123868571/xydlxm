/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairManagement;

import java.util.List;

/**
 * 报修管理DAO接口
 * @author Hongjun
 * @version 2016-01-18
 */
@MyBatisDao
public interface GzfRepairManagementDao extends CrudDao<GzfRepairManagement> {

    List<GzfRepairManagement> findListWithProject(GzfRepairManagement gzfRepairManagement);
	
}
