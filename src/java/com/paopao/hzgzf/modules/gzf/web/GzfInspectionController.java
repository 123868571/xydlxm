/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.ArrayList;
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
import com.paopao.hzgzf.modules.gzf.entity.GzfInspection;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionCategory;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionDetail;
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionDetailRelation;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionCategoryService;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionDetailRelationService;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionDetailService;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 巡查设备Controller
 * @author Hongjun
 * @version 2016-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfInspection")
public class GzfInspectionController extends BaseController {

    @Autowired
    private GzfInspectionService               gzfInspectionService;
    @Autowired
    private GzfVillageService                  gzfVillageService;
    @Autowired
    private GzfInspectionCategoryService       gzfInspectionCategoryService;
    @Autowired
    private GzfInspectionDetailService         gzfInspectionDetailService;
    @Autowired
    private GzfInspectionDetailRelationService gzfInspectionDetailRelationService;

    @ModelAttribute
    public GzfInspection get(@RequestParam(required = false) String id) {
        GzfInspection entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfInspectionService.get(id);
        }
        if (entity == null) {
            entity = new GzfInspection();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfInspection:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfInspection gzfInspection, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfInspection> page = gzfInspectionService.findPage(new Page<GzfInspection>(request,
            response), gzfInspection);
        model.addAttribute("page", page);
        model.addAttribute("gzfVillageList", gzfVillageService.findList(new GzfVillage()));
        model.addAttribute("gzfInspectionCategoryList",
            gzfInspectionCategoryService.findList(new GzfInspectionCategory()));
        return "modules/gzf/gzfInspectionList";
    }

    @RequiresPermissions("gzf:gzfInspection:view")
    @RequestMapping(value = "form")
    public String form(GzfInspection gzfInspection, Model model) {
        GzfInspectionDetailRelation gzfInspectionDetailRelation = new GzfInspectionDetailRelation();
        gzfInspectionDetailRelation.setGzfInspectionId(gzfInspection.getId());
        if (StringUtils.isNotEmpty(gzfInspection.getId())) {
            List<GzfInspectionDetailRelation> rs = gzfInspectionDetailRelationService
                .findList(gzfInspectionDetailRelation);
            List<String> ids = new ArrayList<String>();
            for (GzfInspectionDetailRelation gzfInspectionDetailRelation2 : rs) {
                ids.add(gzfInspectionDetailRelation2.getGzfInspectionDetailId());
            }
            gzfInspection.setGzfInspectionDetailIds(ids);
        }
        model.addAttribute("gzfInspection", gzfInspection);
        model.addAttribute("gzfVillageList", gzfVillageService.findList(new GzfVillage()));
        model.addAttribute("gzfInspectionCategoryList",
            gzfInspectionCategoryService.findList(new GzfInspectionCategory()));
        model.addAttribute("gzfInspectionDetailList",
            gzfInspectionDetailService.findList(new GzfInspectionDetail()));
        return "modules/gzf/gzfInspectionForm";
    }

    @RequiresPermissions("gzf:gzfInspection:edit")
    @RequestMapping(value = "save")
    public String save(GzfInspection gzfInspection, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfInspection)) {
            return form(gzfInspection, model);
        }
        gzfInspectionService.save(gzfInspection);

        for (String gzfInspectionDetailId : gzfInspection.getGzfInspectionDetailIds()) {
            GzfInspectionDetailRelation gzfInspectionDetailRelation = new GzfInspectionDetailRelation();
            gzfInspectionDetailRelation.setGzfInspectionId(gzfInspection.getId());
            gzfInspectionDetailRelation.setGzfInspectionDetailId(gzfInspectionDetailId);
            gzfInspectionDetailRelationService.save(gzfInspectionDetailRelation);
        }

        addMessage(redirectAttributes, "保存巡查设备成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspection/?repage";
    }

    @RequiresPermissions("gzf:gzfInspection:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfInspection gzfInspection, RedirectAttributes redirectAttributes) {
        gzfInspectionService.delete(gzfInspection);
        addMessage(redirectAttributes, "删除巡查设备成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspection/?repage";
    }

}