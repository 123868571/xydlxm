package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.AccountBankDao;
import com.paopao.hzgzf.modules.pay.entity.AccountBank;

@Service
@Transactional(readOnly=true)
public class AccountBankService extends CrudService<AccountBankDao, AccountBank>{

	public List<AccountBank> findList(AccountBank entity){
		return super.findList(entity);
	}
	
	public Page<AccountBank> findPage(Page<AccountBank> page, AccountBank entity){
		return super.findPage(page, entity);
	}
}
