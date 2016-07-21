/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.dlj.entity.ComAttachUpload;

/**
 * 通用附件上传DAO接口
 * @author zdk
 * @version 2016-07-14
 */
@MyBatisDao
public interface ComAttachUploadDao extends CrudDao<ComAttachUpload> {
	
}