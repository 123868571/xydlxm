/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairManagerProject;
import com.paopao.hzgzf.modules.gzf.dao.GzfRepairManagerProjectDao;

/**
 * 维修关系管理Service
 * @author Hongjun
 * @version 2016-02-21
 */
@Service
@Transactional(readOnly = true)
public class GzfRepairManagerProjectService extends CrudService<GzfRepairManagerProjectDao, GzfRepairManagerProject> {

	public GzfRepairManagerProject get(String id) {
		return super.get(id);
	}
	
	public List<GzfRepairManagerProject> findList(GzfRepairManagerProject gzfRepairManagerProject) {
		return super.findList(gzfRepairManagerProject);
	}
	
	public Page<GzfRepairManagerProject> findPage(Page<GzfRepairManagerProject> page, GzfRepairManagerProject gzfRepairManagerProject) {
		return super.findPage(page, gzfRepairManagerProject);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfRepairManagerProject gzfRepairManagerProject) {
		super.save(gzfRepairManagerProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfRepairManagerProject gzfRepairManagerProject) {
		super.delete(gzfRepairManagerProject);
	}
	
}