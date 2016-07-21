/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionDetailRelation;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionDetailRelationService;

/**
 * 巡查设备详情Controller
 * @author Hongjun
 * @version 2016-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfInspectionDetailRelation")
public class GzfInspectionDetailRelationController extends BaseController {

    @Autowired
    private GzfInspectionDetailRelationService gzfInspectionDetailRelationService;

    @ModelAttribute
    public GzfInspectionDetailRelation get(@RequestParam(required = false) String id) {
        GzfInspectionDetailRelation entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfInspectionDetailRelationService.get(id);
        }
        if (entity == null) {
            entity = new GzfInspectionDetailRelation();
        }
        return entity;
    }

    @RequestMapping(value = { "list", "" })
    public String list(GzfInspectionDetailRelation gzfInspectionDetailRelation,
                       HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GzfInspectionDetailRelation> page = gzfInspectionDetailRelationService.findPage(
            new Page<GzfInspectionDetailRelation>(request, response), gzfInspectionDetailRelation);
        model.addAttribute("page", page);
        return "modules/gzf/gzfInspectionDetailRelationList";
    }

    @RequestMapping(value = "form")
    public String form(GzfInspectionDetailRelation gzfInspectionDetailRelation, Model model) {
        model.addAttribute("gzfInspectionDetailRelation", gzfInspectionDetailRelation);
        return "modules/gzf/gzfInspectionDetailRelationForm";
    }

    @RequestMapping(value = "save")
    public String save(GzfInspectionDetailRelation gzfInspectionDetailRelation, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfInspectionDetailRelation)) {
            return form(gzfInspectionDetailRelation, model);
        }
        gzfInspectionDetailRelationService.save(gzfInspectionDetailRelation);
        addMessage(redirectAttributes, "保存巡查设备详情成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspectionDetailRelation/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GzfInspectionDetailRelation gzfInspectionDetailRelation,
                         RedirectAttributes redirectAttributes) {
        gzfInspectionDetailRelationService.delete(gzfInspectionDetailRelation);
        addMessage(redirectAttributes, "删除巡查设备详情成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspectionDetailRelation/?repage";
    }

}