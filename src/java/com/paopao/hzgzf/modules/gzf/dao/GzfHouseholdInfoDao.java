/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import java.util.List;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;

/**
 * 人员信息DAO接口
 * @author Hongjun
 * @version 2016-01-02
 */
@MyBatisDao
public interface GzfHouseholdInfoDao extends CrudDao<GzfHouseholdInfo> {

    GzfHouseholdInfo getByEntity(GzfHouseholdInfo gzfHouseholdInfo);

    GzfHouseholdInfo getByCardId(String cardid);

    List<GzfHouseholdInfo> getByNameAndPhone(GzfHouseholdInfo gzfHouseholdInfo);

    int countHousehold(String personal);

    List<GzfHouseholdInfo> findHouseholdList(GzfHouseholdInfo gzfHouseholdInfo);

}