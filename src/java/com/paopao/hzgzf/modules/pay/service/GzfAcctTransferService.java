package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfAcctTransferMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransfer;

@Service
@Transactional(readOnly=true)
public class GzfAcctTransferService extends CrudService<GzfAcctTransferMapper, GzfAcctTransfer>{

	@Transactional(readOnly=false)
	public void save(GzfAcctTransfer entity){
		super.saveNew(entity);
	}
	
	public GzfAcctTransfer get(String id){
		return super.get(id);
	}
	
	public GzfAcctTransfer get(GzfAcctTransfer entity){
		return super.get(entity);
	}
	
	public Page<GzfAcctTransfer> findPage(Page<GzfAcctTransfer> page, GzfAcctTransfer entity){
		return super.findPage(page, entity);
	}
	
	public List<GzfAcctTransfer> findList(GzfAcctTransfer entity){
		return super.findList(entity);
	}
	
	@Transactional(readOnly=false)
	public void delete(GzfAcctTransfer entity){
		super.delete(entity);
	}
}
