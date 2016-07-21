/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfPaymentStandardDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;

/**
 * 缴费标准Service
 * @author Hongjun
 * @version 2016-01-02
 */
@Service
@Transactional(readOnly = true)
public class GzfPaymentStandardService extends
                                      CrudService<GzfPaymentStandardDao, GzfPaymentStandard> {

    public GzfPaymentStandard get(String id) {
        return super.get(id);
    }

    public List<GzfPaymentStandard> findList(GzfPaymentStandard gzfPaymentStandard) {
        return super.findList(gzfPaymentStandard);
    }

    public Page<GzfPaymentStandard> findPage(Page<GzfPaymentStandard> page,
                                             GzfPaymentStandard gzfPaymentStandard) {
        return super.findPage(page, gzfPaymentStandard);
    }

    @Transactional(readOnly = false)
    public void save(GzfPaymentStandard gzfPaymentStandard) {
        super.save(gzfPaymentStandard);
    }

    @Transactional(readOnly = false)
    public void delete(GzfPaymentStandard gzfPaymentStandard) {
        super.delete(gzfPaymentStandard);
    }

    public GzfPaymentStandard getByName(String name) {
        return dao.getByName(name);
    }

}