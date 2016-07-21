/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.web;

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
import com.paopao.hzgzf.modules.dlj.entity.ComJdManger;
import com.paopao.hzgzf.modules.dlj.service.ComJdMangerService;

/**
 * 局点管理表Controller
 * @author pjs
 * @version 2016-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comJdManger")
public class ComJdMangerController extends BaseController {

	@Autowired
	private ComJdMangerService comJdMangerService;
	
	@ModelAttribute
	public ComJdManger get(@RequestParam(required=false) String id) {
		ComJdManger entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comJdMangerService.get(id);
		}
		if (entity == null){
			entity = new ComJdManger();
		}
		return entity;
	}
	
	@RequiresPermissions("dlj:comJdManger:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComJdManger comJdManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComJdManger> page = comJdMangerService.findPage(new Page<ComJdManger>(request, response), comJdManger); 
		model.addAttribute("page", page);
		return "modules/dlj/comJdMangerList";
	}

	@RequiresPermissions("dlj:comJdManger:view")
	@RequestMapping(value = "form")
	public String form(ComJdManger comJdManger, Model model) {
		model.addAttribute("comJdManger", comJdManger);
		return "modules/dlj/comJdMangerForm";
	}
	
	@RequestMapping(value = {"show", ""})
	public String show(ComJdManger comJdManger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComJdManger> page = comJdMangerService.findPage(new Page<ComJdManger>(request, response), comJdManger); 
		model.addAttribute("page", page);
		return "modules/dlj/comJdMangerView";
	}

	@RequiresPermissions("dlj:comJdManger:edit")
	@RequestMapping(value = "save")
	public String save(ComJdManger comJdManger, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comJdManger)){
			return form(comJdManger, model);
		}
		comJdMangerService.save(comJdManger);
		addMessage(redirectAttributes, "保存保存局点成功");
		//return "redirect:"+Global.getAdminPath()+"/dlj/comJdManger/?repage";
		return "redirect:"+Global.getAdminPath()+"/dlj/comJdManger/list";
	}
	
	@RequiresPermissions("dlj:comJdManger:edit")
	@RequestMapping(value = "delete")
	public String delete(ComJdManger comJdManger, RedirectAttributes redirectAttributes) {
		comJdMangerService.delete(comJdManger);
		addMessage(redirectAttributes, "删除保存局点成功");
		//return "redirect:"+Global.getAdminPath()+"/dlj/comJdManger/?repage";
		return "redirect:"+Global.getAdminPath()+"/dlj/comJdManger/list";
	}

}