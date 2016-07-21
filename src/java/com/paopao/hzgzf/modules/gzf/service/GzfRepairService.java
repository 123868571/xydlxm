/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepair;
import com.paopao.hzgzf.modules.gzf.dao.GzfRepairDao;

/**
 * 维修历史Service
 * @author Hongjun
 * @version 2016-01-14
 */
@Service
@Transactional(readOnly = true)
public class GzfRepairService extends CrudService<GzfRepairDao, GzfRepair> {

	public GzfRepair get(String id) {
		return super.get(id);
	}
	
	public List<GzfRepair> findList(GzfRepair gzfRepair) {
		return super.findList(gzfRepair);
	}
	
	public Page<GzfRepair> findPage(Page<GzfRepair> page, GzfRepair gzfRepair) {
		return super.findPage(page, gzfRepair);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfRepair gzfRepair) {
		super.save(gzfRepair);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfRepair gzfRepair) {
		super.delete(gzfRepair);
	}
	
}