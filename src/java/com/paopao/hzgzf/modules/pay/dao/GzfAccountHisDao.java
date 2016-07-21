/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountHis;

/**
 * 历史帐户DAO接口
 * @author songyahe
 * @version 2016-03-30
 */
@MyBatisDao
public interface GzfAccountHisDao extends CrudDao<GzfAccountHis> {
	
}