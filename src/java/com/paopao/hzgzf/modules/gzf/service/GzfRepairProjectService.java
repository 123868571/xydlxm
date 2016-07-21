/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairProject;
import com.paopao.hzgzf.modules.gzf.dao.GzfRepairProjectDao;

/**
 * 报修项目Service
 * @author Hongjun
 * @version 2016-01-02
 */
@Service
@Transactional(readOnly = true)
public class GzfRepairProjectService extends CrudService<GzfRepairProjectDao, GzfRepairProject> {

	@Autowired
	GzfRepairProjectDao gzfRepairProjectDao;

	public GzfRepairProject get(String id) {
		return super.get(id);
	}
	
	public List<GzfRepairProject> findList(GzfRepairProject gzfRepairProject) {
		return super.findList(gzfRepairProject);
	}
	
	public Page<GzfRepairProject> findPage(Page<GzfRepairProject> page, GzfRepairProject gzfRepairProject) {
		return super.findPage(page, gzfRepairProject);
	}

	public List<GzfRepairProject> findAllList(Page page){
		return gzfRepairProjectDao.findAllList(page, 0);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfRepairProject gzfRepairProject) {
		super.save(gzfRepairProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfRepairProject gzfRepairProject) {
		super.delete(gzfRepairProject);
	}
	
}
