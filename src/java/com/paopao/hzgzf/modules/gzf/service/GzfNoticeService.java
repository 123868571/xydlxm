/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfNotice;
import com.paopao.hzgzf.modules.gzf.dao.GzfNoticeDao;

/**
 * 通知发布Service
 * @author Hongjun
 * @version 2016-05-05
 */
@Service
@Transactional(readOnly = true)
public class GzfNoticeService extends CrudService<GzfNoticeDao, GzfNotice> {

	public GzfNotice get(String id) {
		return super.get(id);
	}
	
	public List<GzfNotice> findList(GzfNotice gzfNotice) {
		return super.findList(gzfNotice);
	}
	
	public Page<GzfNotice> findPage(Page<GzfNotice> page, GzfNotice gzfNotice) {
		return super.findPage(page, gzfNotice);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfNotice gzfNotice) {
		super.save(gzfNotice);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfNotice gzfNotice) {
		super.delete(gzfNotice);
	}
	
}