/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspection;
import com.paopao.hzgzf.modules.gzf.dao.GzfInspectionDao;

/**
 * 巡查设备Service
 * @author Hongjun
 * @version 2016-03-15
 */
@Service
@Transactional(readOnly = true)
public class GzfInspectionService extends CrudService<GzfInspectionDao, GzfInspection> {

	public GzfInspection get(String id) {
		return super.get(id);
	}
	
	public List<GzfInspection> findList(GzfInspection gzfInspection) {
		return super.findList(gzfInspection);
	}
	
	public Page<GzfInspection> findPage(Page<GzfInspection> page, GzfInspection gzfInspection) {
		return super.findPage(page, gzfInspection);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfInspection gzfInspection) {
		super.save(gzfInspection);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfInspection gzfInspection) {
		super.delete(gzfInspection);
	}
	
}