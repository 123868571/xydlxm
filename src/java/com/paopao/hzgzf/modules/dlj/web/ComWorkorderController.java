/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.dlj.entity.ComWorkorder;
import com.paopao.hzgzf.modules.dlj.service.ComWorkorderService;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseApplyForm;

/**
 * 工单分配表Controller
 * @author pjs
 * @version 2016-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comWorkorder")
public class ComWorkorderController extends BaseController {

	@Autowired
	private ComWorkorderService comWorkorderService;
	
	@ModelAttribute
	public ComWorkorder get(@RequestParam(required=false) String id) {
		ComWorkorder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comWorkorderService.get(id);
		}
		if (entity == null){
			entity = new ComWorkorder();
		}
		return entity;
	}
	
	@RequiresPermissions("dlj:comWorkorder:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComWorkorder comWorkorder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComWorkorder> page = comWorkorderService.findPage(new Page<ComWorkorder>(request, response), comWorkorder); 
		model.addAttribute("page", page);
		return "modules/dlj/comWorkorderList";
	}

	@RequiresPermissions("dlj:comWorkorder:view")
	@RequestMapping(value = "form")
	public String form(ComWorkorder comWorkorder, Model model) {
		model.addAttribute("comWorkorder", comWorkorder);
		return "modules/dlj/comWorkorderForm";
	}

	@RequiresPermissions("dlj:comWorkorder:edit")
	@RequestMapping(value = "save")
	public String save(ComWorkorder comWorkorder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comWorkorder)){
			return form(comWorkorder, model);
		}
		
        String code =comWorkorder.getCode();
        if (StringUtils.isEmpty(code)) {
        	code = genCode();
        	comWorkorder.setCode(code);
        }
		comWorkorderService.save(comWorkorder);
		addMessage(redirectAttributes, "保存工单分配成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comWorkorder/?repage";
	}
	
	@RequiresPermissions("dlj:comWorkorder:edit")
	@RequestMapping(value = "delete")
	public String delete(ComWorkorder comWorkorder, RedirectAttributes redirectAttributes) {
		comWorkorderService.delete(comWorkorder);
		addMessage(redirectAttributes, "删除工单分配成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comWorkorder/?repage";
	}
	
	
	@RequestMapping(value = "print")
	public String print(ComWorkorder comWorkorder, Model model) {
		//comWorkorderService.delete(comWorkorder);
		//return "redirect:"+Global.getAdminPath()+"/dlj/comWorkorder/?repage";
		model.addAttribute("comWorkorder", comWorkorder);
		return "modules/dlj/comWorkorderPrint";
	}
	
	
    /**
     * 
     * 生成编号. <br/>
     * 
     * @author pjs
     * @return
     * @since JDK 1.6
     */
    private String genCode() {
        String s = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            s += random.nextInt(10);
        }
        String code = "GD" + DateUtils.getDate("yyyyMMdd") + s;

        ComWorkorder searchForm = new ComWorkorder();
        searchForm.setCode(code);
        List<ComWorkorder> forms = comWorkorderService.findList(searchForm);
        if (forms != null && !forms.isEmpty()) {
            return genCode();
        }
        return code;
    }

}