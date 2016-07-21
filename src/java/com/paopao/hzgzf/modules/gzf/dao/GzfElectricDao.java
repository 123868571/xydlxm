/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import java.util.List;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfElectric;

/**
 * 电表录入DAO接口
 * @author Hongjun
 * @version 2016-01-18
 */
@MyBatisDao
public interface GzfElectricDao extends CrudDao<GzfElectric> {

    List<GzfElectric> getByHouseId(String id);

}