/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.entity.ComWorkorder;
import com.paopao.hzgzf.modules.dlj.dao.ComWorkorderDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseApplyForm;

/**
 * 工单分配表Service
 * @author pjs
 * @version 2016-06-02
 */
@Service
@Transactional(readOnly = true)
public class ComWorkorderService extends CrudService<ComWorkorderDao, ComWorkorder> {

	public ComWorkorder get(String id) {
		return super.get(id);
	}
	
	public List<ComWorkorder> findList(ComWorkorder comWorkorder) {
		return super.findList(comWorkorder);
	}
	public Page<ComWorkorder> findPage(Page<ComWorkorder> page, ComWorkorder comWorkorder) {
		return super.findPage(page, comWorkorder);
	}
	
	@Transactional(readOnly = false)
	public void save(ComWorkorder comWorkorder) {
		super.save(comWorkorder);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComWorkorder comWorkorder) {
		super.delete(comWorkorder);
	}
	
}