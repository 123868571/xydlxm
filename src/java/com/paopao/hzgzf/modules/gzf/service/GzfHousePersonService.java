/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfHousePersonDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;

/**
 * 房屋与人员Service
 * @author Hongjun
 * @version 2016-01-08
 */
@Service
@Transactional(readOnly = true)
public class GzfHousePersonService extends CrudService<GzfHousePersonDao, GzfHousePerson> {

    @Autowired
    private GzfHousePersonDao gzfHousePersonDao;

    public GzfHousePerson get(String id) {
        return super.get(id);
    }

    public GzfHousePerson getByHistory(String id) {
        return dao.getByHistory(id);
    }

    public GzfHousePerson query(GzfHousePerson gzfHousePerson) {
        return gzfHousePersonDao.query(gzfHousePerson);
    }

    public GzfHousePerson query1(GzfHousePerson gzfHousePerson) {
        return gzfHousePersonDao.query1(gzfHousePerson);
    }

    public List<GzfHousePerson> findList(GzfHousePerson gzfHousePerson) {
        return super.findList(gzfHousePerson);
    }

    public Page<GzfHousePerson> findPage(Page<GzfHousePerson> page, GzfHousePerson gzfHousePerson) {
        return super.findPage(page, gzfHousePerson);
    }

    @Transactional(readOnly = false)
    public void save(GzfHousePerson entity) {
        super.save(entity);
    }

    @Transactional(readOnly = false)
    public void delete(GzfHousePerson gzfHousePerson) {
        super.delete(gzfHousePerson);
    }

}