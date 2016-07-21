package com.paopao.hzgzf.modules.pay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.pay.dao.GzfBankCardMapper;
import com.paopao.hzgzf.modules.pay.entity.AccountBank;
import com.paopao.hzgzf.modules.pay.entity.GzfBankCard;

@Service
@Transactional(readOnly=true)
public class GzfBankCardService extends CrudService<GzfBankCardMapper, GzfBankCard>{

	public GzfBankCard get(String id){
		return super.get(id);
	}
	
	public GzfBankCard get(GzfBankCard entity){
		return super.get(entity);
	}
	
	public List<GzfBankCard> findList(GzfBankCard entity){
		return super.findList(entity);
	}
	
	public Page<GzfBankCard> findPage(Page<GzfBankCard> page, GzfBankCard entity){
		return super.findPage(page, entity);
	}
	
	@Transactional(readOnly=false)
	public void save(GzfBankCard entity){
		super.saveNew(entity);
	}
	
	@Transactional(readOnly=false)
	public void saveList(List<GzfBankCard> list){
		if (list == null || list.size() == 0) {
			return;
		}
		for(int i = 0; i < list.size(); i++){
			save(list.get(i));
		}
	}
	
	@Transactional(readOnly=false)
	public void delete(GzfBankCard entity){
		super.delete(entity);
	}
	
	@Transactional(readOnly=false)
	public void deleteByAccountAndCardNo(GzfBankCard entity){
		dao.deleteByAccountAndCardNo(entity);
	}
	
	public List<GzfBankCard> findListByAccountBank(AccountBank accountBank){
		GzfBankCard temp = new GzfBankCard();
		if (accountBank != null) {
			temp.setAccountId(accountBank.getId());
			temp.setBankId(accountBank.getBankId());
			temp.setCardNo(accountBank.getCardNo());
		}
		return findList(temp);
	}
	
	@Transactional(readOnly=false)
	public boolean modify(AccountBank accountBank){
		String oldAcctItemType = accountBank.getAcctItemType();
		String[] oldAcctItemArr = oldAcctItemType.split(",");;
		List<String> acctItemTypes = accountBank.getAcctItemTypeList();
		List<String> delList = new ArrayList<String>();
		//删除的
		for(int i = 0; i < oldAcctItemArr.length; i++){
			boolean delFlag = true;
			for(int j = 0; j < acctItemTypes.size(); j++){
				if (oldAcctItemArr[i].equals(acctItemTypes.get(j))) {
					delFlag = false;
					break;
				}
			}
			if (delFlag) {
				delList.add(oldAcctItemArr[i]);
			}
		}
		//增加的
		List<String> addList = new ArrayList<String>();
		for(int i = 0; i < acctItemTypes.size(); i++){
			boolean addFlag = true;
			for(int j = 0; j < oldAcctItemArr.length; j++){
				if (oldAcctItemArr[j].equals(acctItemTypes.get(i))) {
					addFlag = false;
					break;
				}
			}
			if (addFlag) {
				addList.add(acctItemTypes.get(i));
			}
		}
		if (delList.isEmpty() && addList.isEmpty()) {
			return false;
		}
		for(int i = 0; i < addList.size(); i++){
			if (StringUtils.isBlank(addList.get(i))) {
				continue;
			}
			GzfBankCard bankCard = new GzfBankCard();
			bankCard.setAccountId(accountBank.getId());
			bankCard.setBankName(accountBank.getBankName());
			bankCard.setBankId(accountBank.getBankId());
			bankCard.setAcctItemTypeId(Integer.parseInt(addList.get(i)));
			bankCard.setCardNo(accountBank.getCardNo());
			save(bankCard);
		}
		for(int i = 0; i < delList.size(); i++){
			if (StringUtils.isBlank(delList.get(i))) {
				continue;
			}
			GzfBankCard bankCard = new GzfBankCard();
			bankCard.setAccountId(accountBank.getId());
			bankCard.setBankId(accountBank.getBankId());
			bankCard.setAcctItemTypeId(Integer.parseInt(delList.get(i)));
			bankCard.setCardNo(accountBank.getCardNo());
			deleteByAccountAndCardNo(bankCard);
		}
		return true;
	}
}
