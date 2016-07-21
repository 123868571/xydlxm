/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountHis;
import com.paopao.hzgzf.modules.pay.dao.GzfAccountHisDao;

/**
 * 历史帐户Service
 * @author songyahe
 * @version 2016-03-30
 */
@Service
@Transactional(readOnly = true)
public class GzfAccountHisService extends CrudService<GzfAccountHisDao, GzfAccountHis> {

	public GzfAccountHis get(String id) {
		return super.get(id);
	}
	
	public List<GzfAccountHis> findList(GzfAccountHis gzfAccountHis) {
		return super.findList(gzfAccountHis);
	}
	
	public Page<GzfAccountHis> findPage(Page<GzfAccountHis> page, GzfAccountHis gzfAccountHis) {
		return super.findPage(page, gzfAccountHis);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfAccountHis gzfAccountHis) {
		super.save(gzfAccountHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfAccountHis gzfAccountHis) {
		super.delete(gzfAccountHis);
	}
	
}