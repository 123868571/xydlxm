/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfWaterMeterDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfWaterMeter;

/**
 * 水表Service
 * @author Hongjun
 * @version 2016-01-17
 */
@Service
@Transactional(readOnly = true)
public class GzfWaterMeterService extends CrudService<GzfWaterMeterDao, GzfWaterMeter> {

    public GzfWaterMeter get(String id) {
        return super.get(id);
    }

    public List<GzfWaterMeter> getByHouseId(String id) {
        return dao.getByHouseId(id);
    }

    public List<GzfWaterMeter> findList(GzfWaterMeter gzfWaterMeter) {
        return super.findList(gzfWaterMeter);
    }

    public Page<GzfWaterMeter> findPage(Page<GzfWaterMeter> page, GzfWaterMeter gzfWaterMeter) {
        return super.findPage(page, gzfWaterMeter);
    }

    @Transactional(readOnly = false)
    public void save(GzfWaterMeter gzfWaterMeter) {
        super.save(gzfWaterMeter);
    }

    @Transactional(readOnly = false)
    public void delete(GzfWaterMeter gzfWaterMeter) {
        super.delete(gzfWaterMeter);
    }

}