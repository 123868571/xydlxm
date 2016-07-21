/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.process.entity.SysProcessStepConf;
import com.paopao.hzgzf.common.process.dao.SysProcessStepConfDao;

/**
 * 流程步骤分配Service
 * @author zhoudk
 * @version 2016-06-22
 */
@Service
@Transactional(readOnly = true)
public class SysProcessStepConfService extends CrudService<SysProcessStepConfDao, SysProcessStepConf> {

	public SysProcessStepConf get(String id) {
		return super.get(id);
	}
	
	public List<SysProcessStepConf> findList(SysProcessStepConf sysProcessStepConf) {
		return super.findList(sysProcessStepConf);
	}
	
	public Page<SysProcessStepConf> findPage(Page<SysProcessStepConf> page, SysProcessStepConf sysProcessStepConf) {
		return super.findPage(page, sysProcessStepConf);
	}
	
	@Transactional(readOnly = false)
	public void save(SysProcessStepConf sysProcessStepConf) {
		super.save(sysProcessStepConf);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysProcessStepConf sysProcessStepConf) {
		super.delete(sysProcessStepConf);
	}
	
}