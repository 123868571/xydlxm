/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.process.entity.SysProcessInstanceDetail;
import com.paopao.hzgzf.common.process.dao.SysProcessInstanceDetailDao;

/**
 * 流程实例明细Service
 * @author zhoudk
 * @version 2016-06-23
 */
@Service
@Transactional(readOnly = true)
public class SysProcessInstanceDetailService extends CrudService<SysProcessInstanceDetailDao, SysProcessInstanceDetail> {

	public SysProcessInstanceDetail get(String id) {
		return super.get(id);
	}
	
	public List<SysProcessInstanceDetail> findList(SysProcessInstanceDetail sysProcessInstanceDetail) {
		return super.findList(sysProcessInstanceDetail);
	}
	
	public Page<SysProcessInstanceDetail> findPage(Page<SysProcessInstanceDetail> page, SysProcessInstanceDetail sysProcessInstanceDetail) {
		return super.findPage(page, sysProcessInstanceDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(SysProcessInstanceDetail sysProcessInstanceDetail) {
		super.save(sysProcessInstanceDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysProcessInstanceDetail sysProcessInstanceDetail) {
		super.delete(sysProcessInstanceDetail);
	}
	
}