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
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairManagerProject;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairManagerProjectService;

/**
 * 维修关系管理Controller
 * @author Hongjun
 * @version 2016-02-21
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfRepairManagerProject")
public class GzfRepairManagerProjectController extends BaseController {

	@Autowired
	private GzfRepairManagerProjectService gzfRepairManagerProjectService;
	
	@ModelAttribute
	public GzfRepairManagerProject get(@RequestParam(required=false) String id) {
		GzfRepairManagerProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfRepairManagerProjectService.get(id);
		}
		if (entity == null){
			entity = new GzfRepairManagerProject();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfRepairManagerProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfRepairManagerProject gzfRepairManagerProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfRepairManagerProject> page = gzfRepairManagerProjectService.findPage(new Page<GzfRepairManagerProject>(request, response), gzfRepairManagerProject); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfRepairManagerProjectList";
	}

	@RequiresPermissions("gzf:gzfRepairManagerProject:view")
	@RequestMapping(value = "form")
	public String form(GzfRepairManagerProject gzfRepairManagerProject, Model model) {
		model.addAttribute("gzfRepairManagerProject", gzfRepairManagerProject);
		return "modules/gzf/gzfRepairManagerProjectForm";
	}

	@RequiresPermissions("gzf:gzfRepairManagerProject:edit")
	@RequestMapping(value = "save")
	public String save(GzfRepairManagerProject gzfRepairManagerProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfRepairManagerProject)){
			return form(gzfRepairManagerProject, model);
		}
		gzfRepairManagerProjectService.save(gzfRepairManagerProject);
		addMessage(redirectAttributes, "保存维修关系管理成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRepairManagerProject/?repage";
	}
	
	@RequiresPermissions("gzf:gzfRepairManagerProject:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfRepairManagerProject gzfRepairManagerProject, RedirectAttributes redirectAttributes) {
		gzfRepairManagerProjectService.delete(gzfRepairManagerProject);
		addMessage(redirectAttributes, "删除维修关系管理成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRepairManagerProject/?repage";
	}

}