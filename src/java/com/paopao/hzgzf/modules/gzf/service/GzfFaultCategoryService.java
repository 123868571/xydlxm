/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfFaultCategory;
import com.paopao.hzgzf.modules.gzf.dao.GzfFaultCategoryDao;

/**
 * 故障类别Service
 * @author Hongjun
 * @version 2016-01-02
 */
@Service
@Transactional(readOnly = true)
public class GzfFaultCategoryService extends CrudService<GzfFaultCategoryDao, GzfFaultCategory> {

	public GzfFaultCategory get(String id) {
		return super.get(id);
	}
	
	public List<GzfFaultCategory> findList(GzfFaultCategory gzfFaultCategory) {
		return super.findList(gzfFaultCategory);
	}
	
	public Page<GzfFaultCategory> findPage(Page<GzfFaultCategory> page, GzfFaultCategory gzfFaultCategory) {
		return super.findPage(page, gzfFaultCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfFaultCategory gzfFaultCategory) {
		super.save(gzfFaultCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfFaultCategory gzfFaultCategory) {
		super.delete(gzfFaultCategory);
	}
	
}