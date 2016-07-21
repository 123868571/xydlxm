/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.ftp.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.common.ftp.entity.ComFtpConf;

/**
 * ftp服务器路径配置DAO接口
 * @author zdk
 * @version 2016-07-17
 */
@MyBatisDao
public interface ComFtpConfDao extends CrudDao<ComFtpConf> {
	
}