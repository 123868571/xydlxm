/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报修项目DAO接口
 * @author Hongjun
 * @version 2016-01-02
 */
@MyBatisDao
public interface GzfRepairProjectDao extends CrudDao<GzfRepairProject> {

    List<GzfRepairProject> findAllList(@Param("page") Page page, @Param("DEL_FLAG_NORMAL") Integer flag);

}
