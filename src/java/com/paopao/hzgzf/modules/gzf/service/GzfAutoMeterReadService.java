/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.entity.GzfAutoMeterRead;
import com.paopao.hzgzf.modules.gzf.dao.GzfAutoMeterReadDao;

/**
 * 自动抄表Service
 * @author songyahe
 * @version 2016-03-09
 */
@Service
@Transactional(readOnly = true)
public class GzfAutoMeterReadService extends CrudService<GzfAutoMeterReadDao, GzfAutoMeterRead> {

	public GzfAutoMeterRead get(String id) {
		return super.get(id);
	}
	
	public List<GzfAutoMeterRead> findList(GzfAutoMeterRead gzfAutoMeterRead) {
		return super.findList(gzfAutoMeterRead);
	}
	
	public Page<GzfAutoMeterRead> findPage(Page<GzfAutoMeterRead> page, GzfAutoMeterRead gzfAutoMeterRead) {
		return super.findPage(page, gzfAutoMeterRead);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfAutoMeterRead gzfAutoMeterRead) {
		super.save(gzfAutoMeterRead);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfAutoMeterRead gzfAutoMeterRead) {
		super.delete(gzfAutoMeterRead);
	}
	
}