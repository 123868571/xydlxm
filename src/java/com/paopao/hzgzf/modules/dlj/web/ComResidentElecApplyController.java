/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.web;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.process.dao.SysProcessInstanceDao;
import com.paopao.hzgzf.common.process.entity.SysProcessInstance;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.dlj.entity.ComResidentElecApply;
import com.paopao.hzgzf.modules.dlj.service.ComResidentElecApplyService;
import com.paopao.hzgzf.modules.sys.entity.Area;
import com.paopao.hzgzf.modules.sys.service.AreaService;

/**
 * 城乡居民生活用电需求表Controller
 * @author zhoudk
 * @version 2016-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comResidentElecApply")
public class ComResidentElecApplyController extends BaseController {

	@Autowired
	private ComResidentElecApplyService comResidentElecApplyService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CrudService<SysProcessInstanceDao, SysProcessInstance> instanceService;
	
	@ModelAttribute
	public ComResidentElecApply get(@RequestParam(required=false) String id) {
		ComResidentElecApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comResidentElecApplyService.get(id);
		}
		if (entity == null){
			entity = new ComResidentElecApply();
		}
		return entity;
	}
	
	@RequiresPermissions("dlj:comResidentElecApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComResidentElecApply comResidentElecApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComResidentElecApply> page = comResidentElecApplyService.findPage(new Page<ComResidentElecApply>(request, response), comResidentElecApply); 
		model.addAttribute("page", page);
		return "modules/dlj/comResidentElecApplyList";
	}

	@RequiresPermissions("dlj:comResidentElecApply:view")
	@RequestMapping(value = "form")
	public String form(ComResidentElecApply comResidentElecApply, Model model) {
		if (comResidentElecApply != null) {
			if (StringUtils.isEmpty(comResidentElecApply.getSerial()) ) {
				comResidentElecApply.setSerial(genSerial());
			}
		}
		model.addAttribute("comResidentElecApply", comResidentElecApply);
		return "modules/dlj/comResidentElecApplyForm";
	}

	@RequiresPermissions("dlj:comResidentElecApply:edit")
	@RequestMapping(value = "save")
	public String save(ComResidentElecApply comResidentElecApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comResidentElecApply)){
			return form(comResidentElecApply, model);
		}
		comResidentElecApplyService.save(comResidentElecApply);
		addMessage(redirectAttributes, "保存居民生活用电表成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comResidentElecApply/?repage";
	}
	
	@RequiresPermissions("dlj:comResidentElecApply:edit")
	@RequestMapping(value = "delete")
	public String delete(ComResidentElecApply comResidentElecApply, RedirectAttributes redirectAttributes) {
		comResidentElecApplyService.delete(comResidentElecApply);
		addMessage(redirectAttributes, "删除居民生活用电表成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comResidentElecApply/?repage";
	}
	
	/**   
	* @Function: apply
	* @Description: 申请提交
	*
	*---------------------------------------------------------*
	* 2016-6-22     zdk           v1.0.0               修改原因
	*/
	@RequiresPermissions("dlj:comResidentElecApply:edit")
	@RequestMapping(value = { "apply" })
	@ResponseBody
    public String apply(ComResidentElecApply comResidentElecApply, 
    		HttpServletRequest request, HttpServletResponse response,
    		RedirectAttributes redirectAttributes,
    		Model model, @RequestParam String id) {
		ComResidentElecApply elecApply = comResidentElecApplyService.get(id);
		String serial = elecApply.getSerial();
        if (StringUtils.isEmpty(serial)) {
            serial = genSerial();
        }
        String result = "";
        try {
        	comResidentElecApplyService.saveApplyToSubmit(elecApply);
        	//addMessage(model, "提交用电申请成功！");
        	result = "success";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//addMessage(model, e.getMessage());
			result = e.getMessage();
		}
        return result;
    }
	
	public String audit(ComResidentElecApply comResidentElecApply, 
    		HttpServletRequest request, HttpServletResponse response,
    		RedirectAttributes redirectAttributes,
    		Model model, @RequestParam String id) {
		
		return "";
	}

	/**
     * 
     * 生成编号. <br/>
     * @return
     */
    private String genSerial() {
        String s = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            s += random.nextInt(10);
        }
        String serial = "RA" + DateUtils.getDate("yyyyMMdd") + s;

        ComResidentElecApply searchForm = new ComResidentElecApply();
        searchForm.setSerial(serial);
        List<ComResidentElecApply> forms = comResidentElecApplyService.findList(searchForm);
        if (forms != null && !forms.isEmpty()) {
            return genSerial();
        }
        return serial;
    }

}