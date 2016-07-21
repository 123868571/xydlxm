package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfBalanceRecordMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceRecord;

@Service
@Transactional(readOnly=true)
public class GzfBalanceRecordService extends CrudService<GzfBalanceRecordMapper, GzfBalanceRecord>{

	@Transactional(readOnly=false)
	public void save(GzfBalanceRecord entity){
		super.saveNew(entity);
	}
	
	@Transactional(readOnly=false)
	public void saveList(List<GzfBalanceRecord> entityList){
		if (entityList == null || entityList.size() == 0) {
			return;
		}
		for(int i = 0; i < entityList.size(); i++){
			save(entityList.get(i));
		}
	}
	
	public List<GzfBalanceRecord> findList(GzfBalanceRecord entity){
		return super.findList(entity);
	}
	
	public Page<GzfBalanceRecord> findPage(Page<GzfBalanceRecord> page, GzfBalanceRecord entity){
		return super.findPage(page, entity);
	}
	
	public GzfBalanceRecord get(GzfBalanceRecord entity){
		return super.get(entity);
	}
	
	public GzfBalanceRecord get(String id){
		return super.get(id);
	}
	
	@Transactional(readOnly=false)
	public void delete(GzfBalanceRecord entity){
		super.delete(entity);
	}
	
	//根据帐户查询帐本支出明细列表
	public List<GzfBalanceRecord> getListByAccount(GzfAccount account){
		GzfBalanceRecord entity = new GzfBalanceRecord();
		entity.setAccountId(account.getId());
		return findList(entity);
	}
}
