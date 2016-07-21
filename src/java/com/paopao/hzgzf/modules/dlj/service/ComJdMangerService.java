/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.entity.ComJdManger;
import com.paopao.hzgzf.modules.dlj.dao.ComJdMangerDao;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;

/**
 * 局点管理表Service
 * @author pjs
 * @version 2016-05-28
 */
@Service
@Transactional(readOnly = true)
public class ComJdMangerService extends CrudService<ComJdMangerDao, ComJdManger> {

	public ComJdManger get(String id) {
		return super.get(id);
	}
	
	public List<ComJdManger> findList(ComJdManger comJdManger) {
		User user = UserUtils.getUser();
		comJdManger.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "c", "u"));
		return super.findList(comJdManger);
	}
	
	public Page<ComJdManger> findPage(Page<ComJdManger> page, ComJdManger comJdManger) {
		User user = UserUtils.getUser();
		comJdManger.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "c", "u"));
		return super.findPage(page, comJdManger);
	}
	
	@Transactional(readOnly = false)
	public void save(ComJdManger comJdManger) {
		super.save(comJdManger);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComJdManger comJdManger) {
		super.delete(comJdManger);
	}
	
}