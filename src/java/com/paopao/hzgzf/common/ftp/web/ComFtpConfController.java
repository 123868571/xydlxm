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
import com.paopao.hzgzf.common.ftp.entity.ComFtpConf;
import com.paopao.hzgzf.common.ftp.service.ComFtpConfService;

/**
 * ftp服务器路径配置Controller
 * @author zdk
 * @version 2016-07-17
 */
@Controller
@RequestMapping(value = "${adminPath}/ftp/comFtpConf")
public class ComFtpConfController extends BaseController {

	@Autowired
	private ComFtpConfService comFtpConfService;
	
	@ModelAttribute
	public ComFtpConf get(@RequestParam(required=false) String id) {
		ComFtpConf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comFtpConfService.get(id);
		}
		if (entity == null){
			entity = new ComFtpConf();
		}
		return entity;
	}
	
	@RequiresPermissions("ftp:comFtpConf:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComFtpConf comFtpConf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComFtpConf> page = comFtpConfService.findPage(new Page<ComFtpConf>(request, response), comFtpConf); 
		model.addAttribute("page", page);
		return "common/ftp/comFtpConfList";
	}

	@RequiresPermissions("ftp:comFtpConf:view")
	@RequestMapping(value = "form")
	public String form(ComFtpConf comFtpConf, Model model) {
		model.addAttribute("comFtpConf", comFtpConf);
		return "common/ftp/comFtpConfForm";
	}

	@RequiresPermissions("ftp:comFtpConf:edit")
	@RequestMapping(value = "save")
	public String save(ComFtpConf comFtpConf, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comFtpConf)){
			return form(comFtpConf, model);
		}
		comFtpConfService.save(comFtpConf);
		addMessage(redirectAttributes, "保存ftp服务器路径配置成功");
		return "redirect:"+Global.getAdminPath()+"/ftp/comFtpConf/?repage";
	}
	
	@RequiresPermissions("ftp:comFtpConf:edit")
	@RequestMapping(value = "delete")
	public String delete(ComFtpConf comFtpConf, RedirectAttributes redirectAttributes) {
		comFtpConfService.delete(comFtpConf);
		addMessage(redirectAttributes, "删除ftp服务器路径配置成功");
		return "redirect:"+Global.getAdminPath()+"/ftp/comFtpConf/?repage";
	}

}