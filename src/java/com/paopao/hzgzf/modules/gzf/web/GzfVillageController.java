/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

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
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 小区Controller
 * @author Hongjun
 * @version 2015-12-20
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfVillage")
public class GzfVillageController extends BaseController {

    @Autowired
    private GzfVillageService gzfVillageService;

    @ModelAttribute
    public GzfVillage get(@RequestParam(required = false) String id) {
        GzfVillage entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfVillageService.get(id);
        }
        if (entity == null) {
            entity = new GzfVillage();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfVillage:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfVillage gzfVillage, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfVillage> page = gzfVillageService.findPage(new Page<GzfVillage>(request, response),
            gzfVillage);
        model.addAttribute("page", page);
        return "modules/gzf/gzfVillageList";
    }

    @RequiresPermissions("gzf:gzfVillage:view")
    @RequestMapping(value = "form")
    public String form(GzfVillage gzfVillage, Model model) {
        model.addAttribute("gzfVillage", gzfVillage);
        return "modules/gzf/gzfVillageForm";
    }

    @RequiresPermissions("gzf:gzfVillage:edit")
    @RequestMapping(value = "save")
    public String save(GzfVillage gzfVillage, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfVillage)) {
            return form(gzfVillage, model);
        }
        if (!StringUtils.isEmpty(gzfVillage.getCheckPalaces())) {
            if (!StringUtils.isEmpty(gzfVillage.getPalaces())) {
                gzfVillage.setNextLevel(gzfVillage.getPalaces());
            } else {
                gzfVillage.setNextLevel("苑");
            }
        } else {
            if (!StringUtils.isEmpty(gzfVillage.getBuilding())) {
                gzfVillage.setNextLevel(gzfVillage.getBuilding());
            } else {
                gzfVillage.setNextLevel(gzfVillage.getBuilding());
            }
        }
        gzfVillageService.save(gzfVillage);
        addMessage(redirectAttributes, "保存小区成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfVillage/?repage";
    }

    @RequiresPermissions("gzf:gzfVillage:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfVillage gzfVillage, RedirectAttributes redirectAttributes) {
        gzfVillageService.delete(gzfVillage);
        addMessage(redirectAttributes, "删除小区成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfVillage/?repage";
    }

    @RequiresPermissions("gzf:gzfVillage:edit")
    @RequestMapping(value = "moredelete")
    @ResponseBody
    public String moredelete(String[] valArr) {
        if (valArr.length <= 0) {
            return "false";
        } else {
            for (String str : valArr) {
                GzfVillage gzfVillage = new GzfVillage();
                gzfVillage.setId(str);
                try {
                    gzfVillageService.delete(gzfVillage);
                } catch (Exception e) {
                    return "error";
                }
            }
        }

        //gzfVillageService.delete(gzfVillage);
        return "success";
    }

    @RequestMapping(value = "get")
    @ResponseBody
    public GzfVillage getVillage(@RequestParam(required = false) String id) {
        GzfVillage entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfVillageService.get(id);
        }
        if (entity == null) {
            entity = new GzfVillage();
        }
        return entity;
    }

}