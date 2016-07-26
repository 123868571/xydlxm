/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.sys.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.sys.entity.HonorWall;

import java.util.List;

/**
 * 荣誉墙DAO接口
 * @author luoo
 * @version 2016-07-26
 */
@MyBatisDao
public interface HonorWallDao extends CrudDao<HonorWall> {

    public List<HonorWall> findAll(HonorWall hw);
	
}