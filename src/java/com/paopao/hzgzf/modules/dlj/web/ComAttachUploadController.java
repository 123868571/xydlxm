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
import com.paopao.hzgzf.modules.dlj.entity.ComAttachUpload;
import com.paopao.hzgzf.modules.dlj.service.ComAttachUploadService;

/**
 * 通用附件上传Controller
 * @author zdk
 * @version 2016-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comAttachUpload")
public class ComAttachUploadController extends BaseController {

	@Autowired
	private ComAttachUploadService comAttachUploadService;
	
	@ModelAttribute
	public ComAttachUpload get(@RequestParam(required=false) String id) {
		ComAttachUpload entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comAttachUploadService.get(id);
		}
		if (entity == null){
			entity = new ComAttachUpload();
		}
		return entity;
	}
	
	@RequiresPermissions("dlj:comAttachUpload:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComAttachUpload comAttachUpload, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComAttachUpload> page = comAttachUploadService.findPage(new Page<ComAttachUpload>(request, response), comAttachUpload); 
		model.addAttribute("page", page);
		return "modules/dlj/comAttachUploadList";
	}

	@RequiresPermissions("dlj:comAttachUpload:view")
	@RequestMapping(value = "form")
	public String form(ComAttachUpload comAttachUpload, Model model) {
		model.addAttribute("comAttachUpload", comAttachUpload);
		return "modules/dlj/comAttachUploadForm";
	}

	@RequiresPermissions("dlj:comAttachUpload:edit")
	@RequestMapping(value = "save")
	public String save(ComAttachUpload comAttachUpload, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comAttachUpload)){
			return form(comAttachUpload, model);
		}
		comAttachUploadService.save(comAttachUpload);
		addMessage(redirectAttributes, "保存通用附件上传成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comAttachUpload/?repage";
	}
	
	@RequiresPermissions("dlj:comAttachUpload:edit")
	@RequestMapping(value = "delete")
	public String delete(ComAttachUpload comAttachUpload, RedirectAttributes redirectAttributes) {
		comAttachUploadService.delete(comAttachUpload);
		addMessage(redirectAttributes, "删除通用附件上传成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comAttachUpload/?repage";
	}

}