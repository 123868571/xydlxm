/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

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
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;

/**
 * 缴费标准Controller
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfPaymentStandard")
public class GzfPaymentStandardController extends BaseController {

	@Autowired
	private GzfPaymentStandardService gzfPaymentStandardService;
	
	@ModelAttribute
	public GzfPaymentStandard get(@RequestParam(required=false) String id) {
		GzfPaymentStandard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfPaymentStandardService.get(id);
		}
		if (entity == null){
			entity = new GzfPaymentStandard();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfPaymentStandard:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfPaymentStandard gzfPaymentStandard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfPaymentStandard> page = gzfPaymentStandardService.findPage(new Page<GzfPaymentStandard>(request, response), gzfPaymentStandard); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfPaymentStandardList";
	}

	@RequiresPermissions("gzf:gzfPaymentStandard:view")
	@RequestMapping(value = "form")
	public String form(GzfPaymentStandard gzfPaymentStandard, Model model) {
		model.addAttribute("gzfPaymentStandard", gzfPaymentStandard);
		return "modules/gzf/gzfPaymentStandardForm";
	}

	@RequiresPermissions("gzf:gzfPaymentStandard:edit")
	@RequestMapping(value = "save")
	public String save(GzfPaymentStandard gzfPaymentStandard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfPaymentStandard)){
			return form(gzfPaymentStandard, model);
		}
		gzfPaymentStandardService.save(gzfPaymentStandard);
		addMessage(redirectAttributes, "保存缴费标准成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfPaymentStandard/?repage";
	}
	
	@RequiresPermissions("gzf:gzfPaymentStandard:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfPaymentStandard gzfPaymentStandard, RedirectAttributes redirectAttributes) {
		gzfPaymentStandardService.delete(gzfPaymentStandard);
		addMessage(redirectAttributes, "删除缴费标准成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfPaymentStandard/?repage";
	}

}