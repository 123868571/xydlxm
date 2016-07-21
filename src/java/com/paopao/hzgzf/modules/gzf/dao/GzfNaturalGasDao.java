/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import java.util.List;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfNaturalGas;

/**
 * 天然气表录入DAO接口
 * @author Hongjun
 * @version 2016-01-18
 */
@MyBatisDao
public interface GzfNaturalGasDao extends CrudDao<GzfNaturalGas> {

    List<GzfNaturalGas> getByHouseId(String id);
	
}