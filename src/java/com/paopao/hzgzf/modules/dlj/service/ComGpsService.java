/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.entity.ComGps;
import com.paopao.hzgzf.modules.dlj.dao.ComGpsDao;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;

/**
 * GPSService
 * @author pjs
 * @version 2016-05-28
 */
@Service
@Transactional(readOnly = true)
public class ComGpsService extends CrudService<ComGpsDao, ComGps> {

	public ComGps get(String id) {
		return super.get(id);
	}
	
	public List<ComGps> findList(ComGps comGps) {
		User user = UserUtils.getUser();
		comGps.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "c", "u"));
		return super.findList(comGps);
	}
	
	public Page<ComGps> findPage(Page<ComGps> page, ComGps comGps) {
		User user = UserUtils.getUser();
		comGps.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "c", "u"));
		return super.findPage(page, comGps);
	}
	
	@Transactional(readOnly = false)
	public void save(ComGps comGps) {
		super.save(comGps);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComGps comGps) {
		super.delete(comGps);
	}
	
}