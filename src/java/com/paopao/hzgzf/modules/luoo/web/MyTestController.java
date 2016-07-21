/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.luoo.web;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.luoo.entity.MyTest;
import com.paopao.hzgzf.modules.luoo.service.MyTestService;
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

/**
 * helloController
 * @author luoo
 * @version 2016-07-21
 */
@Controller
@RequestMapping(value = "${adminPath}/luoo/myTest")
public class MyTestController extends BaseController {

	@Autowired
	private MyTestService myTestService;

	@ModelAttribute
	public MyTest get(@RequestParam(required=false) String id) {
		MyTest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = myTestService.get(id);
		}
		if (entity == null){
			entity = new MyTest();
		}
		return entity;
	}

	@RequiresPermissions("luoo:myTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyTest myTest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MyTest> page = myTestService.findPage(new Page<MyTest>(request, response), myTest);
		model.addAttribute("page", page);
		return "modules/luoo/myTestList";
	}

	@RequiresPermissions("luoo:myTest:view")
	@RequestMapping(value = "form")
	public String form(MyTest myTest, Model model) {
		model.addAttribute("myTest", myTest);
		return "modules/luoo/myTestForm";
	}

	@RequiresPermissions("luoo:myTest:edit")
	@RequestMapping(value = "save")
	public String save(MyTest myTest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, myTest)){
			return form(myTest, model);
		}
		myTestService.save(myTest);
		addMessage(redirectAttributes, "保存hello成功");
		return "redirect:"+Global.getAdminPath()+"/luoo/myTest/?repage";
	}

	@RequiresPermissions("luoo:myTest:edit")
	@RequestMapping(value = "delete")
	public String delete(MyTest myTest, RedirectAttributes redirectAttributes) {
		myTestService.delete(myTest);
		addMessage(redirectAttributes, "删除hello成功");
		return "redirect:"+Global.getAdminPath()+"/luoo/myTest/?repage";
	}

}