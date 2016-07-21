/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.web;

import java.util.List;

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
import com.paopao.hzgzf.common.process.entity.SysProcessStepConf;
import com.paopao.hzgzf.common.process.entity.SysProcessType;
import com.paopao.hzgzf.common.process.service.SysProcessStepConfService;
import com.paopao.hzgzf.common.process.service.SysProcessTypeService;
import com.paopao.hzgzf.modules.sys.entity.Role;
import com.paopao.hzgzf.modules.sys.service.SystemService;

/**
 * 流程步骤分配Controller
 * @author zhoudk
 * @version 2016-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/process/sysProcessStepConf")
public class SysProcessStepConfController extends BaseController {

	@Autowired
	private SysProcessStepConfService sysProcessStepConfService;
	
	@Autowired
	private SysProcessTypeService sysProcessTypeService;
	
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public SysProcessStepConf get(@RequestParam(required=false) String id) {
		SysProcessStepConf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysProcessStepConfService.get(id);
		}
		if (entity == null){
			entity = new SysProcessStepConf();
		}
		return entity;
	}
	
	@RequiresPermissions("process:sysProcessStepConf:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysProcessStepConf sysProcessStepConf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysProcessStepConf> page = sysProcessStepConfService.findPage(new Page<SysProcessStepConf>(request, response), sysProcessStepConf); 
		model.addAttribute("page", page);
		// 查询角色
		List<Role> roles = systemService.findAllRole();
		model.addAttribute("roles", roles);
		// 查询流程类型
		SysProcessType sysProcessType = new SysProcessType();
		List<SysProcessType> types = sysProcessTypeService.findList(sysProcessType);
		model.addAttribute("processTypes", types);
		return "common/process/sysProcessStepConfList";
	}

	@RequiresPermissions("process:sysProcessStepConf:view")
	@RequestMapping(value = "form")
	public String form(SysProcessStepConf sysProcessStepConf, Model model) {
		model.addAttribute("sysProcessStepConf", sysProcessStepConf);
		// 查询角色
		List<Role> roles = systemService.findAllRole();
		model.addAttribute("roles", roles);
		return "common/process/sysProcessStepConfForm";
	}

	@RequiresPermissions("process:sysProcessStepConf:edit")
	@RequestMapping(value = "save")
	public String save(SysProcessStepConf sysProcessStepConf, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysProcessStepConf)){
			return form(sysProcessStepConf, model);
		}
		sysProcessStepConfService.save(sysProcessStepConf);
		addMessage(redirectAttributes, "保存流程步骤成功");
		return "redirect:"+Global.getAdminPath()+"/process/sysProcessStepConf/?repage";
	}
	
	@RequiresPermissions("process:sysProcessStepConf:edit")
	@RequestMapping(value = "delete")
	public String delete(SysProcessStepConf sysProcessStepConf, RedirectAttributes redirectAttributes) {
		sysProcessStepConfService.delete(sysProcessStepConf);
		addMessage(redirectAttributes, "删除流程步骤成功");
		return "redirect:"+Global.getAdminPath()+"/process/sysProcessStepConf/?repage";
	}

}