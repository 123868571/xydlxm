/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransferOrderHis;
import com.paopao.hzgzf.modules.pay.dao.GzfAcctTransferOrderHisDao;

/**
 * 转帐工单历史Service
 * @author songyahe
 * @version 2016-04-19
 */
@Service
@Transactional(readOnly = true)
public class GzfAcctTransferOrderHisService extends CrudService<GzfAcctTransferOrderHisDao, GzfAcctTransferOrderHis> {

	public GzfAcctTransferOrderHis get(String id) {
		return super.get(id);
	}
	
	public List<GzfAcctTransferOrderHis> findList(GzfAcctTransferOrderHis gzfAcctTransferOrderHis) {
		return super.findList(gzfAcctTransferOrderHis);
	}
	
	public Page<GzfAcctTransferOrderHis> findPage(Page<GzfAcctTransferOrderHis> page, GzfAcctTransferOrderHis gzfAcctTransferOrderHis) {
		return super.findPage(page, gzfAcctTransferOrderHis);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfAcctTransferOrderHis gzfAcctTransferOrderHis) {
		super.save(gzfAcctTransferOrderHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfAcctTransferOrderHis gzfAcctTransferOrderHis) {
		super.delete(gzfAcctTransferOrderHis);
	}
	
}