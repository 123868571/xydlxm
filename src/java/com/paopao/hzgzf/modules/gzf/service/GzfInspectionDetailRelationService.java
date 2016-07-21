/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionDetailRelation;
import com.paopao.hzgzf.modules.gzf.dao.GzfInspectionDetailRelationDao;

/**
 * 巡查设备详情Service
 * @author Hongjun
 * @version 2016-03-16
 */
@Service
@Transactional(readOnly = true)
public class GzfInspectionDetailRelationService extends CrudService<GzfInspectionDetailRelationDao, GzfInspectionDetailRelation> {

	public GzfInspectionDetailRelation get(String id) {
		return super.get(id);
	}
	
	public List<GzfInspectionDetailRelation> findList(GzfInspectionDetailRelation gzfInspectionDetailRelation) {
		return super.findList(gzfInspectionDetailRelation);
	}
	
	public Page<GzfInspectionDetailRelation> findPage(Page<GzfInspectionDetailRelation> page, GzfInspectionDetailRelation gzfInspectionDetailRelation) {
		return super.findPage(page, gzfInspectionDetailRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfInspectionDetailRelation gzfInspectionDetailRelation) {
		super.save(gzfInspectionDetailRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfInspectionDetailRelation gzfInspectionDetailRelation) {
		super.delete(gzfInspectionDetailRelation);
	}
	
}