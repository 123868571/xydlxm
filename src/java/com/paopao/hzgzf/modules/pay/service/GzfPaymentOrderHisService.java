/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.entity.GzfPaymentOrderHis;
import com.paopao.hzgzf.modules.pay.dao.GzfPaymentOrderHisDao;

/**
 * 外围缴费工单历史表Service
 * @author songyahe
 * @version 2016-04-19
 */
@Service
@Transactional(readOnly = true)
public class GzfPaymentOrderHisService extends CrudService<GzfPaymentOrderHisDao, GzfPaymentOrderHis> {

	public GzfPaymentOrderHis get(String id) {
		return super.get(id);
	}
	
	public List<GzfPaymentOrderHis> findList(GzfPaymentOrderHis gzfPaymentOrderHis) {
		return super.findList(gzfPaymentOrderHis);
	}
	
	public Page<GzfPaymentOrderHis> findPage(Page<GzfPaymentOrderHis> page, GzfPaymentOrderHis gzfPaymentOrderHis) {
		return super.findPage(page, gzfPaymentOrderHis);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfPaymentOrderHis gzfPaymentOrderHis) {
		super.save(gzfPaymentOrderHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfPaymentOrderHis gzfPaymentOrderHis) {
		super.delete(gzfPaymentOrderHis);
	}
	
}