package com.paopao.hzgzf.modules.pay.web;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
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
import com.paopao.hzgzf.modules.sys.utils.DictUtils;

/**
 * App专用
 * @author songyh
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/pay/gzfAccountPublic")
public class GzfAccount4AppController extends BaseController{
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
	DealBillService dealbillService;
	
	
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
	
	@RequestMapping(value = "prePay")
    public String prePay(GzfAccount gzfAccount, Model model) {
        model.addAttribute("gzfAccount", gzfAccount);
        GzfPayment gzfPayment = new GzfPayment();
        model.addAttribute("gzfPayment", gzfPayment);
        model.addAttribute("gzfBalanceRecord", new GzfBalanceRecord());
        return "modules/pay/prePay4App";
    }
	
	
	@RequestMapping(value="listBalanceRecord")
	public @ResponseBody Map<String, GzfBalanceRecord> listBalanceRecord(GzfBalanceRecord gzfBalanceRecord, HttpServletRequest request, HttpServletResponse response){
		List<GzfBalanceRecord> list = balanceRecordService.findList(gzfBalanceRecord);
		int totalIncome = 0;
		int totalExtend = 0;
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
			List<GzfBalanceRecord> pageList = page.getList();
			if (pageList != null && pageList.size() > 0) {
				for(int j = 0; j < pageList.size(); j++){
					GzfBalanceRecord balanceRecord = pageList.get(j);
					balanceRecord.setAmount(balanceRecord.getAmount()/100);
					balanceRecord.setBalance(balanceRecord.getBalance()/100);
					balanceRecord.setOptCode(DictUtils.getDictLabel(balanceRecord.getOptCode(), "optCode", ""));
				}
			}
		}
		Map map = new HashMap();
		map.put("totalIncome", totalIncome/100);
		map.put("totalExtend", totalExtend/100);
		map.put("pageList", page);
		map.put("pageHtml", page.getHtml());
		return map;
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
			}else if (PaymentConst.SPECIAL_PAYMENT.MANAGEMENT == balanceList.get(i).getSpePaymentId()) {
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
		int totalIncome = 0;
		int totalExtend = 0;
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
		return "modules/pay/transferForm4App";
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
		return "modules/pay/payFreeFee4App";
	}
	
	
	//缴费提交
	@RequestMapping(value="pay")
	public String savePay(GzfPayment gzfPayment, Model model){
		if (!beanValidator(model, gzfPayment)) {
			/*if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
				model.addAttribute("gzfPayment", gzfPayment);
				return "modules/pay/houseRentPayForm";
			}else if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.MANAGEMENT) {
				model.addAttribute("gzfPayment", gzfPayment);
				return "modules/pay/payManagementFee";
			}else if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.ELECTRICITY) {
				model.addAttribute("gzfPayment", gzfPayment);
				return "modules/pay/payElectricityFee";
			}else if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.WATER) {
				model.addAttribute("gzfPayment", gzfPayment);
				return "modules/pay/payWaterFee";
			}else if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.NATURALGAS) {
				model.addAttribute("gzfPayment", gzfPayment);
				return "modules/pay/payNaturalgasFee";
			}else */if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL) {
				model.addAttribute("gzfPayment", gzfPayment);
				return "modules/pay/payFreeFee4App";
			}
		}
		try{
			boolean isAutoWriteOff = true;
			if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.DEPOSIT) {
				isAutoWriteOff = false;
			}
			gzfPayment.setAmount(gzfPayment.getAmount() * 100);
			dealbillService.pay(gzfPayment, isAutoWriteOff);
			addMessage(model, "缴费成功！");
		}catch(Exception e){
			addMessage(model, e.getCause() == null ? e.getMessage():e.getCause().getMessage());
		}
		return "modules/pay/payFreeFee4App";
	}
	
	//转帐
	@RequestMapping(value="transfer")
	public String transfer(GzfAcctTransfer gzfAcctTransfer, Model model){
		if (!beanValidator(model, gzfAcctTransfer)) {
			model.addAttribute("gzfAcctTransfer", gzfAcctTransfer);
			return "modules/pay/transferForm4App";
		}
		try{
			gzfAcctTransfer.setAmount(gzfAcctTransfer.getAmount() * 100);
			dealbillService.transfer(gzfAcctTransfer);
			addMessage(model, "操作成功！");
		}catch(Exception e){
			addMessage(model, e.getCause() == null ? e.getMessage():e.getCause().getMessage());
		}
		return "modules/pay/transferForm4App";
	}
}
