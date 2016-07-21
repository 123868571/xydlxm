/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.List;

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
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfRemind;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfRemindService;

/**
 * 提醒Controller
 * @author Hongjun
 * @version 2016-01-17
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfRemind")
public class GzfRemindController extends BaseController {

	@Autowired
	private GzfRemindService gzfRemindService;
	
	@Autowired
	private GzfHouseholdInfoService gzfHouseholdInfoService;
	
	@Autowired
	private GzfHouseInfoService gzfHouseInfoService;
	
	@Autowired
	private GzfPalacesService gzfPalacesService;
	
	@ModelAttribute
	public GzfRemind get(@RequestParam(required=false) String id) {
		GzfRemind entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gzfRemindService.get(id);
		}
		if (entity == null){
			entity = new GzfRemind();
		}
		return entity;
	}
	
	@RequiresPermissions("gzf:gzfRemind:view")
	@RequestMapping(value = {"list", ""})
	public String list(GzfRemind gzfRemind, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GzfRemind> page = gzfRemindService.findPage(new Page<GzfRemind>(request, response), gzfRemind); 
		List<GzfRemind> list = page.getList();
		if(!list.isEmpty()){
			for(GzfRemind gzfr : list){
				if(gzfr.getGzfHouseholdInfoId() != null && !"".equals(gzfr.getGzfHouseholdInfoId())){
					GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfr.getGzfHouseholdInfoId());
					if(gzfhhi != null){
						gzfr.setGzfHouseholdInfo(gzfhhi);
					}
				}
				
				if(gzfr.getGzfHouseInfo() != null && !"".equals(gzfr.getGzfHousInfoId())){
					GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfr.getGzfHouseInfo());
					if(gzfhi != null){
						if(gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())){
							GzfPalaces gzfp = gzfPalacesService.get(gzfhi.getGzfPalacesId());
							if(gzfp != null){
								gzfhi.setGzfPalaces(gzfp);
							}
						}
						
						gzfr.setGzfHouseInfo(gzfhi);
					}
				}
			}
		}
		page.setList(list);
		model.addAttribute("page", page);
		return "modules/gzf/gzfRemindList";
	}

	@RequiresPermissions("gzf:gzfRemind:view")
	@RequestMapping(value = "form")
	public String form(GzfRemind gzfRemind, Model model) {
		model.addAttribute("gzfRemind", gzfRemind);
		return "modules/gzf/gzfRemindForm";
	}

	@RequiresPermissions("gzf:gzfRemind:edit")
	@RequestMapping(value = "save")
	public String save(GzfRemind gzfRemind, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfRemind)){
			return form(gzfRemind, model);
		}
		gzfRemindService.save(gzfRemind);
		addMessage(redirectAttributes, "保存提醒成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRemind/?repage";
	}
	
	@RequiresPermissions("gzf:gzfRemind:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfRemind gzfRemind, RedirectAttributes redirectAttributes) {
		gzfRemindService.delete(gzfRemind);
		addMessage(redirectAttributes, "删除提醒成功");
		return "redirect:"+Global.getAdminPath()+"/gzf/gzfRemind/?repage";
	}

}