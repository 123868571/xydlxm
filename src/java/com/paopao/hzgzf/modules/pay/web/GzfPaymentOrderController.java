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
import com.paopao.hzgzf.modules.pay.entity.GzfPaymentOrder;
import com.paopao.hzgzf.modules.pay.service.GzfPaymentOrderService;

/**
 * 外围缴费工单Controller
 * @author songyahe
 * @version 2016-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/gzfPaymentOrder")
public class GzfPaymentOrderController extends BaseController {

	@Autowired
	private GzfPaymentOrderService gzfPaymentOrderService;
	
	@ModelAttribute
	public GzfPaymentOrder get(@RequestParam(required=false) String id) {
		GzfPaymentOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfPaymentOrderService.get(id);
		}
		if (entity == null){
			entity = new GzfPaymentOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:gzfPaymentOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfPaymentOrder gzfPaymentOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfPaymentOrder> page = gzfPaymentOrderService.findPage(new Page<GzfPaymentOrder>(request, response), gzfPaymentOrder); 
		model.addAttribute("page", page);
		return "modules/pay/gzfPaymentOrderList";
	}

	@RequiresPermissions("pay:gzfPaymentOrder:view")
	@RequestMapping(value = "form")
	public String form(GzfPaymentOrder gzfPaymentOrder, Model model) {
		model.addAttribute("gzfPaymentOrder", gzfPaymentOrder);
		return "modules/pay/gzfPaymentOrderForm";
	}

	@RequiresPermissions("pay:gzfPaymentOrder:edit")
	@RequestMapping(value = "save")
	public String save(GzfPaymentOrder gzfPaymentOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfPaymentOrder)){
			return form(gzfPaymentOrder, model);
		}
		gzfPaymentOrderService.save(gzfPaymentOrder);
		addMessage(redirectAttributes, "保存缴费成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfPaymentOrder/?repage";
	}
	
	@RequiresPermissions("pay:gzfPaymentOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfPaymentOrder gzfPaymentOrder, RedirectAttributes redirectAttributes) {
		gzfPaymentOrderService.delete(gzfPaymentOrder);
		addMessage(redirectAttributes, "删除缴费成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfPaymentOrder/?repage";
	}

}