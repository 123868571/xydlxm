package com.paopao.hzgzf.modules.pay.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItem;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransfer;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceRecord;
import com.paopao.hzgzf.modules.pay.entity.GzfPayment;
import com.paopao.hzgzf.modules.pay.service.DealBillService;
import com.paopao.hzgzf.modules.pay.service.GzfAccountBalanceHisService;
import com.paopao.hzgzf.modules.pay.service.GzfAccountBalanceService;
import com.paopao.hzgzf.modules.pay.service.GzfAccountService;
import com.paopao.hzgzf.modules.pay.service.GzfAcctItemService;
import com.paopao.hzgzf.modules.pay.service.GzfBalanceRecordService;
import com.paopao.hzgzf.modules.pay.service.GzfPaymentService;
@Controller
@RequestMapping(value="${adminPath}/pay/gzfAccount")
public class GzfAccountController extends BaseController{

	@Autowired
	GzfAccountService accountService;
	@Autowired
	GzfAccountBalanceService balanceService;
	@Autowired
	GzfAccountBalanceHisService balanceHisService;
	@Autowired
	GzfPaymentService paymentService;
	@Autowired
	GzfAcctItemService acctItemService;//帐单服务
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
	
	@Autowired
	GzfBalanceRecordService balanceRecordService;
	@Autowired
	DealBillService dealBillService;
	
	
	@ModelAttribute
	public GzfAccount get(@RequestParam(required=false)String id){
		GzfAccount entity = null;
		if (StringUtils.isNoneBlank(id)) {
			entity = accountService.get(id);
		}else {
			entity = new GzfAccount();
		}
		return entity;
	}
	
	
	@RequestMapping(value="houseRent")
	public String houseRentHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/houseRentPay";
	}
	
	@RequestMapping(value="getHouseRentHomePageInfo")
	public String getHouseRentHomePageInfo(@ModelAttribute GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return houseRentHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return houseRentHomePage(account, model);
		}
		account = accountList.get(0);
		//缴费历史
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		payment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		Date date = new Date();
		payment.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		payment.setEndTime(date);
		Page<GzfPayment> payList = paymentService.findPage(new Page<GzfPayment>(request, response), payment);
		//帐单
//		List<GzfAcctItem> acctItemList = acctItemService.getListByAccount(account);
		//余额
//		List<GzfAccountBalance> balanceList = balanceService.getGroupedBalanceByAccountId(account.getId());
		
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return houseRentHomePage(account, model);
		}
		account.setHousePerson(housePerson);
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
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
			householdInfo.setGzfHouseInfo(houseInfo);
		}
		GzfPaymentStandard paymentStandard = householdInfo.getGzfPaymentStandard();
		if (paymentStandard == null || StringUtils.isBlank(paymentStandard.getId())) {
			paymentStandard = payStandardService.get(householdInfo.getGzfPaymentStandardId());
			householdInfo.setPaymentStandard(paymentStandard);
		}
		double rentUnitPrice = 0;
		if (paymentStandard != null) {
			rentUnitPrice = paymentStandard.getRentUnitPrice();
		}
		double rentFee = rentUnitPrice*100*(houseInfo.getUseArea());//以分为单位
		account.setFee(new BigDecimal(rentFee).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue());//转换为元
		
		//费用到期日期
		Date feeExpireDate = balanceService.getFeeExpireDate(housePerson, PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		GzfAccountBalance balance = new GzfAccountBalance();
		balance.setExpireDate(feeExpireDate);
				
		model.addAttribute("gzfAccount", account);
//		model.addAttribute("balanceList", balanceList);
		model.addAttribute("payList", payList);
//		model.addAttribute("acctItemList", acctItemList);
		model.addAttribute("householdInfo", householdInfo);
		model.addAttribute("lastBalance", balance);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setAmount(account.getFee());
		gzfPayment.setAmountYuan(account.getFee() + "");
		gzfPayment.setPhoneNo(account.getPhoneNo());
		Date effectDate = new Date();
		if (feeExpireDate != null) {
			effectDate = DateUtils.addOrMinusDays(feeExpireDate.getTime(),1);
		}
		effectDate = DateUtils.getBeginDateOfDay(effectDate);
		gzfPayment.setEffectDateStr(DateUtils.formatDate(effectDate));
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/houseRentPay";
	}
	
	
	//房租缴费跳转@RequestParam(value="feeExpireDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date feeExpireDate
	@RequestMapping(value="houseRentPayForm")
	public String houseRentPayForm(GzfPayment gzfPayment, HttpServletRequest request, Model model){
//		gzfPayment.setAmount(gzfPayment.getAmount());
//		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		gzfPayment.setPayMethod("1");
		String effectDateStr = gzfPayment.getEffectDateStr();
		if (StringUtils.isNoneBlank(effectDateStr)) {
			gzfPayment.setExpireDate(DateUtils.addOrMinusMonth(DateUtils.parseDate(effectDateStr).getTime(), 1));
		}
		return "modules/pay/houseRentPayForm";
	}
	
	
	@RequestMapping(value="waterFee")
	public String waterFeeHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.WATER);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payWaterFee";
	}
	
	@RequestMapping(value="getWaterFeeHomePageInfo")
	public String getWaterFeeHomePageInfo(@ModelAttribute GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return waterFeeHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return waterFeeHomePage(account, model);
		}
		account = accountList.get(0);
		//缴费历史
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		payment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.WATER);
		Date date = new Date();
		payment.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		payment.setEndTime(date);
		Page<GzfPayment> payList = paymentService.findPage(new Page<GzfPayment>(request, response), payment);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return waterFeeHomePage(account, model);
		}
		account.setHousePerson(housePerson);
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
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
			householdInfo.setGzfHouseInfo(houseInfo);
		}
		GzfPaymentStandard paymentStandard = householdInfo.getGzfPaymentStandard();
		if (paymentStandard == null || StringUtils.isBlank(paymentStandard.getId())) {
			paymentStandard = payStandardService.get(householdInfo.getGzfPaymentStandardId());
			householdInfo.setPaymentStandard(paymentStandard);
		}
		
		double receMoney = 0;
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(account.getId());
		acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.WATER);
//		List<GzfAcctItem> acctItemList = acctItemService.getSumMoney(acctItem);
//		if (acctItemList != null && acctItemList.size() == 1) {
//			receMoney = acctItemList.get(0).getReceAmount() - acctItemList.get(0).getFactAmount();
//		}
		String usage = "";
		List<GzfAcctItem> acctItemList = acctItemService.findList(acctItem);
		if(acctItemList != null && acctItemList.size() > 0){
			String firstRecord = "";
			String endRecord = "";
			for(int i = 0; i < acctItemList.size(); i++){
				receMoney += acctItemList.get(i).getReceAmount() - acctItemList.get(i).getFactAmount();
				if (StringUtils.isNoneBlank(acctItemList.get(i).getRemarks())) {
//					if (StringUtils.isBlank(firstRecord) ) {
//						firstRecord = acctItemList.get(i).getRemarks();
//					}
//					endRecord = acctItemList.get(i).getRemarks();
					//查询结果改成倒序排序了
					if (StringUtils.isBlank(endRecord) ) {
						endRecord = acctItemList.get(i).getRemarks();
					}
					firstRecord = acctItemList.get(i).getRemarks();
				}
			}
			if (StringUtils.isNoneBlank(firstRecord) && StringUtils.isNoneBlank(endRecord) && !firstRecord.equals(endRecord)) {
				String[] str1 = firstRecord.split("-");
				String[] str2 = endRecord.split("-");
				if (str1.length == 2 && str2.length == 2) {
					usage = str1[0] + "-" + str2[1];
				}
			}
			if (StringUtils.isBlank(usage)) {
				usage = endRecord;
			}
		}
		account.setRemarks(usage);
		account.setFee(new BigDecimal(receMoney).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue());//转换为元
		model.addAttribute("gzfAccount", account);
		model.addAttribute("payList", payList);
		model.addAttribute("householdInfo", householdInfo);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.WATER);
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setAmount(account.getFee());
		gzfPayment.setAmountYuan(account.getFee() + "");
		gzfPayment.setPhoneNo(account.getPhoneNo());
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payWaterFee";
	}
	
	@RequestMapping(value="electricityFee")
	public String electricityFeeHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.ELECTRICITY);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payElectricityFee";
	}
	
	@RequestMapping(value="getElectricityFeeHomePageInfo")
	public String getElectricityFeeHomePageInfo(@ModelAttribute GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return electricityFeeHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return electricityFeeHomePage(account, model);
		}
		account = accountList.get(0);
		//缴费历史
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		payment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.ELECTRICITY);
		Date date = new Date();
		payment.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		payment.setEndTime(date);
		Page<GzfPayment> payList = paymentService.findPage(new Page<GzfPayment>(request, response), payment);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return electricityFeeHomePage(account, model);
		}
		account.setHousePerson(housePerson);
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
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
			householdInfo.setGzfHouseInfo(houseInfo);
		}
		GzfPaymentStandard paymentStandard = householdInfo.getGzfPaymentStandard();
		if (paymentStandard == null || StringUtils.isBlank(paymentStandard.getId())) {
			paymentStandard = payStandardService.get(householdInfo.getGzfPaymentStandardId());
			householdInfo.setPaymentStandard(paymentStandard);
		}
		
		double receMoney = 0;
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(account.getId());
		acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY);
//		List<GzfAcctItem> acctItemList = acctItemService.getSumMoney(acctItem);
//		if (acctItemList != null && acctItemList.size() == 1) {
//			receMoney = acctItemList.get(0).getReceAmount() - acctItemList.get(0).getFactAmount();
//		}
		String usage = "";
		List<GzfAcctItem> acctItemList = acctItemService.findList(acctItem);
		if(acctItemList != null && acctItemList.size() > 0){
			String firstRecord = "";
			String endRecord = "";
			for(int i = 0; i < acctItemList.size(); i++){
				receMoney += acctItemList.get(i).getReceAmount() - acctItemList.get(i).getFactAmount();
				if (StringUtils.isNoneBlank(acctItemList.get(i).getRemarks())) {
//					if (StringUtils.isBlank(firstRecord) ) {
//						firstRecord = acctItemList.get(i).getRemarks();
//					}
//					endRecord = acctItemList.get(i).getRemarks();
					//查询结果改成倒序排序了
					if (StringUtils.isBlank(endRecord) ) {
						endRecord = acctItemList.get(i).getRemarks();
					}
					firstRecord = acctItemList.get(i).getRemarks();
				}
			}
			if (StringUtils.isNoneBlank(firstRecord) && StringUtils.isNoneBlank(endRecord) && !firstRecord.equals(endRecord)) {
				String[] str1 = firstRecord.split("-");
				String[] str2 = endRecord.split("-");
				if (str1.length == 2 && str2.length == 2) {
					usage = str1[0] + "-" + str2[1];
				}
			}
			if (StringUtils.isBlank(usage)) {
				usage = endRecord;
			}
		}
		account.setRemarks(usage);
		account.setFee(new BigDecimal(receMoney).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue());//转换为元
		model.addAttribute("gzfAccount", account);
		model.addAttribute("payList", payList);
		model.addAttribute("householdInfo", householdInfo);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.ELECTRICITY);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setAmount(account.getFee());
		gzfPayment.setAmountYuan(account.getFee() + "");
		gzfPayment.setPhoneNo(account.getPhoneNo());
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payElectricityFee";
	}
	
	@RequestMapping(value="naturalgasFee")
	public String naturalgasFeeHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NATURALGAS);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payNaturalgasFee";
	}
	
	@RequestMapping(value="getNaturalgasFeeHomePageInfo")
	public String getNaturalgasFeeHomePageInfo(@ModelAttribute GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return naturalgasFeeHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return naturalgasFeeHomePage(account, model);
		}
		account = accountList.get(0);
		//缴费历史
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		payment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NATURALGAS);
		Date date = new Date();
		payment.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		payment.setEndTime(date);
		Page<GzfPayment> payList = paymentService.findPage(new Page<GzfPayment>(request, response), payment);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return naturalgasFeeHomePage(account, model);
		}
		account.setHousePerson(housePerson);
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
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
			householdInfo.setGzfHouseInfo(houseInfo);
		}
		GzfPaymentStandard paymentStandard = householdInfo.getGzfPaymentStandard();
		if (paymentStandard == null || StringUtils.isBlank(paymentStandard.getId())) {
			paymentStandard = payStandardService.get(householdInfo.getGzfPaymentStandardId());
			householdInfo.setPaymentStandard(paymentStandard);
		}
		
		double receMoney = 0;
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(account.getId());
		acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.NATURALGAS);
//		List<GzfAcctItem> acctItemList = acctItemService.getSumMoney(acctItem);
//		if (acctItemList != null && acctItemList.size() == 1) {
//			receMoney = acctItemList.get(0).getReceAmount() - acctItemList.get(0).getFactAmount();
//		}
		String usage = "";
		List<GzfAcctItem> acctItemList = acctItemService.findList(acctItem);
		if(acctItemList != null && acctItemList.size() > 0){
			String firstRecord = "";
			String endRecord = "";
			for(int i = 0; i < acctItemList.size(); i++){
				receMoney += acctItemList.get(i).getReceAmount() - acctItemList.get(i).getFactAmount();
				if (StringUtils.isNoneBlank(acctItemList.get(i).getRemarks())) {
//					if (StringUtils.isBlank(firstRecord) ) {
//						firstRecord = acctItemList.get(i).getRemarks();
//					}
//					endRecord = acctItemList.get(i).getRemarks();
					//查询结果改成倒序排序了
					if (StringUtils.isBlank(endRecord) ) {
						endRecord = acctItemList.get(i).getRemarks();
					}
					firstRecord = acctItemList.get(i).getRemarks();
				}
			}
			if (StringUtils.isNoneBlank(firstRecord) && StringUtils.isNoneBlank(endRecord) && !firstRecord.equals(endRecord)) {
				String[] str1 = firstRecord.split("-");
				String[] str2 = endRecord.split("-");
				if (str1.length == 2 && str2.length == 2) {
					usage = str1[0] + "-" + str2[1];
				}
			}
			if (StringUtils.isBlank(usage)) {
				usage = endRecord;
			}
		}
		account.setRemarks(usage);
		account.setFee(new BigDecimal(receMoney).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue());//转换为元
		model.addAttribute("gzfAccount", account);
		model.addAttribute("payList", payList);
		model.addAttribute("householdInfo", householdInfo);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NATURALGAS);
		gzfPayment.setAmount(account.getFee());
		gzfPayment.setAmountYuan(account.getFee() + "");
		gzfPayment.setPhoneNo(account.getPhoneNo());
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payNaturalgasFee";
	}
	
	@RequestMapping(value="managementFee")
	public String managementFeeHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payManagementFee";
	}
	
	@RequestMapping(value="getManagementFeeHomePageInfo")
	public String getManagementFeeHomePageInfo(@ModelAttribute GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return managementFeeHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return managementFeeHomePage(account, model);
		}
		account = accountList.get(0);
		//缴费历史
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		payment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
		Date date = new Date();
		payment.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		payment.setEndTime(date);
		Page<GzfPayment> payList = paymentService.findPage(new Page<GzfPayment>(request, response), payment);
		
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return managementFeeHomePage(account, model);
		}
		account.setHousePerson(housePerson);
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
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
			householdInfo.setGzfHouseInfo(houseInfo);
		}
		GzfPaymentStandard paymentStandard = householdInfo.getGzfPaymentStandard();
		if (paymentStandard == null || StringUtils.isBlank(paymentStandard.getId())) {
			paymentStandard = payStandardService.get(householdInfo.getGzfPaymentStandardId());
			householdInfo.setPaymentStandard(paymentStandard);
		}
		
		double unitPrice = 0;
		double consumUnitPrice = 0;
		if (paymentStandard != null) {
			unitPrice = paymentStandard.getManagementFee();
			consumUnitPrice = paymentStandard.getConsumption();
		}
		double rentFee = unitPrice*100*houseInfo.getUseArea();//以分为单位
		account.setFee(new BigDecimal(rentFee).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue());//转换为元
		double consumPrice = consumUnitPrice*100*houseInfo.getUseArea();//以分为单位
		double consumPriceYuan = new BigDecimal(consumPrice).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		//费用到期日期
		Date feeExpireDate = balanceService.getFeeExpireDate(housePerson, PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
		GzfAccountBalance balance = new GzfAccountBalance();
		balance.setExpireDate(feeExpireDate);
		
		model.addAttribute("gzfAccount", account);
		model.addAttribute("payList", payList);
		model.addAttribute("householdInfo", householdInfo);
		model.addAttribute("lastBalance", balance);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setPhoneNo(account.getPhoneNo());
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.MANAGEMENT);
		gzfPayment.setAmount(account.getFee());
		gzfPayment.setAmountYuan(account.getFee() + "");
//		gzfPayment.setExtendSpecPaymentId("");
//		gzfPayment.setExtendAmount("");
		if(consumPrice > 0){
			gzfPayment.setExtendSpecPaymentId(PaymentConst.SPECIAL_PAYMENT.CONSUMPTION + "");
			gzfPayment.setExtendAmount(consumPriceYuan + "");
		}
		Date effectDate = new Date();
		if (feeExpireDate != null) {
			effectDate = DateUtils.addOrMinusDays(feeExpireDate.getTime(),1);
		}
		effectDate = DateUtils.getBeginDateOfDay(effectDate);
		gzfPayment.setEffectDateStr(DateUtils.formatDate(effectDate));
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payManagementFee";
	}
	
	@RequestMapping(value="payManagementFeeForm")
	public String payManagementFeeForm(GzfPayment gzfPayment, HttpServletRequest request, Model model){
		gzfPayment.setPayMethod("1");
		String effectDateStr = gzfPayment.getEffectDateStr();
		if (StringUtils.isNoneBlank(effectDateStr)) {
			gzfPayment.setExpireDate(DateUtils.addOrMinusDays(DateUtils.parseDate(effectDateStr).getTime(), 1));
		}
		double consumUnitPrice = 0;
		if (StringUtils.isNoneBlank(gzfPayment.getExtendSpecPaymentId()) && StringUtils.isNoneBlank(gzfPayment.getExtendAmount())) {
			consumUnitPrice = Double.valueOf(gzfPayment.getExtendAmount());
		}
		model.addAttribute("consumPrice", consumUnitPrice);
		return "modules/pay/payManagementFeeForm";
	}
	
	@RequestMapping(value="deposite")
	public String depositeHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.DEPOSIT);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payDeposite";
	}
	
	@RequestMapping(value="getDepositeHomePageInfo")
	public String getDepositeHomePageInfo(@ModelAttribute GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return depositeHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return depositeHomePage(account, model);
		}
		account = accountList.get(0);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return depositeHomePage(account, model);
		}
		account.setHousePerson(housePerson);
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
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
			householdInfo.setGzfHouseInfo(houseInfo);
		}
		GzfPaymentStandard paymentStandard = householdInfo.getGzfPaymentStandard();
		if (paymentStandard == null || StringUtils.isBlank(paymentStandard.getId())) {
			paymentStandard = payStandardService.get(householdInfo.getGzfPaymentStandardId());
			householdInfo.setPaymentStandard(paymentStandard);
		}
		
		double unitPrice = 0;
		if (paymentStandard != null) {
			unitPrice = paymentStandard.getDeposit();
		}
		double rentFee = unitPrice*100;//以分为单位
		account.setFee(new BigDecimal(rentFee).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue());//转换为元
		model.addAttribute("gzfAccount", account);
		model.addAttribute("householdInfo", householdInfo);
		
		//缴费历史
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		payment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.DEPOSIT);
		Date date = new Date();
		payment.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		payment.setEndTime(date);
		Page<GzfPayment> payList = paymentService.findPage(new Page<GzfPayment>(request, response), payment);
		
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.DEPOSIT);
		gzfPayment.setAmount(account.getFee());
		gzfPayment.setAmountYuan(account.getFee() + "");
		gzfPayment.setPhoneNo(account.getPhoneNo());
		model.addAttribute("gzfPayment", gzfPayment);
		model.addAttribute("payList", payList);
		return "modules/pay/payDeposite";
	}
	
	@RequestMapping(value="payHouseRentBatch")
	public String payHouseRentBatch(GzfAccount gzfAccount, Model model, HttpServletRequest request, HttpServletResponse response){
		Page<GzfAccount> page = accountService.findPage(new Page<GzfAccount>(request, response), gzfAccount);
		if (page.getList() != null && page.getList().size() > 0) {
			GzfAccountBalance balanceTemp = new GzfAccountBalance();
			List<GzfAccount> accountList = page.getList();
			for(int i = 0; i < accountList.size(); i ++){
				GzfAccount account = accountList.get(i);
				if (account != null) {
					GzfHousePerson housePerson = getHousePerson(account);
					if (housePerson == null) {
						account.setUpdateDate(null);
						continue;
					}
					account.setHousePerson(getHousePerson(account));
					account.setUpdateDate(balanceService.getFeeExpireDate(account.getHousePerson(), PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT));
					balanceTemp.setAccountId(account.getId());
					balanceTemp.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
					int total = 0;
					List<GzfAccountBalance> balances = balanceService.findList(balanceTemp);
					if (balances != null && balances.size() > 0) {
						for(int j = 0; j < balances.size(); j++){
							total += balances.get(j).getBalance();
						}
					}
					account.setFee(Double.valueOf(total));
				}
			}
		}
		model.addAttribute("page", page);
		return "modules/pay/payHouseRentBatch";
	}
	
	public GzfHousePerson getHousePerson(GzfAccount account) {
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			return null;
		}
		//住房信息
		GzfHouseInfo houseInfo = housePerson.getGzfHouseInfo();
		if (houseInfo == null || StringUtils.isBlank(houseInfo.getId())) {
			houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
		}
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
		}
		housePerson.setGzfHouseInfo(houseInfo);
		return housePerson;
	}
	
	@RequestMapping(value="prePay")
	public String prePay(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
//		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);
		model.addAttribute("gzfPayment", gzfPayment);
		model.addAttribute("gzfBalanceRecord", new GzfBalanceRecord());
		return "modules/pay/prePay";
	}
	
	@RequestMapping(value="listAcctItem")
	public String listAcctItem(GzfAcctItem gzfAcctItem, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<GzfAcctItem> page = acctItemService.findPage(new Page<GzfAcctItem>(request, response), gzfAcctItem);
		model.addAttribute("page", page);
		return "modules/pay/gzfAcctItemList";
	}
	
	@RequestMapping(value="getBalanceInfo")
	public String getBalanceInfo(GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return prePay(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return prePay(account, model);
		}
		account = accountList.get(0);
		//查余额
		List<GzfAccountBalance> balanceList = balanceService.getGroupedBalanceByAccountId(account.getId());
		double freeBalance = 0;
		double houseRentBalance = 0;
		double managementBalance = 0;
		double waterBalance = 0;
		double electricityBalance = 0;
		double naturalgasBalance = 0;
		for(int i = 0; i < balanceList.size(); i++){
			if (PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL == balanceList.get(i).getSpePaymentId()) {
				freeBalance = balanceList.get(i).getBalance();
			}else if (PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT == balanceList.get(i).getSpePaymentId()) {
				houseRentBalance = balanceList.get(i).getBalance();
			}else if (PaymentConst.SPECIAL_PAYMENT.MANAGEMENT == balanceList.get(i).getSpePaymentId() 
					|| PaymentConst.SPECIAL_PAYMENT.CONSUMPTION == balanceList.get(i).getSpePaymentId()) {
				managementBalance = balanceList.get(i).getBalance();
			}else if (PaymentConst.SPECIAL_PAYMENT.WATER == balanceList.get(i).getSpePaymentId()) {
				waterBalance = balanceList.get(i).getBalance();
			}else if (PaymentConst.SPECIAL_PAYMENT.ELECTRICITY == balanceList.get(i).getSpePaymentId()) {
				electricityBalance = balanceList.get(i).getBalance();
			}else if (PaymentConst.SPECIAL_PAYMENT.NATURALGAS == balanceList.get(i).getSpePaymentId()) {
				naturalgasBalance = balanceList.get(i).getBalance();
			}
		}
		model.addAttribute("freeBalance", freeBalance);
		model.addAttribute("houseRentBalance", houseRentBalance);
		model.addAttribute("managementBalance", managementBalance);
		model.addAttribute("waterBalance", waterBalance);
		model.addAttribute("electricityBalance", electricityBalance);
		model.addAttribute("naturalgasBalance", naturalgasBalance);
		
		//查收支明细
		GzfBalanceRecord gzfBalanceRecord = new GzfBalanceRecord();
		gzfBalanceRecord.setAccountId(account.getId());
		gzfBalanceRecord.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL);
		Date date = new Date();
		gzfBalanceRecord.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		gzfBalanceRecord.setEndTime(date);
		List<GzfBalanceRecord> list = balanceRecordService.findList(gzfBalanceRecord);
		double totalIncome = 0;
		double totalExtend = 0;
		Page<GzfBalanceRecord> page = new Page<GzfBalanceRecord>();
		if (list != null && list.size() > 0) {
			GzfBalanceRecord record = null;
			for(int i = 0; i < list.size(); i++){
				record = list.get(i);
				if (record == null) {
					continue;
				}
				if (PaymentConst.OPER_TYPE.INCOME.equals(record.getOperType())) {
					totalIncome += record.getAmount();
				}else {
					totalExtend += record.getAmount();
				}
			}
			page = balanceRecordService.findPage(new Page<GzfBalanceRecord>(request, response), gzfBalanceRecord);
		}
		model.addAttribute("gzfBalanceRecord", gzfBalanceRecord);
		model.addAttribute("totalIncome", totalIncome);
		model.addAttribute("totalExtend", totalExtend);
		model.addAttribute("page", page);
		model.addAttribute("gzfAccount", account);
		return "modules/pay/prePay";
	}
	
	
	//转帐form
	@RequestMapping(value="transferForm")
	public String transferForm(GzfAcctTransfer acctTransfer, Model model){
		acctTransfer.setSrcAcctId(acctTransfer.getDestAcctId());
		acctTransfer.setSrcSpecPaymentId(PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL);
		String optCode = "";
		if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.WATER) {
			optCode = PaymentConst.OPT_CODE.TRANS_WATER_FEE;
		}else if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.ELECTRICITY) {
			optCode = PaymentConst.OPT_CODE.TRANS_ELECTRICITY_FEE;
		}else if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.NATURALGAS) {
			optCode = PaymentConst.OPT_CODE.TRANS_NATURALGAS_FEE;
		}else if (acctTransfer.getDestSpecPaymentId() == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
			optCode = PaymentConst.OPT_CODE.TRANS_HOUSE_RENT_FEE;
		}
		acctTransfer.setOptCode(optCode);;
		model.addAttribute("gzfAcctTransfer", acctTransfer);
		return "modules/pay/transferForm";
	}
	
	//充值form
	@RequestMapping(value="payFreeFee")
	public String payFreeFee(GzfPayment gzfPayment, Model model){
		gzfPayment.setOptCode(PaymentConst.OPT_CODE.PAY);
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL);
		model.addAttribute("gzfPayment", gzfPayment);
		int totalFreeFee = 0;
		model.addAttribute("totalFreeFee", totalFreeFee);
		return "modules/pay/payFreeFee";
	}
	
	@RequestMapping(value="refundWhenCheckOut")
	public String refundWhenCheckOut(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfPayment", new GzfPayment());
		return "modules/pay/refundWhenCheckOut";
	}
	
	@RequestMapping(value="queryGroupedBalance")
	public String queryGroupedBalance(GzfAccount gzfAccount, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(gzfAccount);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return refundWhenCheckOut(gzfAccount, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return refundWhenCheckOut(gzfAccount, model);
		}
		gzfAccount = accountList.get(0);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(gzfAccount.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return refundWhenCheckOut(gzfAccount, model);
		}
		//住房信息
		GzfHouseInfo houseInfo = housePerson.getGzfHouseInfo();
		if (houseInfo == null || StringUtils.isBlank(houseInfo.getId())) {
			houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
			housePerson.setGzfHouseInfo(houseInfo);
		}
		
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
			}else if (acctItemType == PaymentConst.ACCT_ITEM_TYPE.REPAIR_FEE) {
				repairFee += balance;
			}
		}
		model.addAttribute("houseRent", houseRent);
		model.addAttribute("management", management);
		model.addAttribute("waterFee", waterFee);
		model.addAttribute("electricityFee", electricityFee);
		model.addAttribute("naturalgasFee", naturalgasFee);
		model.addAttribute("deposite", deposite);
		model.addAttribute("freeFee", freeFee);
		model.addAttribute("repairFee", repairFee);
		double total = houseRent + management + waterFee + electricityFee + naturalgasFee + deposite + freeFee + repairFee;
		model.addAttribute("total", total);
		
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(gzfAccount.getId());
		gzfPayment.setCustId(gzfAccount.getCustId());
		gzfPayment.setPhoneNo(gzfAccount.getPhoneNo());
		model.addAttribute("gzfPayment", gzfPayment);
		model.addAttribute("housePerson", housePerson);
		return "modules/pay/refundWhenCheckOut";
	}
	
	@RequestMapping(value="repairFee")
	public String repairFeeHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/payRepairFee";
	}
	
	@RequestMapping(value="getRepairFeeHomePageInfo")
	public String getRepairFeeHomePageInfo(@ModelAttribute GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return repairFeeHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return repairFeeHomePage(account, model);
		}
		account = accountList.get(0);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return repairFeeHomePage(account, model);
		}
		account.setHousePerson(housePerson);
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
		if (houseInfo != null) {
			houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
			householdInfo.setGzfHouseInfo(houseInfo);
		}
		
		double receMoney = 0;
		GzfAcctItem acctItem = new GzfAcctItem();
		acctItem.setAccountId(account.getId());
		acctItem.setAcctItemTypeId(PaymentConst.ACCT_ITEM_TYPE.REPAIR_FEE);
		List<GzfAcctItem> acctItemList = acctItemService.getSumMoney(acctItem);
		if (acctItemList != null && acctItemList.size() == 1) {
			receMoney = acctItemList.get(0).getReceAmount() - acctItemList.get(0).getFactAmount();
		}
		account.setFee(new BigDecimal(receMoney).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).doubleValue());//转换为元
		model.addAttribute("gzfAccount", account);
		model.addAttribute("householdInfo", householdInfo);
		
		//缴费历史
		GzfPayment payment = new GzfPayment();
		payment.setAccountId(account.getId());
		payment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE);
		Date date = new Date();
		payment.setBeginTime(DateUtils.getBeginDateOfMonth(date));;
		payment.setEndTime(date);
		Page<GzfPayment> payList = paymentService.findPage(new Page<GzfPayment>(request, response), payment);
		
		GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		gzfPayment.setSpePaymentId(PaymentConst.SPECIAL_PAYMENT.REPAIR_FEE);
		gzfPayment.setAmount(account.getFee());
		gzfPayment.setAmountYuan(account.getFee() + "");
		gzfPayment.setPhoneNo(account.getPhoneNo());
		model.addAttribute("gzfPayment", gzfPayment);
		model.addAttribute("payList", payList);
		return "modules/pay/payRepairFee";
	}
	
	@RequestMapping(value="settlement")
	public String settlementOneOffHomePage(GzfAccount gzfAccount, Model model){
		model.addAttribute("gzfAccount", gzfAccount);
		GzfPayment gzfPayment = new GzfPayment();
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/settlementOneOff";
	}
	
	@RequestMapping(value="queryEveryTypeFee")
	public String getEveryTypeFee(GzfAccount account, HttpServletRequest request, HttpServletResponse response, Model model){
		//帐户基本信息（包括帐户信息、住房信息、房屋信息）
		List<GzfAccount> accountList = accountService.findList(account);
		if (accountList == null || accountList.size() == 0) {
			addMessage(model, "查询不到帐户！");
			return settlementOneOffHomePage(account, model);
		}
		if (accountList.size() > 1) {
			addMessage(model, "查到到多个帐户，请更改查询条件！");
			return settlementOneOffHomePage(account, model);
		}
		account = accountList.get(0);
		//住房人员关系
		GzfHousePerson housePerson = housePersonService.get(account.getCustId());
		if (housePerson == null) {
			addMessage(model, "查到不到住房信息！");
			return settlementOneOffHomePage(account, model);
		}
		//住房信息
		GzfHouseInfo houseInfo = housePerson.getGzfHouseInfo();
		if (houseInfo == null || StringUtils.isBlank(houseInfo.getId())) {
			houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
			housePerson.setGzfHouseInfo(houseInfo);
		}
		model.addAttribute("gzfAccount", account);
		model.addAttribute("housePerson", housePerson);
		
		double houseRentFee = 0, manageFee = 0, consumption = 0, waterFee = 0, electricityFee = 0, naturalgasFee = 0, deposite = 0, 
				freeFee = 0, repairFee = 0, total = 0;
		Map<String, Double> ret = dealBillService.getAcctItemMoney(account.getId());
		houseRentFee = Double.valueOf(ret.get("houseRent")) / 100;
        manageFee = Double.valueOf(ret.get("management")) / 100;
        consumption = Double.valueOf(ret.get("consumption")) / 100;
        waterFee = Double.valueOf(ret.get("waterFee")) / 100;//能耗费
        electricityFee = Double.valueOf(ret.get("electricityFee")) / 100;
        naturalgasFee = Double.valueOf(ret.get("naturalgasFee")) / 100;
        deposite = Double.valueOf(ret.get("deposite")) / 100;
        freeFee = Double.valueOf(ret.get("freeBalance")) / 100;
        repairFee = Double.valueOf(ret.get("repairFee")) / 100;
        total = houseRentFee + manageFee + waterFee + electricityFee + naturalgasFee + deposite + freeFee + repairFee;
        model.addAttribute("houseRentFee", houseRentFee < 0 ? "应退" + Math.abs(houseRentFee) : "应缴" + houseRentFee);
        model.addAttribute("manageFee", manageFee < 0 ? "应退" + Math.abs(manageFee) : "应缴" + manageFee);
        model.addAttribute("consumption", consumption < 0 ? "应退" + Math.abs(consumption) : "应缴" + consumption);
        model.addAttribute("waterFee", waterFee < 0 ? "应退" + Math.abs(waterFee) : "应缴" + waterFee);
        model.addAttribute("electricityFee", electricityFee < 0 ? "应退" + Math.abs(electricityFee) : "应缴" + electricityFee);
        model.addAttribute("naturalgasFee", naturalgasFee < 0 ? "应退" + Math.abs(naturalgasFee) : "应缴" + naturalgasFee);
        model.addAttribute("deposite", deposite < 0 ? "应退" + Math.abs(deposite) : "应缴" + deposite);
        model.addAttribute("repairFee", repairFee < 0 ? "应退" + Math.abs(repairFee) : "应缴" + repairFee);
        model.addAttribute("freeFee",  freeFee < 0 ? "应退" + Math.abs(freeFee) : "应缴" + freeFee);
        model.addAttribute("total", total < 0 ? "应退" + Math.abs(total) : "应缴" + total);
       
        GzfPayment gzfPayment = new GzfPayment();
		gzfPayment.setAccountId(account.getId());
		gzfPayment.setCustId(account.getCustId());
		gzfPayment.setPhoneNo(account.getPhoneNo());
		gzfPayment.setPayMethod(PaymentConst.PAY_METHOD.CASH);
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/settlementOneOff";
	}
}
