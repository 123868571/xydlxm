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
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionCategory;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionDetail;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionCategoryService;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionDetailService;

/**
 * 巡查设备详情Controller
 * @author Hongjun
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfInspectionDetail")
public class GzfInspectionDetailController extends BaseController {

    @Autowired
    private GzfInspectionDetailService   gzfInspectionDetailService;
    @Autowired
    private GzfInspectionCategoryService gzfInspectionCategoryService;

    @ModelAttribute
    public GzfInspectionDetail get(@RequestParam(required = false) String id) {
        GzfInspectionDetail entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfInspectionDetailService.get(id);
        }
        if (entity == null) {
            entity = new GzfInspectionDetail();
        }
        return entity;
    }

    @RequestMapping(value = { "list", "" })
    public String list(GzfInspectionDetail gzfInspectionDetail, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfInspectionDetail> page = gzfInspectionDetailService.findPage(
            new Page<GzfInspectionDetail>(request, response), gzfInspectionDetail);
        model.addAttribute("page", page);
        return "modules/gzf/gzfInspectionDetailList";
    }

    @RequestMapping(value = "form")
    public String form(GzfInspectionDetail gzfInspectionDetail, Model model) {
        model.addAttribute("gzfInspectionDetail", gzfInspectionDetail);
        model.addAttribute("gzfInspectionCategoryList",
            gzfInspectionCategoryService.findList(new GzfInspectionCategory()));
        return "modules/gzf/gzfInspectionDetailForm";
    }

    @RequestMapping(value = "save")
    public String save(GzfInspectionDetail gzfInspectionDetail, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfInspectionDetail)) {
            return form(gzfInspectionDetail, model);
        }
        gzfInspectionDetailService.save(gzfInspectionDetail);
        addMessage(redirectAttributes, "保存巡查设备详情成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspectionDetail/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GzfInspectionDetail gzfInspectionDetail,
                         RedirectAttributes redirectAttributes) {
        gzfInspectionDetailService.delete(gzfInspectionDetail);
        addMessage(redirectAttributes, "删除巡查设备详情成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspectionDetail/?repage";
    }

}