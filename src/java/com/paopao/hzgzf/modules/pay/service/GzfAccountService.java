package com.paopao.hzgzf.modules.pay.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.dao.GzfAccountMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountHis;

/**
 * 帐户service
 *
 * @author songyh
 */
@Service
@Transactional(readOnly = true)
public class GzfAccountService extends CrudService<GzfAccountMapper, GzfAccount> {

    @Autowired
    GzfAccountMapper gzfAccountMapper;
    @Autowired
    GzfAccountHisService gzfAccountHisService;

    public Page<GzfAccount> findPageWithJoin(Page<GzfAccount> page, GzfAccount entity) {
        entity.setPage(page);
        page.setList(gzfAccountMapper.findListJoin(entity));
        return page;
    }

    public GzfAccount get(String id) {
        return super.get(id);
    }

    public GzfAccount get(GzfAccount entity) {
        return super.get(entity);
    }

    public List<GzfAccount> findList(GzfAccount entity) {
        return super.findList(entity);
    }

    public Page<GzfAccount> findAllList(GzfAccount gzfAccount, Page<GzfAccount> page) {
        return findPageWithJoin(page, gzfAccount);
    }

    public Page<GzfAccount> findPage(Page<GzfAccount> page, GzfAccount entity) {
        return super.findPage(page, entity);
    }
    //
    //	public Page<GzfAccount> findPageByExample(Page<GzfAccount> page, GzfAccountExample example){
    //		example.setPage(page);
    //		List<GzfAccount> list = dao.selectByExample(example);
    //		page.setList(list);
    //		return page;
    //	}

    @Transactional(readOnly = false)
    public void delete(GzfAccount entity) {
    	entity.setDelFlag(GzfAccount.DEL_FLAG_DELETE);
    	insertAccountHis(entity);
        super.delete(entity);
    }

    @Transactional(readOnly = false)
    public void save(GzfAccount entity) {
    	if(StringUtils.isNoneBlank(entity.getId())){
    		insertAccountHis(entity);
    	}
        super.saveNew(entity);
    }
    
    @Transactional(readOnly=false)
    private void insertAccountHis(GzfAccount entity){
    	GzfAccount oldEntity = get(entity.getId());
		if (oldEntity != null) {
			GzfAccountHis accountHis = new GzfAccountHis();
			accountHis.setAccountId(entity.getId());
			accountHis.setAccountCredit(entity.getAccountCredit());
			accountHis.setAccountName(entity.getAccountName());
			accountHis.setAccountType(entity.getAccountType());
			accountHis.setCreateDate(entity.getCreateDate());
			accountHis.setCustId(entity.getCustId());
			accountHis.setCycleEndDay(entity.getCycleEndDay());
			accountHis.setDelFlag(entity.getDelFlag());
			accountHis.setOpId(entity.getOpId());
			accountHis.setPayMethod(entity.getPayMethod());
			accountHis.setPhoneNo(entity.getPhoneNo());
			accountHis.setRemarks(entity.getRemarks());
			accountHis.setState(entity.getState());
			accountHis.setUpdateDate(entity.getUpdateDate());
			accountHis.setCardid(entity.getCardid());
//			BeanUtils.copyProperties(accountHis, entity);
//			accountHis.setAccountId(oldEntity.getId());
//			accountHis.setId("");
			gzfAccountHisService.save(accountHis);
		}
    }

    private List<GzfAccount> findListJoin(GzfAccount entity){
    	return dao.findListJoin(entity);
    }
    
    //建立人员住房关系时生成帐户信息
    @Transactional(readOnly = false)
    public GzfAccount generateAccount(GzfHousePerson housePerson) {
        GzfAccount account = new GzfAccount();
        account.setCustId(housePerson.getId());
        account.setAccountName(housePerson.getGzfHouseholdInfo().getName());
        account.setAccountType(PaymentConst.ACCT_TYPE.PERSONAL);
        account.setPayMethod(PaymentConst.PAY_METHOD.CASH);
        account.setState(PaymentConst.YES_OR_NO.YES);
        account.setPhoneNo(housePerson.getGzfHouseholdInfo().getPhone());
        account.setCardid(housePerson.getGzfHouseholdInfo().getCardid());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(housePerson.getEffectiveDate());
        account.setCycleEndDay(calendar.get(Calendar.DAY_OF_MONTH));
        save(account);
        return account;
    }
	
}
