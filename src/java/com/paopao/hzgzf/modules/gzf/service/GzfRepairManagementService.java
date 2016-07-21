/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import com.paopao.hzgzf.modules.gzf.entity.GzfRepair;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairManagement;
import com.paopao.hzgzf.modules.gzf.dao.GzfRepairManagementDao;

/**
 * 报修管理Service
 * @author Hongjun
 * @version 2016-01-18
 */
@Service
@Transactional(readOnly = true)
public class GzfRepairManagementService extends CrudService<GzfRepairManagementDao, GzfRepairManagement> {

	@Autowired
	private GzfRepairManagementDao gzfRepairManagementDao;

	public GzfRepairManagement get(String id) {
		return super.get(id);
	}
	
	public List<GzfRepairManagement> findList(GzfRepairManagement gzfRepairManagement) {
		return super.findList(gzfRepairManagement);
	}
	
	public Page<GzfRepairManagement> findPage(Page<GzfRepairManagement> page, GzfRepairManagement gzfRepairManagement) {
		return super.findPage(page, gzfRepairManagement);
	}

	public List<GzfRepairManagement> findListWithProject(GzfRepairManagement gzfRepairManagement){
		return gzfRepairManagementDao.findListWithProject(gzfRepairManagement);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfRepairManagement gzfRepairManagement) {
		super.save(gzfRepairManagement);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfRepairManagement gzfRepairManagement) {
		super.delete(gzfRepairManagement);
	}
	
}
