/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfMaintenanceLog;
import com.paopao.hzgzf.modules.gzf.dao.GzfMaintenanceLogDao;

/**
 * 维修日志Service
 * @author Hongjun
 * @version 2016-01-17
 */
@Service
@Transactional(readOnly = true)
public class GzfMaintenanceLogService extends CrudService<GzfMaintenanceLogDao, GzfMaintenanceLog> {

	public GzfMaintenanceLog get(String id) {
		return super.get(id);
	}
	
	public List<GzfMaintenanceLog> findList(GzfMaintenanceLog gzfMaintenanceLog) {
		return super.findList(gzfMaintenanceLog);
	}
	
	public Page<GzfMaintenanceLog> findPage(Page<GzfMaintenanceLog> page, GzfMaintenanceLog gzfMaintenanceLog) {
		return super.findPage(page, gzfMaintenanceLog);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfMaintenanceLog gzfMaintenanceLog) {
		super.save(gzfMaintenanceLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfMaintenanceLog gzfMaintenanceLog) {
		super.delete(gzfMaintenanceLog);
	}
	
}