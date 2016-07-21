/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionCategory;
import com.paopao.hzgzf.modules.gzf.dao.GzfInspectionCategoryDao;

/**
 * 巡查类别Service
 * @author Hongjun
 * @version 2016-03-15
 */
@Service
@Transactional(readOnly = true)
public class GzfInspectionCategoryService extends CrudService<GzfInspectionCategoryDao, GzfInspectionCategory> {

	public GzfInspectionCategory get(String id) {
		return super.get(id);
	}
	
	public List<GzfInspectionCategory> findList(GzfInspectionCategory gzfInspectionCategory) {
		return super.findList(gzfInspectionCategory);
	}
	
	public Page<GzfInspectionCategory> findPage(Page<GzfInspectionCategory> page, GzfInspectionCategory gzfInspectionCategory) {
		return super.findPage(page, gzfInspectionCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfInspectionCategory gzfInspectionCategory) {
		super.save(gzfInspectionCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfInspectionCategory gzfInspectionCategory) {
		super.delete(gzfInspectionCategory);
	}
	
}