/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfAutoMeterRead;

/**
 * 自动抄表DAO接口
 * @author songyahe
 * @version 2016-03-09
 */
@MyBatisDao
public interface GzfAutoMeterReadDao extends CrudDao<GzfAutoMeterRead> {
	
}