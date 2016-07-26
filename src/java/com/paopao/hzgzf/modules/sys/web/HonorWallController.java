/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.sys.web;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.sys.entity.HonorWall;
import com.paopao.hzgzf.modules.sys.service.HonorWallService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 荣誉墙Controller
 * @author luoo
 * @version 2016-07-26
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/honorWall")
public class HonorWallController extends BaseController {

	@Autowired
	private HonorWallService honorWallService;
	
	@ModelAttribute
	public HonorWall get(@RequestParam(required=false) String id) {
		HonorWall entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = honorWallService.get(id);
		}
		if (entity == null){
			entity = new HonorWall();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:honorWall:view")
	@RequestMapping(value = {"list", ""})
	public String list(HonorWall honorWall, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HonorWall> page = honorWallService.findPage(new Page<HonorWall>(request, response), honorWall); 
		model.addAttribute("page", page);
		return "modules/sys/honorWallList";
	}

	@RequiresPermissions("sys:honorWall:view")
	@RequestMapping(value = "form")
	public String form(HonorWall honorWall, Model model) {

		if (StringUtils.isBlank(honorWall.getId())){
			List<HonorWall> list = honorWallService.findAll();
			if (list.size() > 0){
				honorWall.setSort(list.get(list.size()-1).getSort() + 30);
			}
		}

		model.addAttribute("honorWall", honorWall);
		return "modules/sys/honorWallForm";
	}

	@RequiresPermissions("sys:honorWall:edit")
	@RequestMapping(value = "save")
	public String save(HonorWall honorWall, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, honorWall)){
			return form(honorWall, model);
		}
		honorWallService.save(honorWall);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/sys/honorWall/?repage";
	}
	
	@RequiresPermissions("sys:honorWall:edit")
	@RequestMapping(value = "delete")
	public String delete(HonorWall honorWall, RedirectAttributes redirectAttributes) {
		honorWallService.delete(honorWall);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/sys/honorWall/?repage";
	}

}