/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.entity.GzfPaymentOrder;
import com.paopao.hzgzf.modules.pay.dao.GzfPaymentOrderDao;

/**
 * 外围缴费工单Service
 * @author songyahe
 * @version 2016-04-25
 */
@Service
@Transactional(readOnly = true)
public class GzfPaymentOrderService extends CrudService<GzfPaymentOrderDao, GzfPaymentOrder> {

	public GzfPaymentOrder get(String id) {
		return super.get(id);
	}
	
	public List<GzfPaymentOrder> findList(GzfPaymentOrder gzfPaymentOrder) {
		return super.findList(gzfPaymentOrder);
	}
	
	public Page<GzfPaymentOrder> findPage(Page<GzfPaymentOrder> page, GzfPaymentOrder gzfPaymentOrder) {
		return super.findPage(page, gzfPaymentOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfPaymentOrder gzfPaymentOrder) {
		super.save(gzfPaymentOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfPaymentOrder gzfPaymentOrder) {
		super.delete(gzfPaymentOrder);
	}
	
}