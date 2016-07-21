package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfPaymethodBalancetypeMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfPaymethodBalancetype;

@Service
@Transactional(readOnly=true)
public class GzfPaymethodBalancetypeService extends CrudService<GzfPaymethodBalancetypeMapper, GzfPaymethodBalancetype>{

	public GzfPaymethodBalancetype get(String id){
		return super.get(id);
	}
	
	public GzfPaymethodBalancetype get(GzfPaymethodBalancetype entity){
		return super.get(entity);
	}
	
	public List<GzfPaymethodBalancetype> findList(GzfPaymethodBalancetype entity){
		return super.findList(entity);
	}
	
	@Transactional(readOnly=false)
	public void save(GzfPaymethodBalancetype entity){
		super.saveNew(entity);
	}
	
	@Transactional(readOnly=false)
	public void delete(GzfPaymethodBalancetype entity){
		super.delete(entity);
	}
}
