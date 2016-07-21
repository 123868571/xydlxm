/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfNaturalGasDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfNaturalGas;

/**
 * 天然气表录入Service
 * @author Hongjun
 * @version 2016-01-18
 */
@Service
@Transactional(readOnly = true)
public class GzfNaturalGasService extends CrudService<GzfNaturalGasDao, GzfNaturalGas> {

    public GzfNaturalGas get(String id) {
        return super.get(id);
    }

    public List<GzfNaturalGas> findList(GzfNaturalGas gzfNaturalGas) {
        return super.findList(gzfNaturalGas);
    }

    public Page<GzfNaturalGas> findPage(Page<GzfNaturalGas> page, GzfNaturalGas gzfNaturalGas) {
        return super.findPage(page, gzfNaturalGas);
    }

    @Transactional(readOnly = false)
    public void save(GzfNaturalGas gzfNaturalGas) {
        super.save(gzfNaturalGas);
    }

    @Transactional(readOnly = false)
    public void delete(GzfNaturalGas gzfNaturalGas) {
        super.delete(gzfNaturalGas);
    }

    public List<GzfNaturalGas> getByHouseId(String id) {
        return dao.getByHouseId(id);
    }

}