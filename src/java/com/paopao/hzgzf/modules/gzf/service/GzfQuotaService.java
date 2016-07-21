/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfQuota;
import com.paopao.hzgzf.modules.gzf.dao.GzfQuotaDao;

/**
 * 额度限制Service
 * @author Hongjun
 * @version 2016-05-05
 */
@Service
@Transactional(readOnly = true)
public class GzfQuotaService extends CrudService<GzfQuotaDao, GzfQuota> {

	public GzfQuota get(String id) {
		return super.get(id);
	}
	
	public List<GzfQuota> findList(GzfQuota gzfQuota) {
		return super.findList(gzfQuota);
	}
	
	public Page<GzfQuota> findPage(Page<GzfQuota> page, GzfQuota gzfQuota) {
		return super.findPage(page, gzfQuota);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfQuota gzfQuota) {
		super.save(gzfQuota);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfQuota gzfQuota) {
		super.delete(gzfQuota);
	}
	
}