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
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairProject;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairProjectService;

/**
 * 报修项目Controller
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfRepairProject")
public class GzfRepairProjectController extends BaseController {

	@Autowired
	private GzfRepairProjectService gzfRepairProjectService;
	
	@ModelAttribute
	public GzfRepairProject get(@RequestParam(required=false) String id) {
		GzfRepairProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfRepairProjectService.get(id);
		}
		if (entity == null){
			entity = new GzfRepairProject();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfRepairProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfRepairProject gzfRepairProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfRepairProject> page = gzfRepairProjectService.findPage(new Page<GzfRepairProject>(request, response), gzfRepairProject); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfRepairProjectList";
	}

	@RequiresPermissions("gzf:gzfRepairProject:view")
	@RequestMapping(value = "form")
	public String form(GzfRepairProject gzfRepairProject, Model model) {
		model.addAttribute("gzfRepairProject", gzfRepairProject);
		return "modules/gzf/gzfRepairProjectForm";
	}

	@RequiresPermissions("gzf:gzfRepairProject:edit")
	@RequestMapping(value = "save")
	public String save(GzfRepairProject gzfRepairProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfRepairProject)){
			return form(gzfRepairProject, model);
		}
		gzfRepairProjectService.save(gzfRepairProject);
		addMessage(redirectAttributes, "保存报修项目成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRepairProject/?repage";
	}
	
	@RequiresPermissions("gzf:gzfRepairProject:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfRepairProject gzfRepairProject, RedirectAttributes redirectAttributes) {
		gzfRepairProjectService.delete(gzfRepairProject);
		addMessage(redirectAttributes, "删除报修项目成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRepairProject/?repage";
	}

}