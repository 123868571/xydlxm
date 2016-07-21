/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小区DAO接口
 * @author Hongjun
 * @version 2015-12-20
 */
@MyBatisDao
public interface GzfVillageDao extends CrudDao<GzfVillage> {

	List<GzfVillage> findAllList(@Param("page") Page page, @Param("DEL_FLAG_NORMAL") Integer flag);
}
