package com.paopao.hzgzf.modules.pay.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.UpdateLastPayTime;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.IdGen;
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
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.gzf.webservice.DataServiceStub;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItem;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransfer;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceRecord;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceType;
import com.paopao.hzgzf.modules.pay.entity.GzfBillRecord;
import com.paopao.hzgzf.modules.pay.entity.GzfPayment;
import com.paopao.hzgzf.modules.pay.entity.GzfSpecialPayment;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;
/**
 * 生成帐单和销帐 service
 * @author songyahe
 *
 */
@Service
@Transactional(readOnly=false)
public class DealBillService {
	@Autowired
	GzfAcctItemService acctItemService;
	@Autowired
	GzfPaymentService paymentService;
	@Autowired
	GzfAcctTransferService transferService;
	@Autowired
	GzfAccountBalanceService balanceService;
	@Autowired
	GzfBalanceTypeService balanceTypeService;
	@Autowired
	GzfSpecialPaymentService specialPaymentService;
	@Autowired
	GzfBillRecordService billRecordService;//已销帐帐单service
	@Autowired
	GzfBalanceRecordService balanceRecordService;
	@Autowired
	GzfAccountService accountService;
	
	@Autowired
	GzfHousePersonService housePersonService;
	@Autowired
	GzfHouseInfoService houseInfoService;
	@Autowired
	GzfPalacesService placesService;
	@Autowired
	GzfHouseholdInfoService householdInfoService;
	@Autowired
	GzfPaymentStandardService payStandardService;
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 根据帐单和帐目销帐
	 * @param acctItems 帐单
	 * @param acctItemTypeId 帐目，acctItems中的acctItemTypeId必须与此一致
	 * @throws Exception
	 */
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void writeOff(List<GzfAcctItem> acctItems, int acctItemTypeId) throws Exception{
		if (acctItems == null || acctItems.size() == 0) {
			return;
		}
		GzfAccountBalance balanceTemp = new GzfAccountBalance();
		balanceTemp.setAccountId(acctItems.get(0).getAccountId());
		balanceTemp.setAcctItemTypeId(acctItemTypeId);
		double initTotal = 0;
		List<GzfAccountBalance> balances = new ArrayList<GzfAccountBalance>();
		GzfAccountBalance total = balanceService.getSumBalanceByAcctIdAndAcctItemTypeId(balanceTemp);
		if(total != null && total.getBalance().intValue() > 0){
			initTotal = total.getBalance();
			List<GzfAccountBalance> speBalances = balanceService.findList(balanceTemp);
			if (speBalances != null && speBalances.size() > 0) {
				CollectionUtils.addAll(balances, speBalances.iterator());
			}
		}
		double freeTotal = 0;
		if (acctItemTypeId != PaymentConst.ACCT_ITEM_TYPE.FREE_FEE && acctItemTypeId != PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT 
				&& acctItemTypeId != PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT	&& acctItemTypeId != PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION) {
			balanceTemp.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.FREE_FEE);
			GzfAccountBalance totalFreeFee = balanceService.getSumBalanceByAcctIdAndAcctItemTypeId(balanceTemp);
			if(totalFreeFee != null && totalFreeFee.getBalance().intValue() > 0){
				freeTotal = totalFreeFee.getBalance();
				List<GzfAccountBalance> freeBalances = balanceService.findList(balanceTemp);
				if (freeBalances != null && freeBalances.size() > 0) {
					CollectionUtils.addAll(balances, freeBalances.iterator());
				}
			}
		}
		if (balances == null || balances.size() == 0) {
			return;
		}
		
		GzfAccountBalance balance = null;
		List<GzfAccountBalance> balanceDeleteList = new ArrayList<GzfAccountBalance>();
		List<GzfBillRecord> billRecordList = new ArrayList<GzfBillRecord>();
		List<GzfAcctItem> acctItemList = new ArrayList<GzfAcctItem>();
		List<GzfAcctItem> finishAcctItemList = new ArrayList<GzfAcctItem>();
		List<GzfBalanceRecord> balanceRecords = new ArrayList<GzfBalanceRecord>();
		Map<String, String> needDelBalanceMap = new HashMap<String, String>();
		Map<String, GzfAccountBalance> needSaveBalanceMap = new HashMap<String, GzfAccountBalance>();
		for(int i = 0; i < acctItems.size(); i++){
			GzfAcctItem acctItemTemp = acctItems.get(i);
			double receAmount = acctItemTemp.getReceAmount();
			double factAmount = acctItemTemp.getFactAmount();
			double needPay = receAmount - factAmount;
			if (needPay == 0) {
				finishAcctItemList.add(acctItemTemp);
				continue;
			}
			for(int j = 0; j < balances.size(); j++){
				balance = balances.get(j);
				double amount = balance.getBalance();
				if (amount == 0) {
					//需要入历史
					if (!needDelBalanceMap.containsKey(balance.getId())) {
						needDelBalanceMap.put(balance.getId(), balance.getId());
						balanceDeleteList.add(balance);
					}
					if (needSaveBalanceMap.containsKey(balance.getId())) {
						needSaveBalanceMap.remove(balance.getId());
					}
					continue;
				}
				GzfBalanceType balanceType = balance.getBalanceType();
				if (balanceType == null) {
					balanceType = balanceTypeService.get(balance.getBalanceTypeId() + "");
					balance.setBalanceType(balanceType);
				}
				if (PaymentConst.YES_OR_NO.NO.equals(balanceType.getAllowWriteoff())) {//不允许销帐
					continue;
				}
				if (balance.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL) {//自由预存,允许销任何帐目
					
				}else {
					GzfSpecialPayment specialPayment = specialPaymentService.get(balance.getSpePaymentId()+"");
					if (specialPayment == null) {
						continue;
					}
					if (specialPayment.getAcctItemTypeId() != acctItemTemp.getAcctItemTypeId()) {
						continue;
					}
				}
				receAmount = acctItemTemp.getReceAmount();
				factAmount = acctItemTemp.getFactAmount();
				needPay = receAmount - factAmount;
				double billAmount = 0;
				if (amount >= needPay) {
					acctItemTemp.setFactAmount(receAmount);
					finishAcctItemList.add(acctItemTemp);
					billAmount = needPay;
				}else {
					acctItemTemp.setFactAmount(amount + factAmount);
					acctItemList.add(acctItemTemp);
					billAmount = amount;
				}
				balance.setBalance(amount - billAmount);
				if (balance.getBalance() == 0) {
					if (needSaveBalanceMap.containsKey(balance.getId())) {
						needSaveBalanceMap.remove(balance.getId());
					}
					//需要入历史
					if (!needDelBalanceMap.containsKey(balance.getId())) {
						needDelBalanceMap.put(balance.getId(), balance.getId());
						balanceDeleteList.add(balance);
					}
				}else {
					needSaveBalanceMap.put(balance.getId(), balance);
				}
				
				GzfBillRecord billRecord = new GzfBillRecord();
				billRecord.setAcctItemId(acctItemTemp.getId());
				billRecord.setAcctItemTypeId(acctItemTemp.getAcctItemTypeId());
				billRecord.setAccountId(acctItemTemp.getAccountId());
				billRecord.setPaymentId(balance.getPaymentId());
				billRecord.setLastBillAmount(needPay);
				billRecord.setBillAmount(billAmount);
				billRecordList.add(billRecord);
				billRecord.setBalanceId(balance.getId());
				
				//余额支出明细
				GzfBalanceRecord record = new GzfBalanceRecord();
				record.setAccountBalanceId(balance.getId());
				record.setAccountId(balance.getAccountId());
				record.setAmount(0 - billAmount);
				record.setBalance(balance.getBalance());
				record.setBalanceTypeId(balance.getBalanceTypeId());
				record.setOperType(PaymentConst.OPER_TYPE.EXPENSE);//1－收入 2－支出
				record.setPaymentId(balance.getPaymentId());
				record.setAcctItemTypeId(acctItemTemp.getAcctItemTypeId());
				record.setSpePaymentId(balance.getSpePaymentId());
				record.setTotalBalance(initTotal + record.getAmount());
				String optCode = "";
				if (PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT == acctItemTemp.getAcctItemTypeId()) {
					optCode = PaymentConst.OPT_CODE.WRITE_OFF_RENT;
				}else if (PaymentConst.ACCT_ITEM_TYPE.WATER == acctItemTemp.getAcctItemTypeId()) {
					optCode = PaymentConst.OPT_CODE.WRITE_OFF_WATER;
				}else if (PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY == acctItemTemp.getAcctItemTypeId()) {
					optCode = PaymentConst.OPT_CODE.WRITE_OFF_ELECTRICITY;
				}else if (PaymentConst.ACCT_ITEM_TYPE.NATURALGAS == acctItemTemp.getAcctItemTypeId()) {
					optCode = PaymentConst.OPT_CODE.WRITE_OFF_NATURALGAS;
				}else if (PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT == acctItemTemp.getAcctItemTypeId()) {
					optCode = PaymentConst.OPT_CODE.WRITE_OFF_MANAGEMENT;
				}else if (PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION == acctItemTemp.getAcctItemTypeId()) {
					optCode = PaymentConst.OPT_CODE.WRITE_OFF_CONSUMPTION;
				}else if (PaymentConst.ACCT_ITEM_TYPE.REPAIR_FEE == acctItemTemp.getAcctItemTypeId()) {
					optCode = PaymentConst.OPT_CODE.WRITE_OFF_REPAIR;
				}
				record.setOptCode(optCode);
				balanceRecords.add(record);
				
				initTotal = initTotal - billAmount;
				if (initTotal == 0) {
					initTotal = freeTotal;
				}
				if (billAmount == needPay) {
					break;
				}
			}
		}
		//需要更新的帐单
		if (acctItemList.size() > 0) {
			acctItemService.saveList(acctItemList);
		}
		//删除销帐的帐单并挪历史
		if (finishAcctItemList.size() > 0) {
			acctItemService.deleteList(finishAcctItemList, true);
		}
		//删除余额为0的帐本并入历史
		if (balanceDeleteList.size() > 0) {
			balanceService.deleteList(balanceDeleteList, true);
		}
		//保存余额
		if (!needSaveBalanceMap.isEmpty()) {
			Iterator iterator = needSaveBalanceMap.keySet().iterator();
			List<GzfAccountBalance> list = new ArrayList<GzfAccountBalance>();
			while(iterator.hasNext()){
				GzfAccountBalance tempVal = needSaveBalanceMap.get(iterator.next());
				if (tempVal != null) {
					list.add(tempVal);
				}
			}
			balanceService.saveList(list);
		}
		//销帐记录
		if (billRecordList.size() > 0) {
			billRecordService.saveList(billRecordList);
		}
		//支出记录
		balanceRecordService.saveList(balanceRecords);
	}
	
	/**
	 * 根据帐户和帐目销帐
	 * @param account 帐户
	 * @param acctItemTypeId 帐目
	 * @throws Exception
	 */
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void writeOffByAccount(GzfAccount account, int acctItemTypeId) throws Exception{
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(account.getId());
		acctItem.setAcctItemTypeId(acctItemTypeId);
		List<GzfAcctItem> acctItems = acctItemService.findList(acctItem);
		if (acctItems == null || acctItems.size() == 0) {
			return;
		}
		writeOff(acctItems, acctItemTypeId);
	}
	
	/**
	 * 根据帐户和帐目销帐
	 * @param account 帐户
	 * @param acctItemTypeId 帐目
	 * @throws Exception
	 */
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void writeOffByAccountIdAndItemType(String accountId, int acctItemTypeId) throws Exception{
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(accountId);
		acctItem.setAcctItemTypeId(acctItemTypeId);
		List<GzfAcctItem> acctItems = acctItemService.findList(acctItem);
		if (acctItems == null || acctItems.size() == 0) {
			return;
		}
		writeOff(acctItems, acctItemTypeId);
	}
	
	/**
	 * 缴费总入口
	 * @param gzfPayment
	 * @throws Exception
	 */
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void pay(GzfPayment gzfPayment, boolean isAutoWriteoff) throws Exception{
		gzfPayment = paymentService.pay(gzfPayment);
		if ((gzfPayment.getOweFee().intValue() > 0) && isAutoWriteoff) {
			writeOffByAccountIdAndItemType(gzfPayment.getAccountId(), gzfPayment.getAcctItemTypeId());
		}
		if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
			DataServiceStub dataServiceStub = null;
            try {
                dataServiceStub = new DataServiceStub();
            } catch (AxisFault e1) {
                logger.error("", e1);
            }
            GzfAccount account = new GzfAccount();
            account.setId(gzfPayment.getAccountId());
            account = accountService.get(account);
			GzfHousePerson housePerson = housePersonService.get(account.getCustId());
			UpdateLastPayTime updateLastPayTime = new UpdateLastPayTime();
			updateLastPayTime.setRenterCode(housePerson.getGzfHouseholdInfo().getCode());
			updateLastPayTime.setRoomCode(housePerson.getGzfHouseInfo().getCode());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(gzfPayment.getExpireDate());
			updateLastPayTime.setLastPayTime(calendar);
			dataServiceStub.updateLastPayTime(updateLastPayTime);
		}
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void pay(GzfPayment gzfPayment) throws Exception{
		boolean isAutoWriteOff = true;
		if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.DEPOSIT) {
			isAutoWriteOff = false;
		}
		String orderCode = paymentService.generateOrderCode();
		gzfPayment.setOrderCode(orderCode);
		
		String extendSpec = gzfPayment.getExtendSpecPaymentId();
		String[] multiSpecs = null;
		if (StringUtils.isNoneBlank(extendSpec)) {
			multiSpecs = extendSpec.split("\\|");
		}
		if (multiSpecs != null && multiSpecs.length > 0) {
			String[] amounts = gzfPayment.getExtendAmount().split("\\|");
			for(int i = 0; i < multiSpecs.length; i++){
				if (StringUtils.isBlank(amounts[i])) {
					throw new Exception("费用类型" + multiSpecs[i] + "缴费金额为空！");
				}
				double amount = Double.valueOf(amounts[i]) * 100;//转成分
				GzfPayment gzfPayment2 = new GzfPayment();
//			BeanMapper.copy(gzfPayment, gzfPayment2);
				BeanUtils.copyProperties(gzfPayment2, gzfPayment);
				gzfPayment2.setExtendSpecPaymentId("");
				gzfPayment2.setExtendAmount("");
				gzfPayment2.setSpePaymentId(Integer.valueOf(multiSpecs[i]));
				gzfPayment2.setAmount(amount);
				gzfPayment2.setOrderCode(orderCode);
				gzfPayment2.setIsNewRecord(false);
				if (gzfPayment2.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.DEPOSIT) {
					isAutoWriteOff = false;
				}
				pay(gzfPayment2, isAutoWriteOff);
			}
		}
		
		pay(gzfPayment, isAutoWriteOff);
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void payHouseRentBatch(GzfPayment gzfPayment, boolean isAutoWriteoff) throws Exception{
		GzfAccount account = new GzfAccount();
		account.setPhoneNo(gzfPayment.getPhoneNo());
		List<GzfAccount> accounts = accountService.findList(account);
		if (accounts == null || accounts.size() == 0) {
			throw new Exception("根据手机号码" + account.getPhoneNo() + "查找不到帐户！");
		}
		if (accounts.size() > 1) {
			throw new Exception("根据手机号码" + account.getPhoneNo() + "查到多个帐户！");
		}
		account = accounts.get(0);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			throw new Exception("根据手机号码" + account.getPhoneNo() + "查到不到住房信息！");
		}
		//人员信息
		GzfHouseholdInfo householdInfo = householdInfoService.get(housePerson.getGzfHouseholdInfoId());
		GzfPaymentStandard paymentStandard = payStandardService.get(householdInfo.getGzfPaymentStandardId());
		//住房信息
		GzfHouseInfo houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
		double unitPrice = 0;
		if (paymentStandard != null) {
			unitPrice = paymentStandard.getRentUnitPrice();
		}
		double rentFee = unitPrice * 100 * (houseInfo.getUseArea());//以分为单位
		int days = new BigDecimal(gzfPayment.getAmount()).divide(new BigDecimal(rentFee), 0, BigDecimal.ROUND_HALF_UP).intValue();
		
		Date lastExpireDate = balanceService.getFeeExpireDate(housePerson, PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setEffectDate(DateUtils.getBeginDateOfDay(DateUtils.addOrMinusDays(lastExpireDate.getTime(),1)));
		gzfPayment.setExpireDate(DateUtils.getEndDateOfDay(DateUtils.addOrMinusDays(gzfPayment.getEffectDate().getTime(), days)));
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.IMPORT);
		pay(gzfPayment, isAutoWriteoff);
	}
	
	/**
	 * 转帐总入口
	 * @param acctTransfer
	 * @throws Exception
	 */
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void transfer(GzfAcctTransfer acctTransfer) throws Exception{
		GzfAccountBalance temp = new GzfAccountBalance();
		temp.setAccountId(acctTransfer.getSrcAcctId());
		temp.setSpePaymentId(acctTransfer.getSrcSpecPaymentId());
		GzfAccountBalance outBalance = balanceService.getSumBalanceByAcctIdAndSpecId(temp);
		if (outBalance == null || outBalance.getBalance() < acctTransfer.getAmount()) {
			throw new Exception("余额不足！");
		}
		
		if (StringUtils.isBlank(acctTransfer.getDestAcctId())) {
			acctTransfer.setDestAcctId(acctTransfer.getSrcAcctId());
		}
		acctTransfer.setAmount(acctTransfer.getAmount());
		String optCode = "";
		if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.WATER) {
			optCode = PaymentConst.OPT_CODE.TRANS_WATER_FEE;
		}else if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.ELECTRICITY) {
			optCode = PaymentConst.OPT_CODE.TRANS_ELECTRICITY_FEE;
		}else if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.NATURALGAS) {
			optCode = PaymentConst.OPT_CODE.TRANS_NATURALGAS_FEE;
		}else if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
			optCode = PaymentConst.OPT_CODE.TRANS_HOUSE_RENT_FEE;
		}else if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.MANAGEMENT) {
			optCode = PaymentConst.OPT_CODE.TRANS_MANAGEMENT_FEE;
		}
		acctTransfer.setOptCode(optCode);
		//保存transfer
		transferService.save(acctTransfer);
		
		//转出payment
		GzfPayment srcPayment = new GzfPayment();
		srcPayment.setAccountId(acctTransfer.getSrcAcctId());
		srcPayment.setCustId(acctTransfer.getCustId());
		srcPayment.setAmount((double) (0-acctTransfer.getAmount()));
		srcPayment.setSpePaymentId(acctTransfer.getSrcSpecPaymentId());
		srcPayment.setTransferId(acctTransfer.getId());
		srcPayment.setPhoneNo(acctTransfer.getPhoneNo());
		srcPayment.setOptCode(optCode);
		paymentService.pay(srcPayment);
		
		//转入payment
		GzfPayment destPayment = new GzfPayment();
		destPayment.setAccountId(acctTransfer.getDestAcctId());
		destPayment.setAmount(Double.valueOf(acctTransfer.getAmount()));
		destPayment.setSpePaymentId(acctTransfer.getDestSpecPaymentId());
		destPayment.setTransferId(acctTransfer.getId());
		destPayment.setPhoneNo(acctTransfer.getPhoneNo());
		destPayment.setOptCode(optCode);
		if (!destPayment.getAccountId().equals(srcPayment.getAccountId())) {
			GzfAccount account = accountService.get(destPayment.getAccountId());
			if (account == null) {
				throw new Exception("根据帐户id" + destPayment.getAccountId() + "查询不到帐户！");
			}
			destPayment.setCustId(account.getCustId());
		}
		boolean isAutoWriteOff = true;
		if (destPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.DEPOSIT) {
			isAutoWriteOff = false;
		}
		this.pay(destPayment, isAutoWriteOff);
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void refundWhenCheckOut(GzfPayment gzfPayment) throws Exception{
		gzfPayment.setId(IdGen.uuid());
		double total = 0;
		GzfAccountBalance temp = new GzfAccountBalance();
		temp.setAccountId(gzfPayment.getAccountId());
		List<GzfAccountBalance> balanceList = balanceService.findList(temp);
		if (balanceList == null || balanceList.size() == 0) {
			return;
		}
		Date sysdate = new Date();
		List<GzfBalanceRecord> balanceRecords = new ArrayList<GzfBalanceRecord>();
		for(int i = 0; i < balanceList.size(); i++){
			GzfAccountBalance balance = balanceList.get(i);
			if (GzfAccountBalance.DEL_FLAG_DELETE.equals(balance.getDelFlag()) || balance.getBalance() == 0) {
				continue;
			}
			total += balance.getBalance();
			//余额支出明细
			GzfBalanceRecord record = new GzfBalanceRecord();
			record.setAccountBalanceId(balance.getId());
			record.setAccountId(balance.getAccountId());
			record.setAmount(0 - balance.getBalance());
			record.setBalance(balance.getBalance());
			record.setBalanceTypeId(balance.getBalanceTypeId());
			record.setOperType(PaymentConst.OPER_TYPE.EXPENSE);//1－收入 2－支出
			record.setPaymentId(gzfPayment.getId());
			record.setAcctItemTypeId(balance.getAcctItemTypeId());
			record.setSpePaymentId(balance.getSpePaymentId());
			record.setOptCode(PaymentConst.OPT_CODE.REFUND);
			record.setTotalBalance(record.getBalance());
			balanceRecords.add(record);
			
			balance.setExpireDate(sysdate);
			balance.setBalance(new Double(0));
		}
		balanceService.deleteList(balanceList, true);
		balanceRecordService.saveList(balanceRecords);
		
		
		String loginName = "admin";
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			loginName = user.getLoginName();
		}
		String orderCode = loginName + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		gzfPayment.setOrderCode(orderCode);
		gzfPayment.setAmount(0-total);
		gzfPayment.setOptCode(PaymentConst.OPT_CODE.REFUND);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setState(PaymentConst.PAYMENT_STATE.PAYED);
		gzfPayment.setIsNewRecord(true);
		paymentService.save(gzfPayment);
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void settlementOneOff(GzfPayment gzfPayment) throws Exception{
		Map<String, Double> retMap = getAcctItemMoney(gzfPayment.getAccountId());
		double houseRentFee = 0, manageFee = 0, consumption = 0, waterFee = 0, electricityFee = 0, naturalgasFee = 0, deposite = 0,
				freeFee = 0, repairFee = 0;
		houseRentFee = retMap.get("houseRent");
        manageFee = retMap.get("management");
        consumption = retMap.get("consumption");
        waterFee = retMap.get("waterFee");
        electricityFee = retMap.get("electricityFee");
        naturalgasFee = retMap.get("naturalgasFee");
        deposite = retMap.get("deposite");
        repairFee = retMap.get("repairFee");
        freeFee = retMap.get("freeBalance");
		String orderCode = paymentService.generateOrderCode();
		gzfPayment.setOrderCode(orderCode);
		
		GzfPayment refundPayment = new GzfPayment();
		refundPayment.setOrderCode(orderCode);
		refundPayment.setAccountId(gzfPayment.getAccountId());
		refundPayment.setCustId(gzfPayment.getCustId());
		refundPayment.setPhoneNo(gzfPayment.getPhoneNo());
		
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(gzfPayment.getAccountId());
		Page<GzfAcctItem> page = new Page<GzfAcctItem>();
		page.setOrderBy("create_date");
		acctItem.setPage(page);
		if (houseRentFee > 0) {//缴费
			acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
			List<GzfAcctItem> items = acctItemService.findList(acctItem);
			if (items != null && items.size() > 0) {
				gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
				gzfPayment.setAmount(houseRentFee);
				gzfPayment.setEffectDate(items.get(0).getBeginDate());
				gzfPayment.setExpireDate(items.get(items.size() - 1).getEndDate());
				gzfPayment.setTransferId("一次性结清费用特殊处理");//为了不校验金额
				pay(gzfPayment, true);
			}
		}else if (houseRentFee < 0) {//退费
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
			refundFee(refundPayment);
		}
		if (manageFee > 0) {
			acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
			List<GzfAcctItem> items = acctItemService.findList(acctItem);
			if (items != null && items.size() > 0) {
				gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
				gzfPayment.setAmount(manageFee);
				gzfPayment.setEffectDate(items.get(0).getBeginDate());
				gzfPayment.setExpireDate(items.get(items.size() - 1).getBeginDate());
				if (StringUtils.isNoneBlank(gzfPayment.getId())) {
					gzfPayment.setIsNewRecord(true);
					gzfPayment.setId(IdGen.uuid());
				}
				gzfPayment.setTransferId("一次性结清费用特殊处理");//为了一校验金额
				pay(gzfPayment, true);
			}
		}else if (manageFee < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
		if (consumption > 0) {
			acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
			List<GzfAcctItem> items = acctItemService.findList(acctItem);
			if (items != null && items.size() > 0) {
				gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.CONSUMPTION);
				gzfPayment.setAmount(consumption);
				gzfPayment.setEffectDate(items.get(0).getBeginDate());
				gzfPayment.setExpireDate(items.get(items.size() - 1).getBeginDate());
				if (StringUtils.isNoneBlank(gzfPayment.getId())) {
					gzfPayment.setIsNewRecord(true);
					gzfPayment.setId(IdGen.uuid());
				}
				gzfPayment.setTransferId("一次性结清费用特殊处理");
				pay(gzfPayment, true);
			}
		}else if (consumption < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.CONSUMPTION);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.CONSUMPTION);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
		if (waterFee > 0) {
			gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.WATER);
			gzfPayment.setAmount(waterFee);
			if (StringUtils.isNoneBlank(gzfPayment.getId())) {
				gzfPayment.setIsNewRecord(true);
				gzfPayment.setId(IdGen.uuid());
			}
			gzfPayment.setEffectDate(null);
			gzfPayment.setExpireDate(null);
			pay(gzfPayment, true);
		}else if (waterFee < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.WATER);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.WATER);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
		if (electricityFee > 0) {
			gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.ELECTRICITY);
			gzfPayment.setAmount(electricityFee);
			if (StringUtils.isNoneBlank(gzfPayment.getId())) {
				gzfPayment.setIsNewRecord(true);
				gzfPayment.setId(IdGen.uuid());
			}
			gzfPayment.setEffectDate(null);
			gzfPayment.setExpireDate(null);
			pay(gzfPayment, true);
		}else if (electricityFee < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.ELECTRICITY);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
		if (naturalgasFee > 0) {
			gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NATURALGAS);
			gzfPayment.setAmount(naturalgasFee);
			if (StringUtils.isNoneBlank(gzfPayment.getId())) {
				gzfPayment.setIsNewRecord(true);
				gzfPayment.setId(IdGen.uuid());
			}
			gzfPayment.setEffectDate(null);
			gzfPayment.setExpireDate(null);
			pay(gzfPayment, true);
		}else if (naturalgasFee < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NATURALGAS);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.NATURALGAS);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
		if (repairFee > 0) {
			gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE);
			gzfPayment.setAmount(repairFee);
			if (StringUtils.isNoneBlank(gzfPayment.getId())) {
				gzfPayment.setIsNewRecord(true);
				gzfPayment.setId(IdGen.uuid());
			}
			gzfPayment.setEffectDate(null);
			gzfPayment.setExpireDate(null);
			pay(gzfPayment, true);
		}else if (repairFee < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.REPAIR_FEE);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
		if (deposite < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.DEPOSIT);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.DEPOSIT);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
		if (freeFee < 0) {
			refundPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL);
			refundPayment.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.FREE_FEE);
			if (StringUtils.isNoneBlank(refundPayment.getId())) {
				refundPayment.setIsNewRecord(true);
				refundPayment.setId(IdGen.uuid());
			}
			refundFee(refundPayment);
		}
	}
	
	@Transactional(readOnly=true)
	public Map<String, Double> getAcctItemMoney(String accountId){
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(accountId);
		List<GzfAcctItem> items = acctItemService.getSumMoney(acctItem);
		
		double houseRentBalance = 0;
		double managementBalance = 0;
		double waterFeeBalance = 0;
		double electricityFeeBalance = 0;
		double naturalgasFeeBalance = 0;
		double depositeBalance = 0;
		double consumBalance = 0;
		double freeBalance = 0;
		double repairBalance = 0;
		
		List<GzfAccountBalance> balances = balanceService.getGroupedBalanceByAccountId(accountId);
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
		retMap.put("management", management - managementBalance);
		retMap.put("waterFee", waterFee - waterFeeBalance);
		retMap.put("electricityFee", electricityFee - electricityFeeBalance);
		retMap.put("naturalgasFee", naturalgasFee - naturalgasFeeBalance);
		retMap.put("deposite", deposite - depositeBalance);
		retMap.put("consumption", consumption - consumBalance);
		retMap.put("freeBalance", 0 - freeBalance);
		retMap.put("repairFee", repairFee - repairBalance);
		return retMap;
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void refundFee(GzfPayment gzfPayment) throws Exception{
		double total = 0;
		GzfAccountBalance temp = new GzfAccountBalance();
		temp.setAccountId(gzfPayment.getAccountId());
		temp.setAcctItemTypeId(gzfPayment.getAcctItemTypeId());
		List<GzfAccountBalance> balanceList = balanceService.findList(temp);
		if (balanceList == null || balanceList.size() == 0) {
			return;
		}
		Date sysdate = new Date();
		List<GzfBalanceRecord> balanceRecords = new ArrayList<GzfBalanceRecord>();
		for(int i = 0; i < balanceList.size(); i++){
			GzfAccountBalance balance = balanceList.get(i);
			if (GzfAccountBalance.DEL_FLAG_DELETE.equals(balance.getDelFlag()) || balance.getBalance() == 0) {
				continue;
			}
			total += balance.getBalance();
			//余额支出明细
			GzfBalanceRecord record = new GzfBalanceRecord();
			record.setAccountBalanceId(balance.getId());
			record.setAccountId(balance.getAccountId());
			record.setAmount(0 - balance.getBalance());
			record.setBalance(balance.getBalance());
			record.setBalanceTypeId(balance.getBalanceTypeId());
			record.setOperType(PaymentConst.OPER_TYPE.EXPENSE);//1－收入 2－支出
			record.setPaymentId(gzfPayment.getId());
			record.setAcctItemTypeId(balance.getAcctItemTypeId());
			record.setSpePaymentId(balance.getSpePaymentId());
			record.setOptCode(PaymentConst.OPT_CODE.REFUND);
			record.setTotalBalance(record.getBalance());
			balanceRecords.add(record);
			
			balance.setExpireDate(sysdate);
			balance.setBalance(new Double(0));
		}
		balanceService.deleteList(balanceList, true);
		balanceRecordService.saveList(balanceRecords);
		
		if (StringUtils.isBlank(gzfPayment.getOrderCode())) {
			String loginName = "admin";
			User user = UserUtils.getUser();
			if (StringUtils.isNotBlank(user.getId())){
				loginName = user.getLoginName();
			}
			String orderCode = loginName + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
			gzfPayment.setOrderCode(orderCode);
		}
		gzfPayment.setAmount(0-total);
		gzfPayment.setOptCode(PaymentConst.OPT_CODE.REFUND);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setState(PaymentConst.PAYMENT_STATE.PAYED);
		paymentService.save(gzfPayment);
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void generateWaterAcctItemAndWriteOff(GzfWaterMeter last, GzfWaterMeter current) throws Exception{
		if (last == null) {//不存在上次录入记录，说明是第一次录入，此时不生成帐单
            return;
        }
        if (current == null) {
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
        
		acctItemService.generateWaterAcctItem(last, current);
		writeOffByAccountIdAndItemType(accountId, PaymentConst.ACCT_ITEM_TYPE.WATER);
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void generateElectricAcctItemAndWriteOff(GzfElectric last, GzfElectric current) throws Exception{
		if (last == null) {//不存在上次录入记录，说明是第一次录入，此时不生成帐单
            return;
        }
        if (current == null) {
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
        
		acctItemService.generateElectricAcctItem(last, current);
		writeOffByAccountIdAndItemType(accountId, PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY);
	}
	
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void generateNatureGasAcctItemAndWriteOff(GzfNaturalGas last, GzfNaturalGas current) throws Exception{
		if (last == null) {//不存在上次录入记录，说明是第一次录入，此时不生成帐单
            return;
        }
        if (current == null) {
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
        
		acctItemService.generateNatureGasAcctItem(last, current);
		writeOffByAccountIdAndItemType(accountId, PaymentConst.ACCT_ITEM_TYPE.NATURALGAS);
	}
}
