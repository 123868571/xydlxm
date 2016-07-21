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
import com.paopao.hzgzf.modules.gzf.entity.GzfRepair;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairService;

/**
 * 维修历史Controller
 * @author Hongjun
 * @version 2016-01-14
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfRepair")
public class GzfRepairController extends BaseController {

	@Autowired
	private GzfRepairService gzfRepairService;
	
	@ModelAttribute
	public GzfRepair get(@RequestParam(required=false) String id) {
		GzfRepair entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfRepairService.get(id);
		}
		if (entity == null){
			entity = new GzfRepair();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfRepair:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfRepair gzfRepair, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfRepair> page = gzfRepairService.findPage(new Page<GzfRepair>(request, response), gzfRepair); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfRepairList";
	}

	@RequiresPermissions("gzf:gzfRepair:view")
	@RequestMapping(value = "form")
	public String form(GzfRepair gzfRepair, Model model) {
		model.addAttribute("gzfRepair", gzfRepair);
		return "modules/gzf/gzfRepairForm";
	}

	@RequiresPermissions("gzf:gzfRepair:edit")
	@RequestMapping(value = "save")
	public String save(GzfRepair gzfRepair, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfRepair)){
			return form(gzfRepair, model);
		}
		gzfRepairService.save(gzfRepair);
		addMessage(redirectAttributes, "保存维修历史成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRepair/?repage";
	}
	
	@RequiresPermissions("gzf:gzfRepair:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfRepair gzfRepair, RedirectAttributes redirectAttributes) {
		gzfRepairService.delete(gzfRepair);
		addMessage(redirectAttributes, "删除维修历史成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRepair/?repage";
	}

}