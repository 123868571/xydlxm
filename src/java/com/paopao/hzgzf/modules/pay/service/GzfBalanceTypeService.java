package com.paopao.hzgzf.modules.pay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfBalanceTypeMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceType;

@Service
@Transactional(readOnly=true)
public class GzfBalanceTypeService extends CrudService<GzfBalanceTypeMapper, GzfBalanceType>{

}
