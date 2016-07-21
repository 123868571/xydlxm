/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfQuota;

/**
 * 额度限制DAO接口
 * @author Hongjun
 * @version 2016-05-05
 */
@MyBatisDao
public interface GzfQuotaDao extends CrudDao<GzfQuota> {
	
}