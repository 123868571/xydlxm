package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfSpecialPaymentMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfSpecialPayment;

@Service
@Transactional(readOnly=true)
public class GzfSpecialPaymentService extends CrudService<GzfSpecialPaymentMapper, GzfSpecialPayment>{

	public GzfSpecialPayment get(String id){
		return super.get(id);
	}
	
	public GzfSpecialPayment get(GzfSpecialPayment entity){
		return super.get(entity);
	}
	
	public List<GzfSpecialPayment> findList(GzfSpecialPayment entity){
		return super.findList(entity);
	}
	
	@Transactional(readOnly=false)
	public void save(GzfSpecialPayment entity){
		super.saveNew(entity);
	}
	@Transactional(readOnly=false)
	public void delete(GzfSpecialPayment entity){
		super.delete(entity);
	}
}
