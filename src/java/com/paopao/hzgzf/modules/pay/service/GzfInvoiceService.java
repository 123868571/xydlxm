package com.paopao.hzgzf.modules.pay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfInvoiceMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfInvoice;

@Service
@Transactional(readOnly=true)
public class GzfInvoiceService extends CrudService<GzfInvoiceMapper, GzfInvoice>{

}
