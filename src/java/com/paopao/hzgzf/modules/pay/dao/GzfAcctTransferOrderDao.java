/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransferOrder;

/**
 * 转帐工单DAO接口
 * @author songyahe
 * @version 2016-04-25
 */
@MyBatisDao
public interface GzfAcctTransferOrderDao extends CrudDao<GzfAcctTransferOrder> {
	
}