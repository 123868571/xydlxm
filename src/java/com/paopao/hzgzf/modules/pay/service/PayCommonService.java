package com.paopao.hzgzf.modules.pay.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.gzf.entity.GzfElectric;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfNaturalGas;
import com.paopao.hzgzf.modules.gzf.entity.GzfWaterMeter;
import com.paopao.hzgzf.modules.gzf.service.GzfElectricService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfNaturalGasService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.gzf.service.GzfWaterMeterService;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItem;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemHis;

@Service
@Transactional(readOnly=false)
public class PayCommonService {
	
	@Autowired
    private GzfWaterMeterService  gzfWaterMeterService;
	@Autowired
	GzfElectricService gzfElectricService;
	@Autowired
	GzfHouseholdInfoService householdInfoService;
	@Autowired
	GzfHouseInfoService houseInfoService;
    @Autowired
	GzfPaymentStandardService payStandardService;
	@Autowired
	GzfPalacesService placesService;
    @Autowired
    GzfAcctItemService gzfAcctItemService;
    @Autowired
    GzfAcctItemHisService gzfAcctItemHisService;
    @Autowired
    GzfNaturalGasService gzfNaturalGasService;
    @Autowired
	GzfAccountBalanceService balanceService;
    @Autowired
    DealBillService dealBillService;
    @Autowired
    GzfPaymentService paymentService;
    /**
     * 退费时查询房租和物业应缴/退的金额
     * 当金额大于0，应缴
     * 当金额小于0，应退
     * @param housePerson
     * @param param
     * @return
     */
    @Transactional(readOnly=true)
	public Map<String, Double> getFeceFee(GzfHousePerson housePerson, Map param) throws Exception{
		Map retMap = new HashMap();
		Date endDate = (Date)param.get("endDate");
		Date houseRentExpireDate = getAcctItemStartDate(housePerson, PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		Date manageFeeExpireDate = getAcctItemStartDate(housePerson, PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
		Date consumFeeExpireDate = getAcctItemStartDate(housePerson, PaymentConst.SPECIAL_PAYMENT.CONSUMPTION);
		double houseRentFee = paymentService.getReceFeeByDate(houseRentExpireDate, endDate, housePerson, PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		double manageFee = paymentService.getReceFeeByDate(manageFeeExpireDate, endDate, housePerson, PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
		double consumFee = paymentService.getReceFeeByDate(consumFeeExpireDate, endDate, housePerson, PaymentConst.SPECIAL_PAYMENT.CONSUMPTION);
		double freeFee = 0;
		List<GzfAccountBalance> balances = balanceService.getGroupedBalanceByAccountId(housePerson.getAccountId());
		for(int i = 0; i < balances.size(); i++){
			if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
				houseRentFee -= balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.MANAGEMENT) {
				manageFee -= balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.CONSUMPTION){
				consumFee -= balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL){
				freeFee = balances.get(i).getBalance();
			}
		}
		if ((int)freeFee > 0) {
			if ((int)houseRentFee > 0) {
				if (freeFee >= houseRentFee) {
					freeFee = freeFee - houseRentFee;
					houseRentFee = 0;
				}else{
					freeFee = 0;
					houseRentFee = houseRentFee - freeFee;
				}
			}
		}
		if ((int)freeFee > 0) {
			if ((int)manageFee > 0) {
				if (freeFee >= manageFee) {
					freeFee = freeFee - manageFee;
					manageFee = 0;
				}else{
					freeFee = 0;
					manageFee = manageFee - freeFee;
				}
			}
		}
		if ((int)freeFee > 0) {
			if ((int)consumFee > 0) {
				if (freeFee >= consumFee) {
					freeFee = freeFee - consumFee;
					consumFee = 0;
				}else{
					freeFee = 0;
					consumFee = consumFee - freeFee;
				}
			}
		}
		retMap.put("houseRentFee", houseRentFee);
		retMap.put("manageFee", manageFee);
		retMap.put("consumFee", consumFee);
		retMap.put("freeFee", 0-freeFee);
		return retMap;
	}
    
    @Transactional(readOnly=true)
    private Date getAcctItemStartDate(GzfHousePerson housePerson, int acctItemTypeId){
    	Date date = housePerson.getEffectiveDate();
		GzfAcctItem tempAcctItem = new GzfAcctItem();
		tempAcctItem.setAccountId(housePerson.getAccountId());
		tempAcctItem.setAcctItemTypeId(acctItemTypeId);
		List<GzfAcctItem> houseRentAcctItems = gzfAcctItemService.findList(tempAcctItem);
		if (houseRentAcctItems != null && houseRentAcctItems.size() > 0) {
			date = DateUtils.addOrMinusDays(houseRentAcctItems.get(0).getEndDate().getTime(), 1);
		}else {
			GzfAcctItemHis tempAcctItemhis = new GzfAcctItemHis();
			tempAcctItemhis.setAccountId(housePerson.getAccountId());
			tempAcctItemhis.setAcctItemTypeId(acctItemTypeId);
			List<GzfAcctItemHis> houseRentAcctItemHiss = gzfAcctItemHisService.findList(tempAcctItemhis);
			if (houseRentAcctItemHiss != null && houseRentAcctItemHiss.size() > 0) {
				date = DateUtils.addOrMinusDays(houseRentAcctItemHiss.get(0).getEndDate().getTime(), 1);
			}
		}
		return DateUtils.getBeginDateOfDay(date);
    }
    
    //退房
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public Map houseCheckOut(GzfHousePerson housePerson, Map param) throws Exception{
		Date endDate = (Date)param.get("endDate");
		//生成各项帐单
		//房租帐单
		Date houseRentExpireDate = getAcctItemStartDate(housePerson, PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
		
//		Date houseRentExpireDate = balanceService.getFeeExpireDate(housePerson, PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		gzfAcctItemService.generateAcctItemByDateAndSpec(housePerson, PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT, 
				houseRentExpireDate, endDate);
		
		//物业帐单
		Date manageFeeExpireDate = getAcctItemStartDate(housePerson, PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
		gzfAcctItemService.generateAcctItemByDateAndSpec(housePerson, PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT, 
				manageFeeExpireDate, endDate);
		
		//能耗帐单
		Date consumptionFeeExpireDate = getAcctItemStartDate(housePerson, PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
		gzfAcctItemService.generateAcctItemByDateAndSpec(housePerson, PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION, 
				consumptionFeeExpireDate, endDate);
		
		//水
		Date currentDate = new Date();
		GzfWaterMeter gzfWaterMeter = new GzfWaterMeter();
		gzfWaterMeter.setGzfHouseholdInfoId(housePerson.getGzfHouseholdInfoId());
		gzfWaterMeter.setGzfHouseInfoId(housePerson.getGzfHouseInfoId());
		gzfWaterMeter.setNum(Double.valueOf(param.get("water").toString()));
		gzfWaterMeter.setRemarks("退房");
		gzfWaterMeter.setTime(currentDate);
		List<GzfWaterMeter> gs = gzfWaterMeterService.getByHouseId(housePerson.getGzfHouseInfoId());
		GzfWaterMeter g = null;
		if (!gs.isEmpty()) {
			g = gs.get(0);
			g.setGzfHouseholdInfoId(gzfWaterMeter.getGzfHouseholdInfoId());
		}
		//生成帐单
		if (g != null && StringUtils.isNoneBlank(g.getId())) {
            gzfAcctItemService.generateWaterAcctItem(g, gzfWaterMeter);
        }
		//保存到水费抄表记录表中
		gzfWaterMeterService.save(gzfWaterMeter);
		
		//电
		GzfElectric gzfElectric = new GzfElectric();
		gzfElectric.setGzfHouseholdInfoId(housePerson.getGzfHouseholdInfoId());
		gzfElectric.setGzfHouseInfoId(housePerson.getGzfHouseInfoId());
		gzfElectric.setNum(Double.valueOf(param.get("electric").toString()));
		gzfElectric.setRemarks("退房");
		gzfElectric.setTime(currentDate);
		List<GzfElectric> records = gzfElectricService.getByHouseId(housePerson.getGzfHouseInfoId());
		GzfElectric lastEle = null;
		if (!records.isEmpty()) {
			lastEle = records.get(0);
			lastEle.setGzfHouseholdInfoId(gzfElectric.getGzfHouseholdInfoId());
		}
		//生成帐单
		if (lastEle != null && StringUtils.isNoneBlank(lastEle.getId())) {
			gzfAcctItemService.generateElectricAcctItem(lastEle, gzfElectric);
		}
		//保存到水费抄表记录表中
		gzfElectricService.save(gzfElectric);
		
		//天然气
		GzfNaturalGas gzfNaturalGas = new GzfNaturalGas();
		gzfNaturalGas.setGzfHouseholdInfoId(housePerson.getGzfHouseholdInfoId());
		gzfNaturalGas.setGzfHouseInfoId(housePerson.getGzfHouseInfoId());
		gzfNaturalGas.setNum(Double.valueOf(param.get("gas").toString()));
		gzfNaturalGas.setRemarks("退房");
		gzfNaturalGas.setTime(currentDate);
		List<GzfNaturalGas> gasRecords = gzfNaturalGasService.getByHouseId(housePerson.getGzfHouseInfoId());
		GzfNaturalGas lastGas = gasRecords.get(0);
		lastGas.setGzfHouseholdInfoId(gzfNaturalGas.getGzfHouseholdInfoId());
		if (lastGas != null && StringUtils.isNoneBlank(lastGas.getId())) {
			gzfAcctItemService.generateNatureGasAcctItem(lastGas, gzfNaturalGas);
		}
		gzfNaturalGasService.save(gzfNaturalGas);
		
		//设备维修费帐单
		if (param.get("dissipationFee") != null && StringUtils.isNoneBlank(param.get("dissipationFee").toString())
				&& Double.valueOf(param.get("dissipationFee").toString()) > 0) {
			GzfAcctItem acctItem = new GzfAcctItem();
			acctItem.setAccountId(housePerson.getAccountId());
			acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.REPAIR_FEE);
			acctItem.setBeginDate(currentDate);
			acctItem.setEndDate(endDate);
			acctItem.setReceAmount(Double.valueOf(param.get("dissipationFee").toString()) * 100);
			acctItem.setFactAmount(new Double(0));
			acctItem.setWriteOffPri(99);
			gzfAcctItemService.save(acctItem);
		}
		
		//销房租
		dealBillService.writeOffByAccountIdAndItemType(housePerson.getAccountId(), PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
		//销物业
		dealBillService.writeOffByAccountIdAndItemType(housePerson.getAccountId(), PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
		//能耗
		dealBillService.writeOffByAccountIdAndItemType(housePerson.getAccountId(), PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
		//水
		dealBillService.writeOffByAccountIdAndItemType(housePerson.getAccountId(), PaymentConst.ACCT_ITEM_TYPE.WATER);
		//电
		dealBillService.writeOffByAccountIdAndItemType(housePerson.getAccountId(), PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY);
		//天燃气
		dealBillService.writeOffByAccountIdAndItemType(housePerson.getAccountId(), PaymentConst.ACCT_ITEM_TYPE.NATURALGAS);
		//设备维修费
		dealBillService.writeOffByAccountIdAndItemType(housePerson.getAccountId(), PaymentConst.ACCT_ITEM_TYPE.REPAIR_FEE);
		
		//塞金额
		return getAcctItemMoney(housePerson);
	}
	
	@Transactional(readOnly=true)
	public Map<String, Double> getAcctItemMoney(GzfHousePerson housePerson){
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(housePerson.getAccountId());
		List<GzfAcctItem> items = gzfAcctItemService.getSumMoney(acctItem);
		
		double houseRentBalance = 0;
		double managementBalance = 0;
		double waterFeeBalance = 0;
		double electricityFeeBalance = 0;
		double naturalgasFeeBalance = 0;
		double depositeBalance = 0;
		double consumBalance = 0;
		double freeBalance = 0;
		double repairBalance = 0;
		
		List<GzfAccountBalance> balances = balanceService.getGroupedBalanceByAccountId(housePerson.getAccountId());
		for(int i = 0; i < balances.size(); i++){
			if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
				houseRentBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.MANAGEMENT) {
				managementBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.CONSUMPTION){
				consumBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.WATER){
				waterFeeBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.ELECTRICITY){
				electricityFeeBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.NATURALGAS){
				naturalgasFeeBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.DEPOSIT){
				depositeBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL){
				freeBalance = balances.get(i).getBalance();
			}else if (balances.get(i).getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE) {
				repairBalance = balances.get(i).getBalance();
			}
		}
		
		double houseRent = 0;
		double management = 0;
		double waterFee = 0;
		double electricityFee = 0;
		double naturalgasFee = 0;
		double deposite = 0;
		double consumption = 0;
		double repairFee = 0;
		for(int i = 0; i < items.size(); i++){
			int acctItemType = items.get(i).getAcctItemTypeId();
//			double fee = new BigDecimal(items.get(i).getReceAmount() - items.get(i).getFactAmount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double fee = items.get(i).getReceAmount() - items.get(i).getFactAmount();
			if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT) {
				houseRent += fee;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT) {
				management += fee;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.WATER) {
				waterFee += fee;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY) {
				electricityFee += fee;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.NATURALGAS) {
				naturalgasFee += fee;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.DEPOSIT) {
				deposite += fee;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION) {
				consumption += fee;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.REPAIR_FEE) {
				repairFee += fee;
			}
		}
		Map retMap = new HashMap();
		retMap.put("houseRent", houseRent - houseRentBalance);
		retMap.put("management", management - managementBalance + consumption - consumBalance);
		retMap.put("waterFee", waterFee - waterFeeBalance);
		retMap.put("electricityFee", electricityFee - electricityFeeBalance);
		retMap.put("naturalgasFee", naturalgasFee - naturalgasFeeBalance);
		retMap.put("deposite", deposite - depositeBalance);
		retMap.put("consumption", consumption - consumBalance);
		retMap.put("freeBalance", 0 - freeBalance);
		retMap.put("repairFee", repairFee - repairBalance);
		return retMap;
	}
	
	
	@Transactional(readOnly=true)
	public Map<String, Double> getGroupBalance(GzfAccount gzfAccount){
		List<GzfAccountBalance> balances = balanceService.getGroupedBalanceByAccountId(gzfAccount.getId());
		double houseRent = 0;
		double management = 0;
		double waterFee = 0;
		double electricityFee = 0;
		double naturalgasFee = 0;
		double deposite = 0;
		double freeFee = 0;
		double repairFee = 0;
		for(int i = 0; i < balances.size(); i++){
			int acctItemType = balances.get(i).getAcctItemTypeId();
			double balance = balances.get(i).getBalance();
			if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT) {
				houseRent += balance;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT || acctItemType == PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION) {
				management += balance;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.WATER) {
				waterFee += balance;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY) {
				electricityFee += balance;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.NATURALGAS) {
				naturalgasFee += balance;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.DEPOSIT) {
				deposite += balance;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.FREE_FEE) {
				freeFee += balance;
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.FREE_FEE) {
				repairFee += balance;
			}
		}
		Map retMap = new HashMap();
		retMap.put("houseRent", houseRent);
		retMap.put("management", management);
		retMap.put("waterFee", waterFee);
		retMap.put("electricityFee", electricityFee);
		retMap.put("naturalgasFee", naturalgasFee);
		retMap.put("deposite", deposite);
		retMap.put("freeFee", freeFee);
		retMap.put("repairFee", repairFee);
		return retMap;
	}
	
	//根据生失效时间，计算应缴纳金额
	@Transactional(readOnly=true)
	public double getReceFeeByDate(Date beginDate, Date endDate, GzfHousePerson housePerson, int specPaymentId) throws Exception{
		return paymentService.getReceFeeByDate(beginDate, endDate, housePerson, specPaymentId);
	}
	
	//根据生失效时间，计算应缴纳金额
	@Transactional(readOnly=true)
	public double getReceFeeByNum(Double beginNum, Double endNum, GzfHousePerson housePerson, int specPaymentId) throws Exception{
		return paymentService.getReceFeeByNum(beginNum, endNum, housePerson, specPaymentId);
	}
	
	//计算生效日期
	@Transactional(readOnly=true)
	public Date getEffectDate(GzfHousePerson housePerson, int specPaymentId) throws Exception{
		return paymentService.getEffectDate(housePerson, specPaymentId);
	}
	
	//计算失效日期
	@Transactional(readOnly=true)
	public Date getExpireDate(Date effectDate, double amount, GzfHousePerson housePerson, int specPaymentId) throws Exception{
		return paymentService.getExpireDate(effectDate, amount, housePerson, specPaymentId);
	}
		
}
