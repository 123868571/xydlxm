/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;

/**
 * 房屋信息DAO接口
 *
 * @author Hongjun
 * @version 2016-01-02
 */
@MyBatisDao
public interface GzfHouseInfoDao extends CrudDao<GzfHouseInfo> {

    List<GzfHouseInfo> findAllList(@Param("page") Page page,
                                   @Param("startVillageDate") String startVillageDate,
                                   @Param("endVillageDate") String endVillageDate,
                                   @Param("palacesIds") List<String> palacesIds,
                                   @Param("DEL_FLAG_NORMAL") Integer flag,
                                   @Param("startDate") String startDate,
                                   @Param("endDate") String endDate);

    GzfHouseInfo getByHouseInfo(GzfHouseInfo gzfHouseInfo);

    void updateSync(GzfHouseInfo gzfHouseInfo);

    void updateInfoAndStatus(GzfHouseInfo gzfHouseInfo);

    int countHouse(String personal);

    List<GzfHouseInfo> findHouseList(GzfHouseInfo gzfHouseInfo);

}
