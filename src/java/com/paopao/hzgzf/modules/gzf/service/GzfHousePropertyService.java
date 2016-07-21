/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfHousePropertyDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseProperty;

/**
 * 房屋属性Service
 * @author Hongjun
 * @version 2016-01-02
 */
@Service
@Transactional(readOnly = true)
public class GzfHousePropertyService extends CrudService<GzfHousePropertyDao, GzfHouseProperty> {

    public GzfHouseProperty get(String id) {
        return super.get(id);
    }

    public List<GzfHouseProperty> findList(GzfHouseProperty gzfHouseProperty) {
        return super.findList(gzfHouseProperty);
    }

    public Page<GzfHouseProperty> findPage(Page<GzfHouseProperty> page,
                                           GzfHouseProperty gzfHouseProperty) {
        return super.findPage(page, gzfHouseProperty);
    }

    @Transactional(readOnly = false)
    public void save(GzfHouseProperty gzfHouseProperty) {
        super.save(gzfHouseProperty);
    }

    @Transactional(readOnly = false)
    public void delete(GzfHouseProperty gzfHouseProperty) {
        super.delete(gzfHouseProperty);
    }

    public GzfHouseProperty getByName(String name) {
        return dao.getByName(name);
    }

}