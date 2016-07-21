/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.entity.ComRecord;
import com.paopao.hzgzf.modules.dlj.dao.ComRecordDao;

/**
 * 通用变更记录Service
 * @author zdk
 * @version 2016-07-14
 */
@Service
@Transactional(readOnly = true)
public class ComRecordService extends CrudService<ComRecordDao, ComRecord> {

	public ComRecord get(String id) {
		return super.get(id);
	}
	
	public List<ComRecord> findList(ComRecord comRecord) {
		return super.findList(comRecord);
	}
	
	public Page<ComRecord> findPage(Page<ComRecord> page, ComRecord comRecord) {
		return super.findPage(page, comRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(ComRecord comRecord) {
		super.save(comRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComRecord comRecord) {
		super.delete(comRecord);
	}
	
}