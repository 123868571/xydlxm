package com.paopao.hzgzf.modules.pay.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.pay.entity.AccountBank;
import com.paopao.hzgzf.modules.pay.entity.GzfBankCard;
import com.paopao.hzgzf.modules.pay.service.AccountBankService;
import com.paopao.hzgzf.modules.pay.service.GzfBankCardService;

@Controller
@RequestMapping(value="${adminPath}/pay/accountBank")
public class AccountBankController extends BaseController{
	@Autowired
	AccountBankService accountBankService;
	@Autowired
	GzfHousePersonService housePersonService;
	@Autowired
	GzfHouseInfoService houseInfoService;
	@Autowired
	GzfBankCardService bankCardService;
	
	@ModelAttribute
	public AccountBank get(@RequestParam(required=false)String id, @RequestParam(required=false)String bankId,
			@RequestParam(required=false)String cardNo, @RequestParam(required=false)String bankName, @RequestParam(required=false)String acctItemType){
		AccountBank accountBank = new AccountBank();
		if (StringUtils.isNoneBlank(id)) {
			accountBank.setId(id);
		}
		if (StringUtils.isNoneBlank(bankId)) {
			accountBank.setBankId(bankId);;
		}
		if (StringUtils.isNoneBlank(cardNo)) {
			accountBank.setCardNo(cardNo);
		}
		if (StringUtils.isNoneBlank(bankName)) {
			accountBank.setBankName(bankName);
		}
		if (StringUtils.isNoneBlank(acctItemType)) {
			accountBank.setAcctItemType(acctItemType);
		}
		return accountBank;
	}
	
	@RequestMapping(value={"","list"})
	public String list(AccountBank accountBank, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<AccountBank> page = accountBankService.findPage(new Page<AccountBank>(request, response), accountBank);
		List<AccountBank> list = page.getList();
		if (list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++){
				AccountBank bank = list.get(i);
				if (StringUtils.isNoneBlank(bank.getCardNo())) {
					List<GzfBankCard> bankCards = bankCardService.findListByAccountBank(bank);
					StringBuilder temp = new StringBuilder();
					if (bankCards != null && bankCards.size() > 0) {
						for(int j = 0; j < bankCards.size(); j++){
							temp.append(bankCards.get(j).getAcctItemTypeId()).append(",");
						}
					}
					if (StringUtils.isNoneBlank(temp.toString())) {
						String acctItemTypes = temp.toString();
						bank.setAcctItemType(acctItemTypes.substring(0, acctItemTypes.length() - 1));
					}
				}
				String housePersonId = bank.getCustId();
				if (StringUtils.isBlank(housePersonId)) {
					continue;
				}
				GzfHousePerson housePerson = housePersonService.get(housePersonId);
				if (housePerson == null) {
					continue;
				}
				String houseInfoId = housePerson.getGzfHouseInfoId();
				GzfHouseInfo houseInfo = houseInfoService.get(houseInfoId);
				housePerson.setGzfHouseInfo(houseInfo);
				bank.setHousePerson(housePerson);
			}
		}
		model.addAttribute("page", page);
		return "modules/pay/gzfBankCardList";
	}
	
	@RequestMapping(value="form")
	public String form(AccountBank accountBank, String oper, Model model){
		model.addAttribute("accountBank", accountBank);
		model.addAttribute("oper", oper);
		if ("add".equals(oper)) {
			return "modules/pay/gzfBankCardBindForm";
		}else {
			if (StringUtils.isNoneBlank(accountBank.getAcctItemType())) {
				String[] items = accountBank.getAcctItemType().split(",");
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < items.length; i++){
					if (StringUtils.isNoneBlank(items[i])) {
						list.add(items[i]);
					}
				}
				accountBank.setAcctItemTypeList(list);
			}
			return "modules/pay/gzfBankCardBindModifyForm";
		}
	}
	
	@RequestMapping(value="modify")
	public String modifyBankCard(AccountBank accountBank, Model model){
		boolean result = bankCardService.modify(accountBank);
		if (!result) {
			addMessage(model, "没有要保存的数据！");
			return "modules/pay/gzfBankCardBindForm";
		}
		return "redirect:" + Global.getAdminPath() + "/pay/accountBank/?repage";
	}
	
	@RequestMapping(value="add")
	public String addBankCard(AccountBank accountBank, Model model){
		List<GzfBankCard> list = new ArrayList<GzfBankCard>();
		List<String> acctItemTypes = accountBank.getAcctItemTypeList();
		if (acctItemTypes == null || acctItemTypes.size() == 0) {
			addMessage(model, "没有要保存的数据！");
			return "modules/pay/gzfBankCardBindForm";
		}
		for(int i = 0; i < acctItemTypes.size(); i++){
			if (StringUtils.isBlank(acctItemTypes.get(i))) {
				continue;
			}
			GzfBankCard bankCard = new GzfBankCard();
			bankCard.setAccountId(accountBank.getId());
			bankCard.setBankName(accountBank.getBankName());
			bankCard.setBankId(accountBank.getBankId());
			bankCard.setAcctItemTypeId(Integer.parseInt(acctItemTypes.get(i)));
			bankCard.setCardNo(accountBank.getCardNo());
			list.add(bankCard);
		}
		bankCardService.saveList(list);
		return "redirect:" + Global.getAdminPath() + "/pay/accountBank/?repage";
	}
}
