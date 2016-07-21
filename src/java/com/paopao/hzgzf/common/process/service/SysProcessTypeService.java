/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.process.entity.SysProcessType;
import com.paopao.hzgzf.common.process.dao.SysProcessTypeDao;

/**
 * 流程类型管理Service
 * @author zhoudk
 * @version 2016-06-29
 */
@Service
@Transactional(readOnly = true)
public class SysProcessTypeService extends CrudService<SysProcessTypeDao, SysProcessType> {

	public SysProcessType get(String id) {
		return super.get(id);
	}
	
	public List<SysProcessType> findList(SysProcessType sysProcessType) {
		return super.findList(sysProcessType);
	}
	
	public Page<SysProcessType> findPage(Page<SysProcessType> page, SysProcessType sysProcessType) {
		return super.findPage(page, sysProcessType);
	}
	
	@Transactional(readOnly = false)
	public void save(SysProcessType sysProcessType) {
		super.save(sysProcessType);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysProcessType sysProcessType) {
		super.delete(sysProcessType);
	}
	
}