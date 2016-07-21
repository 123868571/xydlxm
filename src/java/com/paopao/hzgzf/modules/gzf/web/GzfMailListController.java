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
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfMailList;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfMailListService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 水表Controller
 * @author Hongjun
 * @version 2016-01-17
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfMailList")
public class GzfMailListController extends BaseController {

	@Autowired
	private GzfMailListService gzfMailListService;
	
	@Autowired
	private GzfVillageService gzfVillageService;
	
	@ModelAttribute
	public GzfMailList get(@RequestParam(required=false) String id) {
		GzfMailList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfMailListService.get(id);
		}
		if (entity == null){
			entity = new GzfMailList();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfMailList:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfMailList gzfMailList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfMailList> page = gzfMailListService.findPage(new Page<GzfMailList>(request, response), gzfMailList); 
		model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
		model.addAttribute("page", page);
		return "modules/gzf/gzfMailListList";
	}

	@RequiresPermissions("gzf:gzfMailList:view")
	@RequestMapping(value = "form")
	public String form(GzfMailList gzfMailList, Model model) {
		model.addAttribute("gzfMailList", gzfMailList);
		model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
		return "modules/gzf/gzfMailListForm";
	}

	@RequiresPermissions("gzf:gzfMailList:edit")
	@RequestMapping(value = "save")
	public String save(GzfMailList gzfMailList, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfMailList)){
			return form(gzfMailList, model);
		}
		gzfMailListService.save(gzfMailList);
		addMessage(redirectAttributes, "保存水表成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfMailList/?repage";
	}
	
	@RequiresPermissions("gzf:gzfMailList:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfMailList gzfMailList, RedirectAttributes redirectAttributes) {
		gzfMailListService.delete(gzfMailList);
		addMessage(redirectAttributes, "删除水表成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfMailList/?repage";
	}

}