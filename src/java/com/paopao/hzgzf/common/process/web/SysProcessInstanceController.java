/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.web;

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
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.process.dao.SysProcessInstanceDao;
import com.paopao.hzgzf.common.process.entity.SysProcessInstance;

/**
 * 流程实例管理Controller
 * @author zhoudk
 * @version 2016-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/process/sysProcessInstance")
public class SysProcessInstanceController extends BaseController {

	@Autowired
	private CrudService<SysProcessInstanceDao, SysProcessInstance> sysProcessInstanceService;
	
	@ModelAttribute
	public SysProcessInstance get(@RequestParam(required=false) String id) {
		SysProcessInstance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysProcessInstanceService.get(id);
		}
		if (entity == null){
			entity = new SysProcessInstance();
		}
		return entity;
	}
	
	@RequiresPermissions("process:sysProcessInstance:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysProcessInstance sysProcessInstance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysProcessInstance> page = sysProcessInstanceService.findPage(new Page<SysProcessInstance>(request, response), sysProcessInstance); 
		model.addAttribute("page", page);
		return "common/process/sysProcessInstanceList";
	}

	@RequiresPermissions("process:sysProcessInstance:view")
	@RequestMapping(value = "form")
	public String form(SysProcessInstance sysProcessInstance, Model model) {
		model.addAttribute("sysProcessInstance", sysProcessInstance);
		return "common/process/sysProcessInstanceForm";
	}

	@RequiresPermissions("process:sysProcessInstance:edit")
	@RequestMapping(value = "save")
	public String save(SysProcessInstance sysProcessInstance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysProcessInstance)){
			return form(sysProcessInstance, model);
		}
		sysProcessInstanceService.save(sysProcessInstance);
		addMessage(redirectAttributes, "保存流程实例成功");
		return "redirect:"+Global.getAdminPath()+"/process/sysProcessInstance/?repage";
	}
	
	@RequiresPermissions("process:sysProcessInstance:edit")
	@RequestMapping(value = "delete")
	public String delete(SysProcessInstance sysProcessInstance, RedirectAttributes redirectAttributes) {
		sysProcessInstanceService.delete(sysProcessInstance);
		addMessage(redirectAttributes, "删除流程实例成功");
		return "redirect:"+Global.getAdminPath()+"/process/sysProcessInstance/?repage";
	}

}