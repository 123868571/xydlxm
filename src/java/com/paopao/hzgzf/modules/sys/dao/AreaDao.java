/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.sys.dao;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.persistence.TreeDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.sys.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

    List<Area> findAllList(@Param("DEL_FLAG_NORMAL") Integer flag);

    List<Area> findByParentIdsLike(@Param("parentIds") String parentIds, @Param("DEL_FLAG_NORMAL") Integer flag);

    List<Area> findAreaListByPid(@Param("pid") String pid, @Param("DEL_FLAG_NORMAL") Integer flag);

}
