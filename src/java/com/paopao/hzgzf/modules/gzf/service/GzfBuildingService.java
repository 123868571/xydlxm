/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfBuilding;
import com.paopao.hzgzf.modules.gzf.dao.GzfBuildingDao;

/**
 * 楼栋Service
 * @author Hongjun
 * @version 2015-12-20
 */
@Service
@Transactional(readOnly = true)
public class GzfBuildingService extends CrudService<GzfBuildingDao, GzfBuilding> {

	public GzfBuilding get(String id) {
		return super.get(id);
	}
	
	public List<GzfBuilding> findList(GzfBuilding gzfBuilding) {
		return super.findList(gzfBuilding);
	}
	
	public Page<GzfBuilding> findPage(Page<GzfBuilding> page, GzfBuilding gzfBuilding) {
		return super.findPage(page, gzfBuilding);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfBuilding gzfBuilding) {
		super.save(gzfBuilding);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfBuilding gzfBuilding) {
		super.delete(gzfBuilding);
	}
	
}