package com.paopao.hzgzf.modules.pay.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceRecord;
import com.paopao.hzgzf.modules.pay.service.GzfBalanceRecordService;
import com.paopao.hzgzf.modules.sys.utils.DictUtils;

@Controller
@RequestMapping(value="${adminPath}/pay/balance")
public class GzfAccountBalanceController extends BaseController{
	@Autowired
	GzfBalanceRecordService balanceRecordService;
	
	@RequestMapping(value="listJson")
	public @ResponseBody Map<String, GzfBalanceRecord> listBalanceRecord(GzfBalanceRecord gzfBalanceRecord, HttpServletRequest request, HttpServletResponse response){
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
			List<GzfBalanceRecord> pageList = page.getList();
			if (pageList != null && pageList.size() > 0) {
				for(int j = 0; j < pageList.size(); j++){
					GzfBalanceRecord balanceRecord = pageList.get(j);
					balanceRecord.setAmount(balanceRecord.getAmount()/100);
					balanceRecord.setBalance(balanceRecord.getBalance()/100);
					balanceRecord.setTotalBalance(balanceRecord.getTotalBalance()/100);
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
}
