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
import com.paopao.hzgzf.modules.gzf.entity.GzfSystemName;
import com.paopao.hzgzf.modules.gzf.service.GzfSystemNameService;

/**
 * 系统名称Controller
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfSystemName")
public class GzfSystemNameController extends BaseController {

	@Autowired
	private GzfSystemNameService gzfSystemNameService;
	
	@ModelAttribute
	public GzfSystemName get(@RequestParam(required=false) String id) {
		GzfSystemName entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfSystemNameService.get(id);
		}
		if (entity == null){
			entity = new GzfSystemName();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfSystemName:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfSystemName gzfSystemName, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfSystemName> page = gzfSystemNameService.findPage(new Page<GzfSystemName>(request, response), gzfSystemName); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfSystemNameList";
	}

	@RequiresPermissions("gzf:gzfSystemName:view")
	@RequestMapping(value = "form")
	public String form(GzfSystemName gzfSystemName, Model model) {
		model.addAttribute("gzfSystemName", gzfSystemName);
		return "modules/gzf/gzfSystemNameForm";
	}

	@RequiresPermissions("gzf:gzfSystemName:edit")
	@RequestMapping(value = "save")
	public String save(GzfSystemName gzfSystemName, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfSystemName)){
			return form(gzfSystemName, model);
		}
		gzfSystemNameService.save(gzfSystemName);
		addMessage(redirectAttributes, "保存系统名称成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfSystemName/?repage";
	}
	
	@RequiresPermissions("gzf:gzfSystemName:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfSystemName gzfSystemName, RedirectAttributes redirectAttributes) {
		gzfSystemNameService.delete(gzfSystemName);
		addMessage(redirectAttributes, "删除系统名称成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfSystemName/?repage";
	}

}