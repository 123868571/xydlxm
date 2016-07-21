/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.dlj.entity.ComRecord;

/**
 * 通用变更记录DAO接口
 * @author zdk
 * @version 2016-07-14
 */
@MyBatisDao
public interface ComRecordDao extends CrudDao<ComRecord> {
	
}