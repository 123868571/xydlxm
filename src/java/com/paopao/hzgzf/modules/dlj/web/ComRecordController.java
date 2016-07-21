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
import com.paopao.hzgzf.modules.dlj.entity.ComRecord;
import com.paopao.hzgzf.modules.dlj.service.ComRecordService;

/**
 * 通用变更记录Controller
 * @author zdk
 * @version 2016-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comRecord")
public class ComRecordController extends BaseController {

	@Autowired
	private ComRecordService comRecordService;
	
	@ModelAttribute
	public ComRecord get(@RequestParam(required=false) String id) {
		ComRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comRecordService.get(id);
		}
		if (entity == null){
			entity = new ComRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("dlj:comRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComRecord comRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComRecord> page = comRecordService.findPage(new Page<ComRecord>(request, response), comRecord); 
		model.addAttribute("page", page);
		return "modules/dlj/comRecordList";
	}

	@RequiresPermissions("dlj:comRecord:view")
	@RequestMapping(value = "form")
	public String form(ComRecord comRecord, Model model) {
		model.addAttribute("comRecord", comRecord);
		return "modules/dlj/comRecordForm";
	}

	@RequiresPermissions("dlj:comRecord:edit")
	@RequestMapping(value = "save")
	public String save(ComRecord comRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comRecord)){
			return form(comRecord, model);
		}
		comRecordService.save(comRecord);
		addMessage(redirectAttributes, "保存通用变更记录成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comRecord/?repage";
	}
	
	@RequiresPermissions("dlj:comRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(ComRecord comRecord, RedirectAttributes redirectAttributes) {
		comRecordService.delete(comRecord);
		addMessage(redirectAttributes, "删除通用变更记录成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comRecord/?repage";
	}

}