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
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseProperty;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePropertyService;

/**
 * 房屋属性Controller
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseProperty")
public class GzfHousePropertyController extends BaseController {

	@Autowired
	private GzfHousePropertyService gzfHousePropertyService;
	
	@ModelAttribute
	public GzfHouseProperty get(@RequestParam(required=false) String id) {
		GzfHouseProperty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfHousePropertyService.get(id);
		}
		if (entity == null){
			entity = new GzfHouseProperty();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfHouseProperty:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfHouseProperty gzfHouseProperty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfHouseProperty> page = gzfHousePropertyService.findPage(new Page<GzfHouseProperty>(request, response), gzfHouseProperty); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfHousePropertyList";
	}

	@RequiresPermissions("gzf:gzfHouseProperty:view")
	@RequestMapping(value = "form")
	public String form(GzfHouseProperty gzfHouseProperty, Model model) {
		model.addAttribute("gzfHouseProperty", gzfHouseProperty);
		return "modules/gzf/gzfHousePropertyForm";
	}

	@RequiresPermissions("gzf:gzfHouseProperty:edit")
	@RequestMapping(value = "save")
	public String save(GzfHouseProperty gzfHouseProperty, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfHouseProperty)){
			return form(gzfHouseProperty, model);
		}
		gzfHousePropertyService.save(gzfHouseProperty);
		addMessage(redirectAttributes, "保存房屋属性成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfHouseProperty/?repage";
	}
	
	@RequiresPermissions("gzf:gzfHouseProperty:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfHouseProperty gzfHouseProperty, RedirectAttributes redirectAttributes) {
		gzfHousePropertyService.delete(gzfHouseProperty);
		addMessage(redirectAttributes, "删除房屋属性成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfHouseProperty/?repage";
	}

}