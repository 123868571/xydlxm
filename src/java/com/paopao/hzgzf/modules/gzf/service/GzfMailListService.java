/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfMailList;
import com.paopao.hzgzf.modules.gzf.dao.GzfMailListDao;

/**
 * 水表Service
 * @author Hongjun
 * @version 2016-01-17
 */
@Service
@Transactional(readOnly = true)
public class GzfMailListService extends CrudService<GzfMailListDao, GzfMailList> {

	public GzfMailList get(String id) {
		return super.get(id);
	}
	
	public List<GzfMailList> findList(GzfMailList gzfMailList) {
		return super.findList(gzfMailList);
	}
	
	public Page<GzfMailList> findPage(Page<GzfMailList> page, GzfMailList gzfMailList) {
		return super.findPage(page, gzfMailList);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfMailList gzfMailList) {
		super.save(gzfMailList);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfMailList gzfMailList) {
		super.delete(gzfMailList);
	}
	
}