package com.paopao.hzgzf.modules.pay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfBillingCycleMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfBillingCycle;

@Service
@Transactional(readOnly=true)
public class GzfBillingCycleService extends CrudService<GzfBillingCycleMapper, GzfBillingCycle>{

}
