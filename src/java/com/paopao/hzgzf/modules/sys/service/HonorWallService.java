/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.sys.service;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.sys.dao.HonorWallDao;
import com.paopao.hzgzf.modules.sys.entity.HonorWall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 荣誉墙Service
 * @author luoo
 * @version 2016-07-26
 */
@Service
@Transactional(readOnly = true)
public class HonorWallService extends CrudService<HonorWallDao, HonorWall> {

	public HonorWall get(String id) {
		return super.get(id);
	}

	public List<HonorWall> findAll() {
		return  dao.findAll(new HonorWall());
	}
	
	public List<HonorWall> findList(HonorWall honorWall) {
		return super.findList(honorWall);
	}
	
	public Page<HonorWall> findPage(Page<HonorWall> page, HonorWall honorWall) {
		return super.findPage(page, honorWall);
	}
	
	@Transactional(readOnly = false)
	public void save(HonorWall honorWall) {
		super.save(honorWall);
	}
	
	@Transactional(readOnly = false)
	public void delete(HonorWall honorWall) {
		super.delete(honorWall);
	}
	
}