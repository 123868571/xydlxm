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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.common.utils.CacheUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.dlj.entity.ComStaff;
import com.paopao.hzgzf.modules.dlj.service.ComStaffService;
import com.paopao.hzgzf.modules.gen.service.GenTableService;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.sys.dao.UserDao;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.paopao.hzgzf.modules.sys.service.SystemService;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;

/**
 * 员工Controller
 * @author panjs
 * @version 2016-05-26
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comStaff")
public class ComStaffController extends BaseController {

	@Autowired
	private ComStaffService comStaffService;
	
	@ModelAttribute
	public ComStaff get(@RequestParam(required=false) String id) {
		ComStaff entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comStaffService.get(id);
		}
		if (entity == null){
			entity = new ComStaff();
		}
		return entity;
	}
	
	
	@RequiresPermissions("dlj:comStaff:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/dlj/comStaffIndex";
	}
	
	@RequiresPermissions("dlj:comStaff:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComStaff comStaff, HttpServletRequest request, HttpServletResponse response, Model model) {
//		User user = UserUtils.getUser();		
//		User oldUser = userDao.get(user.getId());
//        if (oldUser.getRole() != null && oldUser.getRole().getId() != null) {
//    		String dd=(String)oldUser.getRole().getDataScope();
//    		System.out.println(dd);
//        }
//		comStaff.setCreateBy(user);
		Page<ComStaff> page = comStaffService.findPage(new Page<ComStaff>(request, response), comStaff); 
		model.addAttribute("page", page);
		return "modules/dlj/comStaffList";
	}

	@RequiresPermissions("dlj:comStaff:view")
	@RequestMapping(value = "form")
	public String form(ComStaff comStaff, Model model) {
		model.addAttribute("comStaff", comStaff);
		return "modules/dlj/comStaffForm";
	}

	@RequiresPermissions("dlj:comStaff:edit")
	@RequestMapping(value = "save")
	public String save(ComStaff comStaff, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comStaff)){
			return form(comStaff, model);
		}
		comStaffService.save(comStaff);
		addMessage(redirectAttributes, "保存保存员工成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comStaff/?repage";
	}
	
	@RequiresPermissions("dlj:comStaff:edit")
	@RequestMapping(value = "delete")
	public String delete(ComStaff comStaff, RedirectAttributes redirectAttributes) {
		comStaffService.delete(comStaff);
		addMessage(redirectAttributes, "删除保存员工成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comStaff/?repage";
	}
}