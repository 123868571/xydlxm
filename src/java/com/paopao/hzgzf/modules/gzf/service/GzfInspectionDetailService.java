/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionDetail;
import com.paopao.hzgzf.modules.gzf.dao.GzfInspectionDetailDao;

/**
 * 巡查设备详情Service
 * @author Hongjun
 * @version 2016-03-15
 */
@Service
@Transactional(readOnly = true)
public class GzfInspectionDetailService extends CrudService<GzfInspectionDetailDao, GzfInspectionDetail> {

	public GzfInspectionDetail get(String id) {
		return super.get(id);
	}
	
	public List<GzfInspectionDetail> findList(GzfInspectionDetail gzfInspectionDetail) {
		return super.findList(gzfInspectionDetail);
	}
	
	public Page<GzfInspectionDetail> findPage(Page<GzfInspectionDetail> page, GzfInspectionDetail gzfInspectionDetail) {
		return super.findPage(page, gzfInspectionDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfInspectionDetail gzfInspectionDetail) {
		super.save(gzfInspectionDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfInspectionDetail gzfInspectionDetail) {
		super.delete(gzfInspectionDetail);
	}
	
}