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
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.process.entity.SysProcessInstanceDetail;
import com.paopao.hzgzf.common.process.service.SysProcessInstanceDetailService;

/**
 * 流程实例明细Controller
 * @author zhoudk
 * @version 2016-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/process/sysProcessInstanceDetail")
public class SysProcessInstanceDetailController extends BaseController {

	@Autowired
	private SysProcessInstanceDetailService sysProcessInstanceDetailService;
	
	@ModelAttribute
	public SysProcessInstanceDetail get(@RequestParam(required=false) String id) {
		SysProcessInstanceDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysProcessInstanceDetailService.get(id);
		}
		if (entity == null){
			entity = new SysProcessInstanceDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("process:sysProcessInstanceDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysProcessInstanceDetail sysProcessInstanceDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysProcessInstanceDetail> page = sysProcessInstanceDetailService.findPage(new Page<SysProcessInstanceDetail>(request, response), sysProcessInstanceDetail); 
		model.addAttribute("page", page);
		return "common/process/sysProcessInstanceDetailList";
	}

	@RequiresPermissions("process:sysProcessInstanceDetail:view")
	@RequestMapping(value = "form")
	public String form(SysProcessInstanceDetail sysProcessInstanceDetail, Model model) {
		model.addAttribute("sysProcessInstanceDetail", sysProcessInstanceDetail);
		return "common/process/sysProcessInstanceDetailForm";
	}

	@RequiresPermissions("process:sysProcessInstanceDetail:edit")
	@RequestMapping(value = "save")
	public String save(SysProcessInstanceDetail sysProcessInstanceDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysProcessInstanceDetail)){
			return form(sysProcessInstanceDetail, model);
		}
		sysProcessInstanceDetailService.save(sysProcessInstanceDetail);
		addMessage(redirectAttributes, "保存流程实例明细成功");
		return "redirect:"+Global.getAdminPath()+"/process/sysProcessInstanceDetail/?repage";
	}
	
	@RequiresPermissions("process:sysProcessInstanceDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(SysProcessInstanceDetail sysProcessInstanceDetail, RedirectAttributes redirectAttributes) {
		sysProcessInstanceDetailService.delete(sysProcessInstanceDetail);
		addMessage(redirectAttributes, "删除流程实例明细成功");
		return "redirect:"+Global.getAdminPath()+"/process/sysProcessInstanceDetail/?repage";
	}

}