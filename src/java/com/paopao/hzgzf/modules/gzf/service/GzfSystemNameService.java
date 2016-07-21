/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfSystemName;
import com.paopao.hzgzf.modules.gzf.dao.GzfSystemNameDao;

/**
 * 系统名称Service
 * @author Hongjun
 * @version 2016-01-02
 */
@Service
@Transactional(readOnly = true)
public class GzfSystemNameService extends CrudService<GzfSystemNameDao, GzfSystemName> {

	public GzfSystemName get(String id) {
		return super.get(id);
	}
	
	public List<GzfSystemName> findList(GzfSystemName gzfSystemName) {
		return super.findList(gzfSystemName);
	}
	
	public Page<GzfSystemName> findPage(Page<GzfSystemName> page, GzfSystemName gzfSystemName) {
		return super.findPage(page, gzfSystemName);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfSystemName gzfSystemName) {
		super.save(gzfSystemName);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfSystemName gzfSystemName) {
		super.delete(gzfSystemName);
	}
	
}