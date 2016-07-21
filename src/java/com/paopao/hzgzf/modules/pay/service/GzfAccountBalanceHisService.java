package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfAccountBalanceHisMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalanceHis;

@Service
@Transactional(readOnly=true)
public class GzfAccountBalanceHisService extends CrudService<GzfAccountBalanceHisMapper, GzfAccountBalanceHis>{

	public GzfAccountBalanceHis get(String id){
		return super.get(id);
	}
	
	public GzfAccountBalanceHis get(GzfAccountBalanceHis entity){
		return super.get(entity);
	}
	
	public List<GzfAccountBalanceHis> findList(GzfAccountBalanceHis entity){
		return super.findList(entity);
	}
	
	public Page<GzfAccountBalanceHis> findPage(Page<GzfAccountBalanceHis> page, GzfAccountBalanceHis entity){
		return super.findPage(page, entity);
	}
	
	@Transactional(readOnly=false)
	public void delete(GzfAccountBalanceHis entity){
		super.delete(entity);
	}
	
	@Transactional(readOnly=false)
	public void save(GzfAccountBalanceHis entity){
		super.saveNew(entity);
	}
	
	public List<GzfAccountBalance> getGroupedBalanceByAccountId(String accountId){
		return dao.getGroupedBalanceByAccountId(accountId);
	}
}
