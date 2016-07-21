/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionLog;
import com.paopao.hzgzf.modules.gzf.dao.GzfInspectionLogDao;

/**
 * 巡查日志Service
 * @author Hongjun
 * @version 2016-02-28
 */
@Service
@Transactional(readOnly = true)
public class GzfInspectionLogService extends CrudService<GzfInspectionLogDao, GzfInspectionLog> {

	public GzfInspectionLog get(String id) {
		return super.get(id);
	}
	
	public List<GzfInspectionLog> findList(GzfInspectionLog gzfInspectionLog) {
		return super.findList(gzfInspectionLog);
	}
	
	public Page<GzfInspectionLog> findPage(Page<GzfInspectionLog> page, GzfInspectionLog gzfInspectionLog) {
		return super.findPage(page, gzfInspectionLog);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfInspectionLog gzfInspectionLog) {
		super.save(gzfInspectionLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfInspectionLog gzfInspectionLog) {
		super.delete(gzfInspectionLog);
	}
	
}