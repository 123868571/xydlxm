/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.dao.GzfVillageDao;

/**
 * 小区Service
 * @author Hongjun
 * @version 2015-12-20
 */
@Service
@Transactional(readOnly = true)
public class GzfVillageService extends CrudService<GzfVillageDao, GzfVillage> {

	@Autowired
	GzfVillageDao gzfVillageDao;

	public GzfVillage get(String id) {
		return super.get(id);
	}
	
	public List<GzfVillage> findList(GzfVillage gzfVillage) {
		return super.findList(gzfVillage);
	}
	
	public Page<GzfVillage> findPage(Page<GzfVillage> page, GzfVillage gzfVillage) {
		return super.findPage(page, gzfVillage);
	}

	public List<GzfVillage> findAllList(Page<GzfVillage> page){
		return gzfVillageDao.findAllList(page, 0);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfVillage gzfVillage) {
		super.save(gzfVillage);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfVillage gzfVillage) {
		super.delete(gzfVillage);
	}
	
}
