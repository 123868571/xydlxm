package com.paopao.hzgzf.modules.pay.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.pay.dao.GzfAccountBalanceMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalanceHis;

@Service
@Transactional(readOnly=true)
public class GzfAccountBalanceService extends CrudService<GzfAccountBalanceMapper, GzfAccountBalance>{

	@Autowired
	GzfAccountBalanceHisService balanceHisService;
	
	public GzfAccountBalance get(String id){
		return super.get(id);
	}
	
	public GzfAccountBalance get(GzfAccountBalance entity){
		return super.get(entity);
	}
	
	public List<GzfAccountBalance> findList(GzfAccountBalance entity){
		return super.findList(entity);
	}
	
	public Page<GzfAccountBalance> findPage(Page<GzfAccountBalance> page, GzfAccountBalance entity){
		return super.findPage(page, entity);
	}
	
	@Transactional(readOnly=false)
	public void delete(GzfAccountBalance entity){
		super.delete(entity);
	}
	
	@Transactional(readOnly=false)
	public void deleteAndSaveHis(GzfAccountBalance entity){
		GzfAccountBalanceHis balanceHis = new GzfAccountBalanceHis();
		//BeanMapper.copy(entity, balanceHis);
		balanceHis.setId(entity.getId());
		balanceHis.setAccountId(entity.getAccountId());
		balanceHis.setAcctItemTypeId(entity.getAcctItemTypeId());
		balanceHis.setBalance(entity.getBalance());
		balanceHis.setBalanceTypeId(entity.getBalanceTypeId());
		balanceHis.setInitBalance(entity.getInitBalance());
		balanceHis.setPaymentId(entity.getPaymentId());
		balanceHis.setRemarks(entity.getRemarks());
		balanceHis.setSpePaymentId(entity.getSpePaymentId());
		balanceHis.setState(entity.getState());
		balanceHis.setEffectDate(entity.getEffectDate());
		balanceHis.setExpireDate(entity.getExpireDate());
		balanceHis.setIsNewRecord(true);
		balanceHisService.save(balanceHis);
		entity.setDelFlag(GzfAccountBalance.DEL_FLAG_DELETE);
		save(entity);
//		super.delete(entity);
	}
	
	@Transactional(readOnly=false)
	public void deleteList(List<GzfAccountBalance> list, boolean isSaveHis){
		if (list == null || list.size() == 0) {
			return;
		}
		for(int i = 0; i < list.size(); i++){
			if (isSaveHis) {
				deleteAndSaveHis(list.get(i));
			}else{
				delete(list.get(i));
			}
		}
	}
	
	@Transactional(readOnly=false)
	public void save(GzfAccountBalance entity){
		super.saveNew(entity);
	}
	
	@Transactional(readOnly=false)
	public void saveList(List<GzfAccountBalance> list){
		if (list == null || list.size() == 0) {
			return;
		}
		for(int i = 0; i < list.size(); i++){
			save(list.get(i));
		}
	}
	
	public List<GzfAccountBalance> getGroupedBalanceByAccountId(String accountId){
		return dao.getGroupedBalanceByAccountId(accountId);
	}
	
	public GzfAccountBalance getSumBalanceByAcctIdAndSpecId(GzfAccountBalance entity){
		return dao.getSumBalanceByAcctIdAndSpecId(entity);
	}
	
	public GzfAccountBalance getSumBalanceByAcctIdAndAcctItemTypeId(GzfAccountBalance entity){
		return dao.getSumBalanceByAcctIdAndAcctItemTypeId(entity);
	}
	
	//获取费用到期日期
	public Date getFeeExpireDate(GzfHousePerson gzfHousePerson, int spePaymentId){
		GzfAccountBalance balance = new GzfAccountBalance();
		balance.setAccountId(gzfHousePerson.getAccountId());
		balance.setSpePaymentId(spePaymentId);
		List<GzfAccountBalance> list = findList(balance);
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<GzfAccountBalance>(){
				@Override
				public int compare(GzfAccountBalance o1, GzfAccountBalance o2) {
					if (o1.getExpireDate() == null) {
						return -1;
					}else if (o2.getExpireDate() == null) {
						return 1;
					}
					if (o1.getExpireDate().before(o2.getExpireDate())) {
						return -1;
					}else {
						return 1;
					}
				}
				
			});
			balance = list.get(list.size() -1);
			return balance.getExpireDate();
		}else {
			GzfAccountBalanceHis balanceHis = new GzfAccountBalanceHis();
			balanceHis.setAccountId(gzfHousePerson.getAccountId());
			balanceHis.setSpePaymentId(spePaymentId);
			List<GzfAccountBalanceHis> balanceHisList = balanceHisService.findList(balanceHis);
			if (balanceHisList != null && balanceHisList.size() > 0) {
				Collections.sort(balanceHisList, new Comparator<GzfAccountBalanceHis>(){
					@Override
					public int compare(GzfAccountBalanceHis o1, GzfAccountBalanceHis o2) {
						if (o1.getExpireDate().before(o2.getExpireDate())) {
							return -1;
						}else {
							return 1;
						}
					}
					
				});
				balanceHis = balanceHisList.get(balanceHisList.size() - 1);
				return balanceHis.getExpireDate();
			}else{
				return DateUtils.getPreLastDateOfDay(gzfHousePerson.getEffectiveDate());
			}
		}
	}
	
	
}
