/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransferOrder;
import com.paopao.hzgzf.modules.pay.dao.GzfAcctTransferOrderDao;

/**
 * 转帐工单Service
 * @author songyahe
 * @version 2016-04-19
 */
@Service
@Transactional(readOnly = true)
public class GzfAcctTransferOrderService extends CrudService<GzfAcctTransferOrderDao, GzfAcctTransferOrder> {

	public GzfAcctTransferOrder get(String id) {
		return super.get(id);
	}
	
	public List<GzfAcctTransferOrder> findList(GzfAcctTransferOrder gzfAcctTransferOrder) {
		return super.findList(gzfAcctTransferOrder);
	}
	
	public Page<GzfAcctTransferOrder> findPage(Page<GzfAcctTransferOrder> page, GzfAcctTransferOrder gzfAcctTransferOrder) {
		return super.findPage(page, gzfAcctTransferOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfAcctTransferOrder gzfAcctTransferOrder) {
		super.save(gzfAcctTransferOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfAcctTransferOrder gzfAcctTransferOrder) {
		super.delete(gzfAcctTransferOrder);
	}
	
}