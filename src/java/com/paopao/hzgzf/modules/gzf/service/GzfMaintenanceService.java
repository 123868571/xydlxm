/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfMaintenance;
import com.paopao.hzgzf.modules.gzf.dao.GzfMaintenanceDao;

/**
 * 维修修管理Service
 * @author Hongjun
 * @version 2016-01-18
 */
@Service
@Transactional(readOnly = true)
public class GzfMaintenanceService extends CrudService<GzfMaintenanceDao, GzfMaintenance> {

	public GzfMaintenance get(String id) {
		return super.get(id);
	}
	
	public List<GzfMaintenance> findList(GzfMaintenance gzfMaintenance) {
		return super.findList(gzfMaintenance);
	}
	
	public Page<GzfMaintenance> findPage(Page<GzfMaintenance> page, GzfMaintenance gzfMaintenance) {
		return super.findPage(page, gzfMaintenance);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfMaintenance gzfMaintenance) {
		super.save(gzfMaintenance);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfMaintenance gzfMaintenance) {
		super.delete(gzfMaintenance);
	}
	
}