/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionDetail;

/**
 * 巡查设备详情DAO接口
 * @author Hongjun
 * @version 2016-03-15
 */
@MyBatisDao
public interface GzfInspectionDetailDao extends CrudDao<GzfInspectionDetail> {
	
}