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
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransferOrder;
import com.paopao.hzgzf.modules.pay.service.GzfAcctTransferOrderService;

/**
 * 转帐工单Controller
 * @author songyahe
 * @version 2016-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/pay/gzfAcctTransferOrder")
public class GzfAcctTransferOrderController extends BaseController {

	@Autowired
	private GzfAcctTransferOrderService gzfAcctTransferOrderService;
	
	@ModelAttribute
	public GzfAcctTransferOrder get(@RequestParam(required=false) String id) {
		GzfAcctTransferOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfAcctTransferOrderService.get(id);
		}
		if (entity == null){
			entity = new GzfAcctTransferOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("pay:gzfAcctTransferOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfAcctTransferOrder gzfAcctTransferOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfAcctTransferOrder> page = gzfAcctTransferOrderService.findPage(new Page<GzfAcctTransferOrder>(request, response), gzfAcctTransferOrder); 
		model.addAttribute("page", page);
		return "modules/pay/gzfAcctTransferOrderList";
	}

	@RequiresPermissions("pay:gzfAcctTransferOrder:view")
	@RequestMapping(value = "form")
	public String form(GzfAcctTransferOrder gzfAcctTransferOrder, Model model) {
		model.addAttribute("gzfAcctTransferOrder", gzfAcctTransferOrder);
		return "modules/pay/gzfAcctTransferOrderForm";
	}

	@RequiresPermissions("pay:gzfAcctTransferOrder:edit")
	@RequestMapping(value = "save")
	public String save(GzfAcctTransferOrder gzfAcctTransferOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfAcctTransferOrder)){
			return form(gzfAcctTransferOrder, model);
		}
		gzfAcctTransferOrderService.save(gzfAcctTransferOrder);
		addMessage(redirectAttributes, "保存转帐成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfAcctTransferOrder/?repage";
	}
	
	@RequiresPermissions("pay:gzfAcctTransferOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfAcctTransferOrder gzfAcctTransferOrder, RedirectAttributes redirectAttributes) {
		gzfAcctTransferOrderService.delete(gzfAcctTransferOrder);
		addMessage(redirectAttributes, "删除转帐成功成功");
		return "redirect:"+Global.getAdminPath()+"/pay/gzfAcctTransferOrder/?repage";
	}

}