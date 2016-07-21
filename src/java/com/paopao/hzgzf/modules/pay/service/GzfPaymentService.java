package com.paopao.hzgzf.modules.pay.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.dao.GzfAcctItemMapper;
import com.paopao.hzgzf.modules.pay.dao.GzfPaymentMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItem;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceRecord;
import com.paopao.hzgzf.modules.pay.entity.GzfPayment;
import com.paopao.hzgzf.modules.pay.entity.GzfPaymethodBalancetype;
import com.paopao.hzgzf.modules.pay.entity.GzfSpecialPayment;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly=true)
public class GzfPaymentService extends CrudService<GzfPaymentMapper, GzfPayment>{
	@Autowired
	GzfSpecialPaymentService specialPaymentService;
	@Autowired
	GzfBalanceTypeService balanceTypeService;
	@Autowired
	GzfAcctItemMapper acctItemDao;//未销帐帐单dao
	@Autowired
	GzfAccountBalanceService balanceService;
	@Autowired
	GzfBalanceRecordService balanceRecordService;
	@Autowired
	GzfPaymentOrderService paymentOrderService;
	@Autowired
	GzfPaymethodBalancetypeService paymethodBalancetypeService;
	@Autowired
	GzfAccountService accountService;
	@Autowired
	GzfHousePersonService housePersonService;
	@Autowired
	GzfHouseholdInfoService householdInfoService;
	@Autowired
	GzfPaymentStandardService paymentStandardService;
	@Autowired
	GzfHouseInfoService houseInfoService;
	
	public GzfPayment get(String id){
		return super.get(id);
	}
	
	public GzfPayment get(GzfPayment entity){
		return super.get(entity);
	}
	
	public List<GzfPayment> findList(GzfPayment entity){
		return super.findList(entity);
	}
	
	public Page<GzfPayment> findPage(Page<GzfPayment> page, GzfPayment entity){
		return super.findPage(page, entity);
	}
	
	@Transactional(readOnly=false)
	public void save(GzfPayment entity){
		super.saveNew(entity);
	}
	
	@Transactional(readOnly=false)
	public void delete(GzfPayment entity){
		super.delete(entity);
	}
	
	public List<GzfPayment> getPaymentByAccountId(String accountId){
		return dao.getPaymentByAccountId(accountId);
	}
	
	//根据帐户查询缴费信息
	public List<GzfPayment> getPaymentByAccount(GzfAccount account){
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		return findList(payment);
	}
	
	//缴费
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public GzfPayment pay(GzfPayment gzfPayment) throws Exception{
		if (gzfPayment.getAmount() == 0) {
			throw new Exception("缴费金额不能为0");
		}
		if(StringUtils.isBlank(gzfPayment.getOrderCode())){
			String orderCode = generateOrderCode();
			gzfPayment.setOrderCode(orderCode);
		}
		String optCode = gzfPayment.getOptCode();
		if (StringUtils.isBlank(optCode)) {
			optCode = PaymentConst.OPT_CODE.PAY;
			gzfPayment.setOptCode(optCode);
		}
		String payMethod = gzfPayment.getPayMethod();
		if (StringUtils.isBlank(payMethod)) {
			payMethod = PaymentConst.PAY_METHOD.CASH;
			gzfPayment.setPayMethod(payMethod);
		}
		String channelType = gzfPayment.getChannelType();//此处之后改为从用户获取
		if (StringUtils.isBlank(channelType)) {
			channelType = PaymentConst.CHANNEL_TYPE.OFFICE;
			gzfPayment.setChannelType(channelType);
		}
		int balanceType = PaymentConst.BALANCE_TYPE.ALLOW_ALL;
		GzfPaymethodBalancetype paymethodBalancetype = new GzfPaymethodBalancetype();
		paymethodBalancetype.setPayMethod(payMethod);
		paymethodBalancetype.setChannelType(channelType);
		List<GzfPaymethodBalancetype> temp = paymethodBalancetypeService.findList(paymethodBalancetype);
		if (temp != null && temp.size() > 0) {
			balanceType = temp.get(0).getBalanceTypeId();
		}
		//在外围已转成分
//		gzfPayment.setAmount(gzfPayment.getAmount() * 100);
		int spePaymentId = gzfPayment.getSpePaymentId();
		int acctItemTypeId = PaymentConst.ACCT_ITEM_TYPE.FREE_FEE;
		if (spePaymentId > 0) {//是专项的缴费
			GzfSpecialPayment specialPayment = specialPaymentService.get(String.valueOf(spePaymentId));
			if (specialPayment != null) {
				acctItemTypeId = specialPayment.getAcctItemTypeId();
			}
		}
		//取acct_item_type_id balance_type
		//先查询是否有未销帐帐单，有的话，之后销帐
		double oweFee = 0;
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(gzfPayment.getAccountId());
		acctItem.setAcctItemTypeId(acctItemTypeId);
		List<GzfAcctItem> acctItems = acctItemDao.findList(acctItem);
		if (acctItems != null && !acctItems.isEmpty()) {
			for(int i = 0; i < acctItems.size(); i++){
				GzfAcctItem acctItemTemp = acctItems.get(i);
				double receAmount = acctItemTemp.getReceAmount();
				double factAmount = acctItemTemp.getFactAmount();
				oweFee += (receAmount - factAmount);
			}
		}
		gzfPayment.setBalanceTypeId(balanceType);
		gzfPayment.setOweFee(oweFee);
		gzfPayment.setState(PaymentConst.PAYMENT_STATE.PAYED);
		gzfPayment.setAcctItemTypeId(acctItemTypeId);
		String custId = gzfPayment.getCustId();
		if (StringUtils.isBlank(custId)) {
			GzfAccount account = accountService.get(gzfPayment.getAccountId());
			if (account == null) {
				throw new Exception("根据帐户id" + gzfPayment.getAccountId() + "查询不到帐户！");
			}
			custId = account.getCustId();
			gzfPayment.setCustId(custId);
		}
		GzfHousePerson housePerson = null;
		//处理生效日期
		dealPaymentEffectDate(gzfPayment, housePerson);
		//处理失效日期
		dealPaymentExpireDate(gzfPayment, housePerson);
		//判断金额是否正确
		if (StringUtils.isBlank(gzfPayment.getTransferId()) && (spePaymentId == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT 
				|| spePaymentId == PaymentConst.SPECIAL_PAYMENT.MANAGEMENT
				|| spePaymentId == PaymentConst.SPECIAL_PAYMENT.CONSUMPTION)) {
			if (housePerson == null) {
				housePerson = housePersonService.get(custId);
			}
			double amount = getReceFeeByDate(gzfPayment.getEffectDate(), DateUtils.addOrMinusMinutes(gzfPayment.getExpireDate().getTime(), 1), housePerson, spePaymentId);
			if (amount != gzfPayment.getAmount().intValue()) {
				throw new Exception("金额不正确！");
			}
		}
		//保存缴费记录
		save(gzfPayment);
		
		double initTotal = 0;
		GzfAccountBalance temp2 = new GzfAccountBalance();
		temp2.setAccountId(gzfPayment.getAccountId());
		temp2.setSpePaymentId(spePaymentId);
		GzfAccountBalance total = balanceService.getSumBalanceByAcctIdAndSpecId(temp2);
		if(total != null){
			initTotal = total.getBalance();
		}
		
		//存入余额,余额收支明细
		GzfAccountBalance balance = new GzfAccountBalance();
		balance.setAccountId(gzfPayment.getAccountId());
		balance.setAcctItemTypeId(acctItemTypeId);
		balance.setSpePaymentId(spePaymentId);
		if(gzfPayment.getAmount() > 0){
			balance.setBalance(gzfPayment.getAmount());
			balance.setBalanceTypeId(balanceType);
			balance.setEffectDate(gzfPayment.getEffectDate());
			balance.setExpireDate(gzfPayment.getExpireDate());
			balance.setInitBalance(gzfPayment.getAmount());
			balance.setPaymentId(gzfPayment.getId());
			balanceService.save(balance);
			
			//余额收入明细
			GzfBalanceRecord record = new GzfBalanceRecord();
			record.setAccountBalanceId(balance.getId());
			record.setAccountId(balance.getAccountId());
			record.setAmount(gzfPayment.getAmount());
			record.setBalanceTypeId(balanceType);
			record.setBalance(gzfPayment.getAmount());
			record.setOperType(PaymentConst.OPER_TYPE.INCOME);//收入
			record.setPaymentId(gzfPayment.getId());
			record.setAcctItemTypeId(acctItemTypeId);
			record.setSpePaymentId(balance.getSpePaymentId());
			record.setOptCode(optCode);
			record.setTotalBalance(initTotal + gzfPayment.getAmount());
			balanceRecordService.save(record);
		}else {//支出，需要从余额中扣
			double needDec = 0 - gzfPayment.getAmount();
			List<GzfAccountBalance> balanceList = new ArrayList<GzfAccountBalance>();
			List<GzfBalanceRecord> balanceRecordList = new ArrayList<GzfBalanceRecord>();
			List<GzfAccountBalance> balances = balanceService.findList(balance);
			for(int i = 0; i < balances.size(); i++){
				GzfAccountBalance decBalance = balances.get(i);
				double oldBalance = decBalance.getBalance();
				if (oldBalance > needDec) {
					decBalance.setBalance(oldBalance - needDec);
					needDec = 0;
				}else {
					needDec = needDec - oldBalance;
					decBalance.setBalance(new Double(0));
					decBalance.setDelFlag(GzfAccountBalance.DEL_FLAG_DELETE);
				}
				balanceList.add(decBalance);
				
				//余额支出明细
				GzfBalanceRecord record = new GzfBalanceRecord();
				record.setAccountBalanceId(decBalance.getId());
				record.setAccountId(balance.getAccountId());
				record.setAmount(decBalance.getBalance() - oldBalance);
				record.setBalanceTypeId(balanceType);
				record.setBalance(oldBalance + record.getAmount());
				record.setOperType(PaymentConst.OPER_TYPE.EXPENSE);//支出
				record.setPaymentId(gzfPayment.getId());
				record.setAcctItemTypeId(acctItemTypeId);
				record.setSpePaymentId(balance.getSpePaymentId());
				record.setOptCode(optCode);
				record.setTotalBalance(initTotal + record.getAmount());
				balanceRecordList.add(record);
				initTotal = initTotal + record.getAmount();
				if (needDec == 0) {
					break;
				}
			}
			if (balanceList.size() > 0) {
				balanceService.saveList(balanceList);
			}
			if (balanceRecordList.size() > 0) {
				balanceRecordService.saveList(balanceRecordList);
			}
		}
		
		//销帐
//		if (oweFee > 0) {
//			writeOff(acctItems, acctItemTypeId);
//		}
		return gzfPayment;
	}
	
	private void dealPaymentEffectDate(GzfPayment gzfPayment, GzfHousePerson housePerson) throws Exception{
		int spePaymentId = gzfPayment.getSpePaymentId();
		if (gzfPayment.getEffectDate() == null) {
			Date effectDate = null;
			if (PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE == spePaymentId || PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL == spePaymentId) {
				effectDate = new Date();
			}else {
				if (StringUtils.isNoneBlank(gzfPayment.getEffectDateStr())) {
					effectDate = DateUtils.getBeginDateOfDay(DateUtils.parseDate(gzfPayment.getEffectDateStr()));
				}else {
					if (housePerson == null) {
						housePerson = housePersonService.get(gzfPayment.getCustId());				
					}
					effectDate = getEffectDate(housePerson, gzfPayment.getSpePaymentId());
				}
			}
			gzfPayment.setEffectDate(effectDate);
		}
	}
	
	private void dealPaymentExpireDate(GzfPayment gzfPayment, GzfHousePerson housePerson) throws Exception{
		Date expireDate = DateUtils.getDefaultExpireDate();
		if (gzfPayment.getExpireDate() != null) {
			expireDate = DateUtils.getPreLastDateOfDay(gzfPayment.getExpireDate());
//			expireDate = DateUtils.getEndDateOfDay(gzfPayment.getExpireDate());
		}else {
			int spePaymentId = gzfPayment.getSpePaymentId();
			if (PaymentConst.SPECIAL_PAYMENT.DEPOSIT != spePaymentId && PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE != spePaymentId
					&& PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL != spePaymentId) {
				if (housePerson == null) {
					housePerson = housePersonService.get(gzfPayment.getCustId());
				}
				expireDate = getExpireDate(gzfPayment.getEffectDate(), gzfPayment.getAmount(), housePerson, gzfPayment.getSpePaymentId());
			}
		}
		gzfPayment.setExpireDate(expireDate);
	}
	
	/**
	 * 退房时把所有费用都退掉
	 * @param accountId
	 * @throws Exception
	 */
	@Transactional(readOnly=false, rollbackFor={RuntimeException.class, Exception.class})
	public void refundAll(String accountId) throws Exception{
		GzfAccountBalance temp = new GzfAccountBalance();
		temp.setAccountId(accountId);
		List<GzfAccountBalance> balanceList = balanceService.findList(temp);
		if (balanceList == null || balanceList.size() == 0) {
			return;
		}
		List<GzfBalanceRecord> balanceRecords = new ArrayList<GzfBalanceRecord>();
		for(int i = 0; i < balanceList.size(); i++){
			GzfAccountBalance balance = balanceList.get(i);
			if (GzfAccountBalance.DEL_FLAG_DELETE.equals(balance.getDelFlag()) || balance.getBalance() == 0) {
				continue;
			}
			//余额支出明细
			GzfBalanceRecord record = new GzfBalanceRecord();
			record.setAccountBalanceId(balance.getId());
			record.setAccountId(balance.getAccountId());
			record.setAmount(0 - balance.getBalance());
			record.setBalance(balance.getBalance());
			record.setBalanceTypeId(balance.getBalanceTypeId());
			record.setOperType(PaymentConst.OPER_TYPE.EXPENSE);//1－收入 2－支出
			record.setPaymentId(balance.getPaymentId());
			record.setAcctItemTypeId(balance.getAcctItemTypeId());
			record.setSpePaymentId(balance.getSpePaymentId());
			record.setOptCode(PaymentConst.OPT_CODE.REFUND);
			record.setTotalBalance(record.getBalance());
			balanceRecords.add(record);
			
			balance.setBalance(new Double(0));
		}
		balanceService.deleteList(balanceList, true);
		balanceRecordService.saveList(balanceRecords);
	}
	
	//生成orderCode
	public String generateOrderCode() throws Exception{
		String loginName = "admin";
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			loginName = user.getLoginName();
		}
		String orderCode = loginName + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		return orderCode;
	}
	
	//根据生失效时间，计算应缴纳金额
	@Transactional(readOnly=true)
	public double getReceFeeByDate(Date beginDate, Date endDate, GzfHousePerson housePerson, int specPaymentId) throws Exception{
		if (specPaymentId != PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT && specPaymentId != PaymentConst.SPECIAL_PAYMENT.CONSUMPTION
				&& specPaymentId != PaymentConst.SPECIAL_PAYMENT.MANAGEMENT) {
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
		if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
			unitPrice = paymentStandard.getRentUnitPrice();
		}else if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.CONSUMPTION) {
			unitPrice = paymentStandard.getConsumption();
		}else if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.MANAGEMENT) {
			unitPrice = paymentStandard.getManagementFee();
		}/*else if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.DEPOSIT) {
			
		}*/
		unitPrice = unitPrice * 100 * houseInfo.getUseArea();//以分为单位
		double months = DateUtils.getDistanceMonthOfTwoDate(beginDate, endDate);
		double days = DateUtils.getDistanceDayOfTwoDate(beginDate, endDate);
		double maxDay = DateUtils.getMaxday(endDate);
		amount = unitPrice * (months + days/maxDay);
		return Double.valueOf(amount).intValue();
	}
	
	//根据生失效时间，计算应缴纳金额
	@Transactional(readOnly=true)
	public double getReceFeeByNum(Double beginNum, Double endNum, GzfHousePerson housePerson, int specPaymentId) throws Exception{
		if (specPaymentId != PaymentConst.SPECIAL_PAYMENT.WATER && specPaymentId != PaymentConst.SPECIAL_PAYMENT.ELECTRICITY
				&& specPaymentId != PaymentConst.SPECIAL_PAYMENT.NATURALGAS) {
			throw new Exception("只能计算水、电、天燃气费用！");
		}
		if (housePerson == null) {
			throw new Exception("租赁关系不能为空！");
		}
		double num = endNum - beginNum;
		if (num == 0) {
			return 0;
		}
		if (num < 0) {
			throw new Exception("用量小于0,请确认数据是否正确!");
		}
		//人员信息
		GzfHouseholdInfo householdInfo = housePerson.getGzfHouseholdInfo();
		if(householdInfo == null || StringUtils.isBlank(householdInfo.getId())){
			householdInfo = householdInfoService.get(housePerson.getGzfHouseholdInfoId());
			housePerson.setGzfHouseholdInfo(householdInfo);
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
		if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.WATER) {
			unitPrice = paymentStandard.getWaterFee();
		}else if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.ELECTRICITY) {
			unitPrice = paymentStandard.getElectricityFee();
		}else if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.NATURALGAS) {
			unitPrice = paymentStandard.getNaturalgasFee();
		}
		amount = unitPrice * 100 * num;//以分为单位
		return amount;
	}
	
	//计算生效日期
	@Transactional(readOnly=true)
	public Date getEffectDate(GzfHousePerson housePerson, int specPaymentId) throws Exception{
		if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.DEPOSIT || specPaymentId == PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL
				|| specPaymentId == PaymentConst.SPECIAL_PAYMENT.WATER || specPaymentId == PaymentConst.SPECIAL_PAYMENT.ELECTRICITY
				|| specPaymentId == PaymentConst.SPECIAL_PAYMENT.NATURALGAS) {
			return DateUtils.getBeginDateOfDay(new Date());
		}
		if (housePerson == null) {
			throw new Exception("租赁关系不能为空！");
		}
		Date effectDate = balanceService.getFeeExpireDate(housePerson, specPaymentId);
		Date effectDay = DateUtils.addOrMinusDays(effectDate.getTime(), 1);
		return DateUtils.getBeginDateOfDay(effectDay);
	}
	
	//计算失效日期
	@Transactional(readOnly=true)
	public Date getExpireDate(Date effectDate, double amount, GzfHousePerson housePerson, int specPaymentId) throws Exception{
		if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.DEPOSIT || specPaymentId == PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL
				|| specPaymentId == PaymentConst.SPECIAL_PAYMENT.WATER || specPaymentId == PaymentConst.SPECIAL_PAYMENT.ELECTRICITY
				|| specPaymentId == PaymentConst.SPECIAL_PAYMENT.NATURALGAS) {
			return DateUtils.getDefaultExpireDate();
		}
		if (housePerson == null) {
			throw new Exception("租赁关系不能为空！");
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
		double unitPrice = 0;
		if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
			unitPrice = paymentStandard.getRentUnitPrice();
		}else if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.CONSUMPTION) {
			unitPrice = paymentStandard.getConsumption();
		}else if (specPaymentId == PaymentConst.SPECIAL_PAYMENT.MANAGEMENT) {
			unitPrice = paymentStandard.getManagementFee();
		}
		unitPrice = unitPrice * 100 * houseInfo.getUseArea();//以分为单位
		int months = Double.valueOf(amount/unitPrice).intValue();
		
		Date tempDate = DateUtils.addOrMinusMonth(effectDate.getTime(), months);
		double feeOneDay = unitPrice/DateUtils.getMaxday(tempDate);
		int days = Double.valueOf((amount-months*unitPrice)/feeOneDay).intValue();
		if ((int)((amount-months*unitPrice)%feeOneDay) > 0) {
			days++;
		}
		tempDate = DateUtils.addOrMinusDays(tempDate.getTime(), days);
//			return DateUtils.getEndDateOfDay(tempDate);
		return DateUtils.getPreLastDateOfDay(tempDate);
	}
}
