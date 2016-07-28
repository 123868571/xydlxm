/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.test.web;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.test.entity.LuooTest;
import com.paopao.hzgzf.modules.test.service.LuooTestService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * testController
 * @author luoo
 * @version 2016-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/test/luooTest")
public class LuooTestController extends BaseController {

	@Autowired
	private LuooTestService luooTestService;
	
	@ModelAttribute
	public LuooTest get(@RequestParam(required=false) String id) {
		LuooTest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = luooTestService.get(id);
		}
		if (entity == null){
			entity = new LuooTest();
		}
		return entity;
	}
	
	@RequiresPermissions("test:luooTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(LuooTest luooTest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LuooTest> page = luooTestService.findPage(new Page<LuooTest>(request, response), luooTest); 
		model.addAttribute("page", page);
		return "modules/test/luooTestList";
	}

	@RequiresPermissions("test:luooTest:view")
	@RequestMapping(value = "form")
	public String form(LuooTest luooTest, Model model) {
		model.addAttribute("luooTest", luooTest);
		return "modules/test/luooTestForm";
	}

	@RequiresPermissions("test:luooTest:edit")
	@RequestMapping(value = "save")
	public String save(LuooTest luooTest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, luooTest)){
			return form(luooTest, model);
		}
		luooTestService.save(luooTest);
		addMessage(redirectAttributes, "保存test成功");
		return "redirect:"+Global.getAdminPath()+"/test/luooTest/?repage";
	}
	
	@RequiresPermissions("test:luooTest:edit")
	@RequestMapping(value = "delete")
	public String delete(LuooTest luooTest, RedirectAttributes redirectAttributes) {
		luooTestService.delete(luooTest);
		addMessage(redirectAttributes, "删除test成功");
		return "redirect:"+Global.getAdminPath()+"/test/luooTest/?repage";
	}

	@RequestMapping(value = "checkForm1", method = {RequestMethod.POST })
	@ResponseBody
	public String checkForm1(HttpServletRequest request,@RequestBody  String ss) throws IOException {

		LuooTest test = JSON.parseObject(ss, LuooTest.class);

		Gson gson = new Gson();
		String str =  gson.toJson(test);

		return str;
	}

	@RequestMapping(value = "checkForm2", method = {RequestMethod.POST })
	@ResponseBody
	public String checkForm2(@RequestBody  LuooTest test) throws IOException {

		Gson gson = new Gson();
		String str =  gson.toJson(test);

		return str;
	}


}