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
import com.paopao.hzgzf.modules.pay.entity.GzfPaymentOrderHis;
import com.paopao.hzgzf.modules.pay.service.GzfPaymentOrderHisService;

/**
 * 外围缴费工单历史表Controller
 * @author songyahe
 * @version 2016-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/gzfPaymentOrderHis")
public class GzfPaymentOrderHisController extends BaseController {

	@Autowired
	private GzfPaymentOrderHisService gzfPaymentOrderHisService;
	
	@ModelAttribute
	public GzfPaymentOrderHis get(@RequestParam(required=false) String id) {
		GzfPaymentOrderHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfPaymentOrderHisService.get(id);
		}
		if (entity == null){
			entity = new GzfPaymentOrderHis();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:gzfPaymentOrderHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfPaymentOrderHis gzfPaymentOrderHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfPaymentOrderHis> page = gzfPaymentOrderHisService.findPage(new Page<GzfPaymentOrderHis>(request, response), gzfPaymentOrderHis); 
		model.addAttribute("page", page);
		return "modules/pay/gzfPaymentOrderHisList";
	}

	@RequiresPermissions("pay:gzfPaymentOrderHis:view")
	@RequestMapping(value = "form")
	public String form(GzfPaymentOrderHis gzfPaymentOrderHis, Model model) {
		model.addAttribute("gzfPaymentOrderHis", gzfPaymentOrderHis);
		return "modules/pay/gzfPaymentOrderHisForm";
	}

	@RequiresPermissions("pay:gzfPaymentOrderHis:edit")
	@RequestMapping(value = "save")
	public String save(GzfPaymentOrderHis gzfPaymentOrderHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfPaymentOrderHis)){
			return form(gzfPaymentOrderHis, model);
		}
		gzfPaymentOrderHisService.save(gzfPaymentOrderHis);
		addMessage(redirectAttributes, "保存保存缴费历史成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfPaymentOrderHis/?repage";
	}
	
	@RequiresPermissions("pay:gzfPaymentOrderHis:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfPaymentOrderHis gzfPaymentOrderHis, RedirectAttributes redirectAttributes) {
		gzfPaymentOrderHisService.delete(gzfPaymentOrderHis);
		addMessage(redirectAttributes, "删除保存缴费历史成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfPaymentOrderHis/?repage";
	}

}