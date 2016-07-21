/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionCategory;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionCategoryService;

/**
 * 巡查类别Controller
 * @author Hongjun
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfInspectionCategory")
public class GzfInspectionCategoryController extends BaseController {

	@Autowired
	private GzfInspectionCategoryService gzfInspectionCategoryService;
	
	@ModelAttribute
	public GzfInspectionCategory get(@RequestParam(required=false) String id) {
		GzfInspectionCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfInspectionCategoryService.get(id);
		}
		if (entity == null){
			entity = new GzfInspectionCategory();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(GzfInspectionCategory gzfInspectionCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfInspectionCategory> page = gzfInspectionCategoryService.findPage(new Page<GzfInspectionCategory>(request, response), gzfInspectionCategory); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfInspectionCategoryList";
	}

	@RequestMapping(value = "form")
	public String form(GzfInspectionCategory gzfInspectionCategory, Model model) {
		model.addAttribute("gzfInspectionCategory", gzfInspectionCategory);
		return "modules/gzf/gzfInspectionCategoryForm";
	}

	@RequestMapping(value = "save")
	public String save(GzfInspectionCategory gzfInspectionCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfInspectionCategory)){
			return form(gzfInspectionCategory, model);
		}
		gzfInspectionCategoryService.save(gzfInspectionCategory);
		addMessage(redirectAttributes, "保存巡查类别成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfInspectionCategory/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(GzfInspectionCategory gzfInspectionCategory, RedirectAttributes redirectAttributes) {
		gzfInspectionCategoryService.delete(gzfInspectionCategory);
		addMessage(redirectAttributes, "删除巡查类别成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfInspectionCategory/?repage";
	}

}