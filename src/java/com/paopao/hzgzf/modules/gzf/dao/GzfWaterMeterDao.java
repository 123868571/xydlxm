/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import java.util.List;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfWaterMeter;

/**
 * 水表DAO接口
 * @author Hongjun
 * @version 2016-01-17
 */
@MyBatisDao
public interface GzfWaterMeterDao extends CrudDao<GzfWaterMeter> {

    List<GzfWaterMeter> getByHouseId(String id);

}