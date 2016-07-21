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
import com.paopao.hzgzf.modules.dlj.entity.ComGps;
import com.paopao.hzgzf.modules.dlj.service.ComGpsService;

/**
 * GPSController
 * @author pjs
 * @version 2016-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comGps")
public class ComGpsController extends BaseController {

	@Autowired
	private ComGpsService comGpsService;
	
	@ModelAttribute
	public ComGps get(@RequestParam(required=false) String id) {
		ComGps entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comGpsService.get(id);
		}
		if (entity == null){
			entity = new ComGps();
		}
		return entity;
	}
	
	@RequiresPermissions("dlj:comGps:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComGps comGps, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComGps> page = comGpsService.findPage(new Page<ComGps>(request, response), comGps); 
		model.addAttribute("page", page);
		return "modules/dlj/comGpsList";
	}

	@RequiresPermissions("dlj:comGps:view")
	@RequestMapping(value = "form")
	public String form(ComGps comGps, Model model) {
		model.addAttribute("comGps", comGps);
		return "modules/dlj/comGpsForm";
	}

	@RequiresPermissions("dlj:comGps:edit")
	@RequestMapping(value = "save")
	public String save(ComGps comGps, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comGps)){
			return form(comGps, model);
		}
		comGpsService.save(comGps);
		addMessage(redirectAttributes, "保存保存GPS成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comGps/?repage";
	}
	
	@RequiresPermissions("dlj:comGps:edit")
	@RequestMapping(value = "delete")
	public String delete(ComGps comGps, RedirectAttributes redirectAttributes) {
		comGpsService.delete(comGps);
		addMessage(redirectAttributes, "删除保存GPS成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comGps/?repage";
	}

}