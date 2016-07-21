/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.ftp.web;

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
import com.paopao.hzgzf.common.ftp.entity.ComFtp;
import com.paopao.hzgzf.common.ftp.service.ComFtpService;

/**
 * ftp服务器配置Controller
 * @author zdk
 * @version 2016-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/ftp/comFtp")
public class ComFtpController extends BaseController {

	@Autowired
	private ComFtpService comFtpService;
	
	@ModelAttribute
	public ComFtp get(@RequestParam(required=false) String id) {
		ComFtp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comFtpService.get(id);
		}
		if (entity == null){
			entity = new ComFtp();
		}
		return entity;
	}
	
	@RequiresPermissions("ftp:comFtp:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComFtp comFtp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComFtp> page = comFtpService.findPage(new Page<ComFtp>(request, response), comFtp); 
		model.addAttribute("page", page);
		return "common/ftp/comFtpList";
	}

	@RequiresPermissions("ftp:comFtp:view")
	@RequestMapping(value = "form")
	public String form(ComFtp comFtp, Model model) {
		model.addAttribute("comFtp", comFtp);
		return "common/ftp/comFtpForm";
	}

	@RequiresPermissions("ftp:comFtp:edit")
	@RequestMapping(value = "save")
	public String save(ComFtp comFtp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comFtp)){
			return form(comFtp, model);
		}
		comFtpService.save(comFtp);
		addMessage(redirectAttributes, "保存ftp服务器配置成功");
		return "redirect:"+Global.getAdminPath()+"/ftp/comFtp/?repage";
	}
	
	@RequiresPermissions("ftp:comFtp:edit")
	@RequestMapping(value = "delete")
	public String delete(ComFtp comFtp, RedirectAttributes redirectAttributes) {
		comFtpService.delete(comFtp);
		addMessage(redirectAttributes, "删除ftp服务器配置成功");
		return "redirect:"+Global.getAdminPath()+"/ftp/comFtp/?repage";
	}

}