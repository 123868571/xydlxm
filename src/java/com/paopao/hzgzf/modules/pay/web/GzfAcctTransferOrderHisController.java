/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.pay.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransferOrderHis;
import com.paopao.hzgzf.modules.pay.service.GzfAcctTransferOrderHisService;

/**
 * 转帐工单历史Controller
 * @author songyahe
 * @version 2016-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/gzfAcctTransferOrderHis")
public class GzfAcctTransferOrderHisController extends BaseController {

	@Autowired
	private GzfAcctTransferOrderHisService gzfAcctTransferOrderHisService;
	
	@ModelAttribute
	public GzfAcctTransferOrderHis get(@RequestParam(required=false) String id) {
		GzfAcctTransferOrderHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfAcctTransferOrderHisService.get(id);
		}
		if (entity == null){
			entity = new GzfAcctTransferOrderHis();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:gzfAcctTransferOrderHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfAcctTransferOrderHis gzfAcctTransferOrderHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfAcctTransferOrderHis> page = gzfAcctTransferOrderHisService.findPage(new Page<GzfAcctTransferOrderHis>(request, response), gzfAcctTransferOrderHis); 
		model.addAttribute("page", page);
		return "modules/pay/gzfAcctTransferOrderHisList";
	}

	@RequiresPermissions("pay:gzfAcctTransferOrderHis:view")
	@RequestMapping(value = "form")
	public String form(GzfAcctTransferOrderHis gzfAcctTransferOrderHis, Model model) {
		model.addAttribute("gzfAcctTransferOrderHis", gzfAcctTransferOrderHis);
		return "modules/pay/gzfAcctTransferOrderHisForm";
	}

	@RequiresPermissions("pay:gzfAcctTransferOrderHis:edit")
	@RequestMapping(value = "save")
	public String save(GzfAcctTransferOrderHis gzfAcctTransferOrderHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfAcctTransferOrderHis)){
			return form(gzfAcctTransferOrderHis, model);
		}
		gzfAcctTransferOrderHisService.save(gzfAcctTransferOrderHis);
		addMessage(redirectAttributes, "保存保存转帐历史成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfAcctTransferOrderHis/?repage";
	}
	
	@RequiresPermissions("pay:gzfAcctTransferOrderHis:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfAcctTransferOrderHis gzfAcctTransferOrderHis, RedirectAttributes redirectAttributes) {
		gzfAcctTransferOrderHisService.delete(gzfAcctTransferOrderHis);
		addMessage(redirectAttributes, "删除保存转帐历史成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfAcctTransferOrderHis/?repage";
	}

}