/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.test.entity.LuooTest;
import com.paopao.hzgzf.modules.test.dao.LuooTestDao;

/**
 * testService
 * @author luoo
 * @version 2016-07-22
 */
@Service
@Transactional(readOnly = true)
public class LuooTestService extends CrudService<LuooTestDao, LuooTest> {

	public LuooTest get(String id) {
		return super.get(id);
	}
	
	public List<LuooTest> findList(LuooTest luooTest) {
		return super.findList(luooTest);
	}
	
	public Page<LuooTest> findPage(Page<LuooTest> page, LuooTest luooTest) {
		return super.findPage(page, luooTest);
	}
	
	@Transactional(readOnly = false)
	public void save(LuooTest luooTest) {
		super.save(luooTest);
	}
	
	@Transactional(readOnly = false)
	public void delete(LuooTest luooTest) {
		super.delete(luooTest);
	}
	
}