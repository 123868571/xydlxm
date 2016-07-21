/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfBuilding;

/**
 * 楼栋DAO接口
 * @author Hongjun
 * @version 2015-12-20
 */
@MyBatisDao
public interface GzfBuildingDao extends CrudDao<GzfBuilding> {
	
}