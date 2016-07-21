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
import com.paopao.hzgzf.modules.gzf.entity.GzfFaultCategory;
import com.paopao.hzgzf.modules.gzf.service.GzfFaultCategoryService;

/**
 * 故障类别Controller
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfFaultCategory")
public class GzfFaultCategoryController extends BaseController {

	@Autowired
	private GzfFaultCategoryService gzfFaultCategoryService;
	
	@ModelAttribute
	public GzfFaultCategory get(@RequestParam(required=false) String id) {
		GzfFaultCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfFaultCategoryService.get(id);
		}
		if (entity == null){
			entity = new GzfFaultCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfFaultCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfFaultCategory gzfFaultCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfFaultCategory> page = gzfFaultCategoryService.findPage(new Page<GzfFaultCategory>(request, response), gzfFaultCategory); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfFaultCategoryList";
	}

	@RequiresPermissions("gzf:gzfFaultCategory:view")
	@RequestMapping(value = "form")
	public String form(GzfFaultCategory gzfFaultCategory, Model model) {
		model.addAttribute("gzfFaultCategory", gzfFaultCategory);
		return "modules/gzf/gzfFaultCategoryForm";
	}

	@RequiresPermissions("gzf:gzfFaultCategory:edit")
	@RequestMapping(value = "save")
	public String save(GzfFaultCategory gzfFaultCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfFaultCategory)){
			return form(gzfFaultCategory, model);
		}
		gzfFaultCategoryService.save(gzfFaultCategory);
		addMessage(redirectAttributes, "保存故障类别成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfFaultCategory/?repage";
	}
	
	@RequiresPermissions("gzf:gzfFaultCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfFaultCategory gzfFaultCategory, RedirectAttributes redirectAttributes) {
		gzfFaultCategoryService.delete(gzfFaultCategory);
		addMessage(redirectAttributes, "删除故障类别成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfFaultCategory/?repage";
	}

}