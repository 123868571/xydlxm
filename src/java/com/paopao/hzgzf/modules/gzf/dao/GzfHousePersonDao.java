/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;

/**
 * 房屋与人员DAO接口
 * @author Hongjun
 * @version 2016-01-08
 */
@MyBatisDao
public interface GzfHousePersonDao extends CrudDao<GzfHousePerson> {

    GzfHousePerson query(GzfHousePerson gzfHousePerson);

    GzfHousePerson query1(GzfHousePerson gzfHousePerson);

    GzfHousePerson getByHistory(String id);

}