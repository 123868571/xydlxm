package com.paopao.hzgzf.modules.pay.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.gzf.entity.GzfElectric;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfNaturalGas;
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;
import com.paopao.hzgzf.modules.gzf.entity.GzfWaterMeter;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.dao.GzfAcctItemMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItem;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemHis;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemType;

@Service
@Transactional(readOnly = true)
public class GzfAcctItemService extends CrudService<GzfAcctItemMapper, GzfAcctItem> {
    @Autowired
    GzfPaymentStandardService paymentStandardService;
    @Autowired
    GzfHouseholdInfoService householdInfoService;
    @Autowired
    GzfHousePersonService housePersonService;
    @Autowired
    GzfAcctItemTypeService acctItemTypeService;
    @Autowired
    GzfAccountService accountService;
    @Autowired
    GzfHouseInfoService houseInfoService;
    @Autowired
    GzfAcctItemHisService acctItemHisService;

    public GzfAcctItem get(String id) {
        return super.get(id);
    }

    public GzfAcctItem get(GzfAcctItem entity) {
        return super.get(entity);
    }

    public List<GzfAcctItem> findList(GzfAcctItem entity) {
        return super.findList(entity);
    }

    public List<GzfAcctItem> getSumMoney(GzfAcctItem entity) {
        return dao.getSumMoney(entity);
    }

    @Transactional(readOnly = false)
    public void delete(GzfAcctItem entity) {
        super.delete(entity);
    }

    @Transactional(readOnly = false)
    public void deleteAndSaveHis(GzfAcctItem entity) {
        GzfAcctItemHis acctItemHis = new GzfAcctItemHis();
        //BeanMapper.copy(entity, acctItemHis);
        acctItemHis.setId(entity.getId());
        acctItemHis.setAccountId(entity.getAccountId());
        acctItemHis.setAcctItemTypeId(entity.getAcctItemTypeId());
        acctItemHis.setBillingCycleId(entity.getBillingCycleId());
        acctItemHis.setFactAmount(entity.getFactAmount());
        acctItemHis.setReceAmount(entity.getReceAmount());
        acctItemHis.setWriteOffPri(entity.getWriteOffPri());
        acctItemHis.setRemarks(entity.getRemarks());
        acctItemHis.setIsNewRecord(true);
        acctItemHisService.save(acctItemHis);
        super.delete(entity);
    }

    @Transactional(readOnly = false)
    public void save(GzfAcctItem entity) {
        super.saveNew(entity);
    }

    //根据帐户查询帐单信息
    public List<GzfAcctItem> getListByAccount(GzfAccount account) {
        GzfAcctItem entity = new GzfAcctItem();
        entity.setAccountId(account.getId());
        return findList(entity);
    }

    @Transactional(readOnly = false)
    public void saveList(List<GzfAcctItem> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            save(list.get(i));
        }
    }

    @Transactional(readOnly = false)
    public void deleteList(List<GzfAcctItem> list, boolean isSaveHis) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (isSaveHis) {
                deleteAndSaveHis(list.get(i));
            } else {
                delete(list.get(i));
            }
        }
    }

    //生成物业费帐单
    @Transactional(readOnly = false)
    public void generateManagementFeeAcctItem(GzfAccount account) throws Exception {
        if (account == null) {
            return;
        }
        int cycleEndDay = account.getCycleEndDay();
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (cycleEndDay != dayOfMonth && !DateUtils.isLastDayOfMonth()) {//没到出帐单日，不出帐
            return;
        }
        //住房人员关系
        GzfHousePerson housePerson = housePersonService.get(account.getCustId());
        if (housePerson == null) {//找不到租赁关系，则不生成帐单
            return;
        }
        //如果是入住当天，则不生成帐单
        if (DateUtils.formatDate(housePerson.getEffectiveDate())
          .equals(DateUtils.formatDate(calendar.getTime()))) {
            return;
        }
        GzfHouseholdInfo householdInfo = householdInfoService.get(housePerson
          .getGzfHouseholdInfoId());
        if (householdInfo == null) {
            throw new Exception("根据住户编号" + housePerson.getGzfHouseholdInfoId() + "找不到人员信息！");
        }
        GzfHouseInfo houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
        if (houseInfo == null) {
            throw new Exception("根据房屋编号" + housePerson.getGzfHouseInfoId() + "找不到房屋信息！");
        }
        GzfPaymentStandard paymentStandard = paymentStandardService.get(householdInfo
          .getGzfPaymentStandardId());
        if (paymentStandard == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到缴费标准！");
        }
//        double managementFee = paymentStandard.getManagementFee();
//        double fee = managementFee * 100 * houseInfo.getUseArea();

        Date now = new Date();
        Date manageFeeExpireDate = getAcctItemStartDate(housePerson, PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
        //判断本帐期的帐单是否已经生成,如果已经生成，不再重复生成，防止重启主机时重复生成帐单
        int billCycle = Integer.parseInt(DateUtils.formatDate(manageFeeExpireDate, "yyyyMM"));
        GzfAcctItem temp = new GzfAcctItem();
        temp.setAccountId(account.getId());
        temp.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
        temp.setBillingCycleId(billCycle);
        List<GzfAcctItem> oldList = findList(temp);
        if (oldList != null && oldList.size() > 0) {
			return;
		}
        
        double fee = calculateFee(housePerson, manageFeeExpireDate, now, PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
        if (fee == 0) {
            return;
        }
        
        GzfAcctItem acctItem = new GzfAcctItem();
        acctItem.setAccountId(account.getId());
        acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
        acctItem.setBeginDate(DateUtils.getBeginDateOfDay(manageFeeExpireDate));
        acctItem.setEndDate(DateUtils.getPreLastDateOfDay(now));
        acctItem.setReceAmount(fee);
        acctItem.setFactAmount(new Double(0));
        acctItem.setWriteOffPri(0);
        acctItem.setBillingCycleId(billCycle);
        GzfAcctItemType acctItemType = acctItemTypeService.get(String.valueOf(acctItem
          .getAcctItemTypeId()));
        if (acctItemType != null) {
            acctItem.setWriteOffPri(acctItemType.getBillPriority());
        }
        save(acctItem);
    }

    //生成能耗费帐单
    @Transactional(readOnly = false)
    public void generateConsumptionAcctItem(GzfAccount account) throws Exception {
        if (account == null) {
            return;
        }
        int cycleEndDay = account.getCycleEndDay();
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (cycleEndDay != dayOfMonth && !DateUtils.isLastDayOfMonth()) {//没到出帐单日，不出帐
            return;
        }
        //住房人员关系
        GzfHousePerson housePerson = housePersonService.get(account.getCustId());
        if (housePerson == null) {//找不到租赁关系，则不生成帐单
            return;
        }
        //如果是入住当天，则不生成帐单
        if (DateUtils.formatDate(housePerson.getEffectiveDate())
          .equals(DateUtils.formatDate(calendar.getTime()))) {
            return;
        }
        GzfHouseholdInfo householdInfo = householdInfoService.get(housePerson
          .getGzfHouseholdInfoId());
        if (householdInfo == null) {
            throw new Exception("根据住户编号" + housePerson.getGzfHouseholdInfoId() + "找不到人员信息！");
        }
        GzfHouseInfo houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
        if (houseInfo == null) {
            throw new Exception("根据房屋编号" + housePerson.getGzfHouseInfoId() + "找不到房屋信息！");
        }
        GzfPaymentStandard paymentStandard = paymentStandardService.get(householdInfo
          .getGzfPaymentStandardId());
        if (paymentStandard == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到缴费标准！");
        }
//        double managementFee = paymentStandard.getManagementFee();
//        double fee = managementFee * 100 * houseInfo.getUseArea();

        Date now = new Date();
        Date consumptionFeeExpireDate = getAcctItemStartDate(housePerson, PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
        //判断本帐期的帐单是否已经生成,如果已经生成，不再重复生成，防止重启主机时重复生成帐单
        int billCycle = Integer.parseInt(DateUtils.formatDate(consumptionFeeExpireDate, "yyyyMM"));
        GzfAcctItem temp = new GzfAcctItem();
        temp.setAccountId(account.getId());
        temp.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
        temp.setBillingCycleId(billCycle);
        List<GzfAcctItem> oldList = findList(temp);
        if (oldList != null && oldList.size() > 0) {
			return;
		}
        
        double fee = calculateFee(housePerson, consumptionFeeExpireDate, now, PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
        if (fee == 0) {
            return;
        }
        
        GzfAcctItem acctItem = new GzfAcctItem();
        acctItem.setAccountId(account.getId());
        acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
        acctItem.setBeginDate(DateUtils.getBeginDateOfDay(consumptionFeeExpireDate));
        acctItem.setEndDate(DateUtils.getPreLastDateOfDay(now));
        acctItem.setReceAmount(fee);
        acctItem.setFactAmount(new Double(0));
        acctItem.setWriteOffPri(0);
        acctItem.setBillingCycleId(billCycle);
        GzfAcctItemType acctItemType = acctItemTypeService.get(String.valueOf(acctItem
          .getAcctItemTypeId()));
        if (acctItemType != null) {
            acctItem.setWriteOffPri(acctItemType.getBillPriority());
        }
        save(acctItem);
    }

    //生成单个帐户房租帐单
    @Transactional(readOnly = false)
    public void generateHouseRentAcctItem(GzfAccount account) throws Exception {
        if (account == null) {
            return;
        }
        int cycleEndDay = account.getCycleEndDay();
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (cycleEndDay != dayOfMonth && !DateUtils.isLastDayOfMonth()) {//没到出帐单日，不出帐
            return;
        }
        //住房人员关系
        GzfHousePerson housePerson = housePersonService.get(account.getCustId());
        if (housePerson == null) {//找不到租赁关系，则不生成帐单
            return;
        }
        //如果是入住当天，则不生成帐单
        if (DateUtils.formatDate(housePerson.getEffectiveDate())
          .equals(DateUtils.formatDate(calendar.getTime()))) {
            return;
        }
        GzfHouseholdInfo householdInfo = householdInfoService.get(housePerson
          .getGzfHouseholdInfoId());
        if (householdInfo == null) {
            throw new Exception("根据住户编号" + housePerson.getGzfHouseholdInfoId() + "找不到人员信息！");
        }
        GzfHouseInfo houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
        if (houseInfo == null) {
            throw new Exception("根据房屋编号" + housePerson.getGzfHouseInfoId() + "找不到房屋信息！");
        }
        GzfPaymentStandard paymentStandard = paymentStandardService.get(householdInfo
          .getGzfPaymentStandardId());
        if (paymentStandard == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到缴费标准！");
        }
//        double rentUnitPrice = paymentStandard.getRentUnitPrice();
//        double fee = rentUnitPrice * 100 * houseInfo.getUseArea();
        Date now = new Date();
        Date houseRentExpireDate = getAcctItemStartDate(housePerson, PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
        //判断本帐期的帐单是否已经生成,如果已经生成，不再重复生成，防止重启主机时重复生成帐单
        int billCycle = Integer.parseInt(DateUtils.formatDate(houseRentExpireDate, "yyyyMM"));
        GzfAcctItem temp = new GzfAcctItem();
        temp.setAccountId(account.getId());
        temp.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
        temp.setBillingCycleId(billCycle);
        List<GzfAcctItem> oldList = findList(temp);
        if (oldList != null && oldList.size() > 0) {
			return;
		}
        
        double fee = calculateFee(housePerson, houseRentExpireDate, now, PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
        if (fee == 0) {
            return;
        }

        GzfAcctItem acctItem = new GzfAcctItem();
        acctItem.setAccountId(account.getId());
        acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
        acctItem.setBeginDate(DateUtils.getBeginDateOfDay(houseRentExpireDate));
        acctItem.setEndDate(DateUtils.getPreLastDateOfDay(now));
        acctItem.setReceAmount(fee);
        acctItem.setFactAmount(new Double(0));
        acctItem.setWriteOffPri(0);
        acctItem.setBillingCycleId(billCycle);
        GzfAcctItemType acctItemType = acctItemTypeService.get(String.valueOf(acctItem
          .getAcctItemTypeId()));
        if (acctItemType != null) {
            acctItem.setWriteOffPri(acctItemType.getBillPriority());
        }
        save(acctItem);
    }

    //生成电费帐单
    @Transactional(readOnly = false)
    public void generateElectricAcctItem(GzfElectric last, GzfElectric current) throws Exception {
        if (last == null) {//不存在上次录入记录，说明是第一次录入，此时不生成帐单
            return;
        }
        if (current == null) {
            return;
        }
        if (current.getNum() == null) {
            throw new Exception("当前电表度数不能为空！");
        }
        double amount = current.getNum() - last.getNum();
        if (amount == 0) {
            return;
        }
        String houseHoldInfoId = current.getGzfHouseholdInfoId();
        GzfHouseholdInfo householdInfo = householdInfoService.get(houseHoldInfoId);
        if (householdInfo == null) {
            throw new Exception("根据住户" + houseHoldInfoId + "找不到住房信息！");
        }
        GzfHousePerson housePerson = householdInfo.getGzfHousePerson();
        if (housePerson == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到租赁关系！");
        }
        String accountId = housePerson.getAccountId();
        GzfPaymentStandard paymentStandard = paymentStandardService.get(householdInfo
          .getGzfPaymentStandardId());
        if (paymentStandard == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到缴费标准！");
        }
        double electricityFee = paymentStandard.getElectricityFee();
        double fee = electricityFee * 100 * amount;

        GzfAcctItem acctItem = new GzfAcctItem();
        acctItem.setAccountId(accountId);
        acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY);
        Date currentDate = new Date();
        if (current.getTime() != null) {
            currentDate = current.getTime();
        }
        acctItem.setBeginDate(currentDate);
        if (last != null) {
            acctItem.setBeginDate(last.getTime());
        }
        acctItem.setEndDate(currentDate);
        acctItem.setReceAmount(fee);
        acctItem.setFactAmount(new Double(0));
        acctItem.setRemarks(last.getNum() + "-" + current.getNum());
        acctItem.setWriteOffPri(0);
        GzfAcctItemType acctItemType = acctItemTypeService.get(String.valueOf(acctItem
          .getAcctItemTypeId()));
        if (acctItemType != null) {
            acctItem.setWriteOffPri(acctItemType.getBillPriority());
        }
        save(acctItem);
    }

    //生成水费帐单
    @Transactional(readOnly = false)
    public void generateWaterAcctItem(GzfWaterMeter last, GzfWaterMeter current) throws Exception {
        if (last == null) {//不存在上次录入记录，说明是第一次录入，此时不生成帐单
            return;
        }
        if (current == null) {
            return;
        }
        if (current.getNum() == null) {
            throw new Exception("当前水表表数不能为空！");
        }
        double amount = current.getNum() - last.getNum();
        if (amount == 0) {
            return;
        }
        String houseHoldInfoId = current.getGzfHouseholdInfoId();
        GzfHouseholdInfo householdInfo = householdInfoService.get(houseHoldInfoId);
        if (householdInfo == null) {
            throw new Exception("根据住户" + houseHoldInfoId + "找不到住房信息！");
        }
        GzfHousePerson housePerson = householdInfo.getGzfHousePerson();
        if (housePerson == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到租赁关系！");
        }
        String accountId = housePerson.getAccountId();
        GzfPaymentStandard paymentStandard = paymentStandardService.get(householdInfo
          .getGzfPaymentStandardId());
        if (paymentStandard == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到缴费标准！");
        }
        double waterFee = paymentStandard.getWaterFee();
        double fee = waterFee * 100 * amount;

        GzfAcctItem acctItem = new GzfAcctItem();
        acctItem.setAccountId(accountId);
        acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.WATER);
        Date currentDate = new Date();
        if (current.getTime() != null) {
            currentDate = current.getTime();
        }
        acctItem.setBeginDate(currentDate);
        if (last != null) {
            acctItem.setBeginDate(last.getTime());
        }
        acctItem.setEndDate(currentDate);
        acctItem.setReceAmount(fee);
        acctItem.setFactAmount(new Double(0));
        acctItem.setRemarks(last.getNum() + "-" + current.getNum());
        acctItem.setWriteOffPri(0);
        GzfAcctItemType acctItemType = acctItemTypeService.get(String.valueOf(acctItem
          .getAcctItemTypeId()));
        if (acctItemType != null) {
            acctItem.setWriteOffPri(acctItemType.getBillPriority());
        }
        save(acctItem);
    }

    //生成天燃气帐单
    @Transactional(readOnly = false)
    public void generateNatureGasAcctItem(GzfNaturalGas last, GzfNaturalGas current)
      throws Exception {
        if (last == null || StringUtils.isBlank(last.getId())) {//不存在上次录入记录，说明是第一次录入，此时不生成帐单
            return;
        }
        if (current == null) {
            return;
        }
        if (current.getNum() == null) {
            throw new Exception("当前天燃气立方数不能为空！");
        }
        double amount = current.getNum() - last.getNum();
        if (amount == 0) {
            return;
        }
        String houseHoldInfoId = current.getGzfHouseholdInfoId();
        GzfHouseholdInfo householdInfo = householdInfoService.get(houseHoldInfoId);
        if (householdInfo == null) {
            throw new Exception("根据住户" + houseHoldInfoId + "找不到住房信息！");
        }
        GzfHousePerson housePerson = householdInfo.getGzfHousePerson();
        if (housePerson == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到租赁关系！");
        }
        String accountId = housePerson.getAccountId();
        GzfPaymentStandard paymentStandard = paymentStandardService.get(householdInfo
          .getGzfPaymentStandardId());
        if (paymentStandard == null) {
            throw new Exception("根据住户" + householdInfo.getName() + "找不到缴费标准！");
        }
        double natureGasFee = paymentStandard.getNaturalgasFee();
        double fee = natureGasFee * 100 * amount;

        GzfAcctItem acctItem = new GzfAcctItem();
        acctItem.setAccountId(accountId);
        acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.NATURALGAS);
        Date currentDate = new Date();
        if (current.getTime() != null) {
            currentDate = current.getTime();
        }
        acctItem.setBeginDate(currentDate);
        if (last != null) {
            acctItem.setBeginDate(last.getTime());
        }
        acctItem.setEndDate(currentDate);
        acctItem.setReceAmount(fee);
        acctItem.setFactAmount(new Double(0));
        acctItem.setRemarks(last.getNum() + "-" + current.getNum());
        //		acctItem.setFactAmount(0);
        acctItem.setWriteOffPri(0);
        GzfAcctItemType acctItemType = acctItemTypeService.get(String.valueOf(acctItem
          .getAcctItemTypeId()));
        if (acctItemType != null) {
            acctItem.setWriteOffPri(acctItemType.getBillPriority());
        }
        save(acctItem);
    }


    //根据租赁关系、费用类型、开始时间、结束时间生成帐单
    @Transactional(readOnly = false)
    public void generateAcctItemByDateAndSpec(GzfHousePerson housePerson, int acctItemTypeId, Date beginDate, Date endDate) throws Exception {
        double fee = calculateFee(housePerson, beginDate, endDate, acctItemTypeId);
        if (fee == 0) {
            return;
        }
        GzfAcctItem acctItem = new GzfAcctItem();
        acctItem.setAccountId(housePerson.getAccountId());
        acctItem.setAcctItemTypeId(acctItemTypeId);
        acctItem.setBeginDate(DateUtils.getBeginDateOfDay(beginDate));
        acctItem.setEndDate(DateUtils.getPreLastDateOfDay(endDate));
        acctItem.setReceAmount(fee);
        acctItem.setFactAmount(new Double(0));
        acctItem.setWriteOffPri(0);
        GzfAcctItemType acctItemType =
          acctItemTypeService.get(String.valueOf(acctItem.getAcctItemTypeId()));
        if (acctItemType != null) {
            acctItem.setWriteOffPri(acctItemType.getBillPriority());
        }
        save(acctItem);
    }

    /**
	 * 根据租赁关系、开始时间、结束时间、费用类型计算应缴费用
	 * @param housePerson
	 * @param beginDate
	 * @param endDate
	 * @param sepcPaymentId
	 * @return 金额（以分为单位）
	 */
	public double calculateFee(GzfHousePerson housePerson, Date beginDate, Date endDate, long acctItemTypeId) throws Exception{
		if (acctItemTypeId != PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT && acctItemTypeId != PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION
				&& acctItemTypeId != PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT) {
			throw new Exception("只能计算房租、物业、能耗费用！");
		}
		if (housePerson == null) {
			throw new Exception("租赁关系不能为空！");
		}
		if (beginDate.compareTo(endDate) == 0) {
			return 0;
		}
		if (beginDate.compareTo(endDate) > 0) {
			throw new Exception("开始日期必须小于结束日期！");
		}
		//人员信息
		GzfHouseholdInfo householdInfo = housePerson.getGzfHouseholdInfo();
		if(householdInfo == null || StringUtils.isBlank(householdInfo.getId())){
			householdInfo = householdInfoService.get(housePerson.getGzfHouseholdInfoId());
			housePerson.setGzfHouseholdInfo(householdInfo);
		}
		//住房信息
		GzfHouseInfo houseInfo = housePerson.getGzfHouseInfo();
		if (houseInfo == null || StringUtils.isBlank(houseInfo.getId())) {
			houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
			housePerson.setGzfHouseInfo(houseInfo);
		}
		//缴费标准
		GzfPaymentStandard paymentStandard = householdInfo.getGzfPaymentStandard();
		if (paymentStandard == null || StringUtils.isBlank(paymentStandard.getId())) {
			paymentStandard = paymentStandardService.get(householdInfo.getGzfPaymentStandardId());
			householdInfo.setPaymentStandard(paymentStandard);
		}
		if (paymentStandard == null) {
			throw new Exception("查询不到缴费标准！");
		}
		double amount = 0;
		double unitPrice = 0;
		if (acctItemTypeId == PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT) {
			unitPrice = paymentStandard.getRentUnitPrice();
		}else if (acctItemTypeId == PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION) {
			unitPrice = paymentStandard.getConsumption();
		}else if (acctItemTypeId == PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT) {
			unitPrice = paymentStandard.getManagementFee();
		}
		unitPrice = unitPrice * 100 * houseInfo.getUseArea();//以分为单位
		double months = DateUtils.getDistanceMonthOfTwoDate(beginDate, endDate);
		double days = DateUtils.getDistanceDayOfTwoDate(beginDate, endDate);
		double maxDay = DateUtils.getMaxday(endDate);
		amount = unitPrice * (months + days/maxDay);
		return Double.valueOf(amount).intValue();
	}


    public List<GzfAcctItem> customGet(GzfAcctItem entity) {
        return dao.selectByGzfAcctItemEntity(entity);
    }
    
    public Date getAcctItemStartDate(GzfHousePerson housePerson, int acctItemTypeId){
    	Date date = housePerson.getEffectiveDate();
		GzfAcctItem tempAcctItem = new GzfAcctItem();
		tempAcctItem.setAccountId(housePerson.getAccountId());
		tempAcctItem.setAcctItemTypeId(acctItemTypeId);
		List<GzfAcctItem> houseRentAcctItems = findList(tempAcctItem);
		if (houseRentAcctItems != null && houseRentAcctItems.size() > 0) {
			date = DateUtils.addOrMinusDays(houseRentAcctItems.get(0).getEndDate().getTime(), 1);
		}else {
			GzfAcctItemHis tempAcctItemhis = new GzfAcctItemHis();
			tempAcctItemhis.setAccountId(housePerson.getAccountId());
			tempAcctItemhis.setAcctItemTypeId(acctItemTypeId);
			List<GzfAcctItemHis> houseRentAcctItemHiss = acctItemHisService.findList(tempAcctItemhis);
			if (houseRentAcctItemHiss != null && houseRentAcctItemHiss.size() > 0) {
				date = DateUtils.addOrMinusDays(houseRentAcctItemHiss.get(0).getEndDate().getTime(), 1);
			}
		}
		return DateUtils.getBeginDateOfDay(date);
    }
}
