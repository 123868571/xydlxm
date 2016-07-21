/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfPalacesDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;

/**
 * 苑Service
 * @author Hongjun
 * @version 2015-12-20
 */
@Service
@Transactional(readOnly = true)
public class GzfPalacesService extends CrudService<GzfPalacesDao, GzfPalaces> {

	public GzfPalaces get(String id) {
		return super.get(id);
	}
	
	public GzfPalaces getByName(String name) {
        return dao.getByName(name);
    }
	
	public List<GzfPalaces> findList(GzfPalaces gzfPalaces) {
		return super.findList(gzfPalaces);
	}
	
	public Page<GzfPalaces> findPage(Page<GzfPalaces> page, GzfPalaces gzfPalaces) {
		return super.findPage(page, gzfPalaces);
	}
	
	@Transactional(readOnly = false)
	public void save(GzfPalaces gzfPalaces) {
		super.save(gzfPalaces);
	}
	
	@Transactional(readOnly = false)
	public void delete(GzfPalaces gzfPalaces) {
		super.delete(gzfPalaces);
	}
	
}