/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfElectricDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfElectric;

/**
 * 电表录入Service
 * @author Hongjun
 * @version 2016-01-18
 */
@Service
@Transactional(readOnly = true)
public class GzfElectricService extends CrudService<GzfElectricDao, GzfElectric> {

    public GzfElectric get(String id) {
        return super.get(id);
    }

    public List<GzfElectric> findList(GzfElectric gzfElectric) {
        return super.findList(gzfElectric);
    }

    public Page<GzfElectric> findPage(Page<GzfElectric> page, GzfElectric gzfElectric) {
        return super.findPage(page, gzfElectric);
    }

    @Transactional(readOnly = false)
    public void save(GzfElectric gzfElectric) {
        super.save(gzfElectric);
    }

    @Transactional(readOnly = false)
    public void delete(GzfElectric gzfElectric) {
        super.delete(gzfElectric);
    }

    public List<GzfElectric> getByHouseId(String id) {
        return dao.getByHouseId(id);
    }

}