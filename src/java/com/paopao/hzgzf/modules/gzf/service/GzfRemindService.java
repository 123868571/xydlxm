/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfRemind;
import com.paopao.hzgzf.modules.gzf.dao.GzfRemindDao;

/**
 * 提醒Service
 * @author Hongjun
 * @version 2016-01-17
 */
@Service
@Transactional(readOnly = true)
public class GzfRemindService extends CrudService<GzfRemindDao, GzfRemind> {

	public GzfRemind get(String id) {
		return super.get(id);
	}
	
	public List<GzfRemind> findList(GzfRemind gzfRemind) {
		return super.findList(gzfRemind);
	}
	
	public Page<GzfRemind> findPage(Page<GzfRemind> page, GzfRemind gzfRemind) {
		return super.findPage(page, gzfRemind);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfRemind gzfRemind) {
		super.save(gzfRemind);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfRemind gzfRemind) {
		super.delete(gzfRemind);
	}
	
}