/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.luoo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.luoo.entity.MyTest;
import com.paopao.hzgzf.modules.luoo.dao.MyTestDao;

/**
 * helloService
 * @author luoo
 * @version 2016-07-21
 */
@Service
@Transactional(readOnly = true)
public class MyTestService extends CrudService<MyTestDao, MyTest> {

	public MyTest get(String id) {
		return super.get(id);
	}
	
	public List<MyTest> findList(MyTest myTest) {
		return super.findList(myTest);
	}
	
	public Page<MyTest> findPage(Page<MyTest> page, MyTest myTest) {
		return super.findPage(page, myTest);
	}
	
	@Transactional(readOnly = false)
	public void save(MyTest myTest) {
		super.save(myTest);
	}
	
	@Transactional(readOnly = false)
	public void delete(MyTest myTest) {
		super.delete(myTest);
	}
	
}