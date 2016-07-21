package com.paopao.hzgzf.modules.pay.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.paopao.hzgzf.common.beanvalidator.BeanValidators;
import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.utils.excel.ExportExcel;
import com.paopao.hzgzf.common.utils.excel.ImportExcel;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransfer;
import com.paopao.hzgzf.modules.pay.entity.GzfPayment;
import com.paopao.hzgzf.modules.pay.service.DealBillService;
import com.paopao.hzgzf.modules.pay.service.GzfPaymentService;
import com.paopao.hzgzf.modules.sys.utils.DictUtils;

@Controller
@RequestMapping(value="${adminPath}/pay/gzfPayment")
public class GzfPaymentController extends BaseController{

	@Autowired
	GzfPaymentService paymentService;
	
	@Autowired
	DealBillService dealbillService;
	
	@ModelAttribute
	public GzfPayment get(@RequestParam(required=false)String id){
		GzfPayment entity = null;
		if (StringUtils.isNoneBlank(id)) {
			entity = paymentService.get(id);
		}else{
			entity = new GzfPayment();
		}
		return entity;
	}
	
	//缴费提交
	@RequestMapping(value="save")
	public String savePay(GzfPayment gzfPayment, Model model){
		if (!beanValidator(model, gzfPayment)) {
			if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT) {
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
			}else if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL) {
				model.addAttribute("gzfPayment", gzfPayment);
				return "modules/pay/payFreeFee";
			}
		}
		try{
//			boolean isAutoWriteOff = true;
//			if (gzfPayment.getSpePaymentId() == PaymentConst.SPECIAL_PAYMENT.DEPOSIT) {
//				isAutoWriteOff = false;
//			}
//			dealbillService.pay(gzfPayment, isAutoWriteOff);
			gzfPayment.setAmount(gzfPayment.getAmount() * 100);//转换成以分为单位
			dealbillService.pay(gzfPayment);
			addMessage(model, "缴费成功！");
		}catch(Exception e){
			addMessage(model, e.getCause() == null ? e.getMessage():e.getCause().getMessage());
		}
		return "modules/pay/result";
	}
	
	//根据帐户和时间段查询缴费历史记录
	@RequestMapping(value="list")
	public String list(@ModelAttribute GzfPayment gzfPayment, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<GzfPayment> page = paymentService.findPage(new Page<GzfPayment>(request, response), gzfPayment);
		model.addAttribute("page", page);
		return "modules/pay/payList";
	}
	
	//根据帐户和时间段查询缴费历史记录,返回json
	@RequestMapping(value="listJson")
	public @ResponseBody Map<String, Page<GzfPayment>> listJson(GzfPayment gzfPayment, HttpServletRequest request, HttpServletResponse response){
		Page<GzfPayment> page = paymentService.findPage(new Page<GzfPayment>(request, response), gzfPayment);
		List<GzfPayment> list = page.getList();
		if (list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++){
				GzfPayment payment = list.get(i);
				payment.setAmountYuan(new BigDecimal(payment.getAmount()).divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP).toString());
				payment.setPayMethodName(DictUtils.getDictLabel(payment.getPayMethod(), "paymethod", ""));
			}
		}
		Map map = new HashMap();
		map.put("payList", page);
		map.put("pageHtml", page.getHtml());
		return map;
	}
	
	//查看缴费详情
	@RequestMapping(value="detail")
	public String detail(GzfPayment gzfPayment, Model model){
		model.addAttribute("gzfPayment", gzfPayment);
		return "modules/pay/paymentDetail";
	}
	
	//转帐
	@RequestMapping(value="transfer")
	public String transfer(GzfAcctTransfer gzfAcctTransfer, Model model){
		if (!beanValidator(model, gzfAcctTransfer)) {
			model.addAttribute("gzfAcctTransfer", gzfAcctTransfer);
			return "modules/pay/transferForm";
		}
		try{
			gzfAcctTransfer.setAmount(gzfAcctTransfer.getAmount() * 100);//转换成以分为单位
			dealbillService.transfer(gzfAcctTransfer);
			addMessage(model, "操作成功！");
		}catch(Exception e){
			addMessage(model, e.getCause() == null ? e.getMessage():e.getCause().getMessage());
		}
		return "modules/pay/result";
	}
	
	//下载批量缴费模板
	@RequestMapping(value="import/template")
	public String importFileTemplat(HttpServletResponse response, RedirectAttributes redirectAttributes){
		try {
            String fileName = "批量缴费导入模板.xlsx";
    		List<GzfPayment> list = Lists.newArrayList(); 
    		GzfPayment temp = new GzfPayment();
    		temp.setPhoneNo("13333333333");
    		temp.setAmount((double) 100);
    		list.add(temp);
    		new ExportExcel("", GzfPayment.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pay/gzfAccount/payHouseRentBatch?repage";
	}
	
	//导入缴费数据
	@RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pay/gzfAccount/payHouseRentBatch?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 0, 0);
			List<GzfPayment> list = ei.getDataList(GzfPayment.class);
			for (GzfPayment payment : list){
				try{
					payment.setAmount(payment.getAmount() * 100);
					dealbillService.payHouseRentBatch(payment, true);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>住户 "+payment.getPhoneNo()+"缴费失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>住户 "+payment.getPhoneNo()+"缴费失败："+ex.getMessage());
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功为 "+successNum+" 条住户缴费"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "批量缴费失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pay/gzfAccount/payHouseRentBatch?repage";
    }
	
	//退费
	@RequestMapping(value="refund")
	public String refundWhenCheckOut(GzfPayment gzfPayment, Model model){
//		if (!beanValidator(model, gzfPayment)) {
//			model.addAttribute("gzfPayment", gzfPayment);
//			return "modules/pay/refundWhenCheckOut";
//		}
		try{
			dealbillService.refundWhenCheckOut(gzfPayment);
			addMessage(model, "退款成功！");
		}catch(Exception e){
			addMessage(model, e.getCause() == null ? e.getMessage():e.getCause().getMessage());
		}
		return "modules/pay/result";
	}
	
	//一次性结清所有费用
	@RequestMapping(value="settlementOneOff")
	public String settlementOneOff(GzfPayment gzfPayment, Model model){
		try{
			dealbillService.settlementOneOff(gzfPayment);
			addMessage(model, "操作成功！");
		}catch(Exception e){
			addMessage(model, e.getCause() == null ? e.getMessage():e.getCause().getMessage());
		}
		return "modules/pay/result";
	}
}
