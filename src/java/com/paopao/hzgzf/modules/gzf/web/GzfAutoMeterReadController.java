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
import com.paopao.hzgzf.modules.gzf.entity.GzfAutoMeterRead;
import com.paopao.hzgzf.modules.gzf.service.GzfAutoMeterReadService;

/**
 * 自动抄表Controller
 * @author songyahe
 * @version 2016-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfAutoMeterRead")
public class GzfAutoMeterReadController extends BaseController {

	@Autowired
	private GzfAutoMeterReadService gzfAutoMeterReadService;
	
	@ModelAttribute
	public GzfAutoMeterRead get(@RequestParam(required=false) String id) {
		GzfAutoMeterRead entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfAutoMeterReadService.get(id);
		}
		if (entity == null){
			entity = new GzfAutoMeterRead();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfAutoMeterRead:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfAutoMeterRead gzfAutoMeterRead, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfAutoMeterRead> page = gzfAutoMeterReadService.findPage(new Page<GzfAutoMeterRead>(request, response), gzfAutoMeterRead); 
		model.addAttribute("page", page);
		return "modules/gzf/gzfAutoMeterReadList";
	}

	@RequiresPermissions("gzf:gzfAutoMeterRead:view")
	@RequestMapping(value = "form")
	public String form(GzfAutoMeterRead gzfAutoMeterRead, Model model) {
		model.addAttribute("gzfAutoMeterRead", gzfAutoMeterRead);
		return "modules/gzf/gzfAutoMeterReadForm";
	}

	@RequiresPermissions("gzf:gzfAutoMeterRead:edit")
	@RequestMapping(value = "save")
	public String save(GzfAutoMeterRead gzfAutoMeterRead, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfAutoMeterRead)){
			return form(gzfAutoMeterRead, model);
		}
		gzfAutoMeterReadService.save(gzfAutoMeterRead);
		addMessage(redirectAttributes, "保存保存抄表记录成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfAutoMeterRead/?repage";
	}
	
	@RequiresPermissions("gzf:gzfAutoMeterRead:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfAutoMeterRead gzfAutoMeterRead, RedirectAttributes redirectAttributes) {
		gzfAutoMeterReadService.delete(gzfAutoMeterRead);
		addMessage(redirectAttributes, "删除保存抄表记录成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfAutoMeterRead/?repage";
	}

}