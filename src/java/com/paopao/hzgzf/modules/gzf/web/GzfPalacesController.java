/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 苑Controller
 * @author Hongjun
 * @version 2015-12-20
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfPalaces")
public class GzfPalacesController extends BaseController {

    @Autowired
    private GzfPalacesService gzfPalacesService;

    @Autowired
    private GzfVillageService gzfVillageService;

    @ModelAttribute
    public GzfPalaces get(@RequestParam(required = false) String id) {
        GzfPalaces entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfPalacesService.get(id);
        }
        if (entity == null) {
            entity = new GzfPalaces();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfPalaces:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfPalaces gzfPalaces, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfPalaces> page = gzfPalacesService.findPage(new Page<GzfPalaces>(request, response),
            gzfPalaces);
        model.addAttribute("page", page);
        model.addAttribute("gzfVillageId", gzfPalaces.getGzfVillageId());
        return "modules/gzf/gzfPalacesList";
    }

    @RequiresPermissions("gzf:gzfPalaces:view")
    @RequestMapping(value = "form")
    public String form(GzfPalaces gzfPalaces, Model model) {
        GzfVillage gzfVillage = gzfVillageService.get(gzfPalaces.getGzfVillageId());
        gzfPalaces.setGzfVillage(gzfVillage);
        model.addAttribute("gzfPalaces", gzfPalaces);
        return "modules/gzf/gzfPalacesForm";
    }

    @RequiresPermissions("gzf:gzfPalaces:edit")
    @RequestMapping(value = "save")
    public String save(GzfPalaces gzfPalaces, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfPalaces)) {
            return form(gzfPalaces, model);
        }
        gzfPalacesService.save(gzfPalaces);
        addMessage(redirectAttributes, "保存苑成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfPalaces/?repage&gzfVillageId="
               + gzfPalaces.getGzfVillageId();
    }

    @RequiresPermissions("gzf:gzfPalaces:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfPalaces gzfPalaces, RedirectAttributes redirectAttributes) {
        gzfPalacesService.delete(gzfPalaces);
        addMessage(redirectAttributes, "删除苑成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfPalaces/?repage&gzfVillageId="
               + gzfPalaces.getGzfVillageId();
    }

    @RequestMapping(value = "getGzfPalaces")
    @ResponseBody
    public GzfPalaces getGzfPalaces(GzfPalaces gzfPalaces) {
        return gzfPalaces;
    }

    @RequiresPermissions("gzf:gzfPalaces:edit")
    @RequestMapping(value = "moredelete")
    @ResponseBody
    public String moredelete(String[] valArr) {
        if (valArr.length <= 0) {
            return "false";
        } else {
            for (String str : valArr) {
                GzfPalaces gzfPalaces = new GzfPalaces();
                gzfPalaces.setId(str);
                try {
                    gzfPalacesService.delete(gzfPalaces);
                } catch (Exception e) {
                    return "error";
                }
            }
        }

        //gzfVillageService.delete(gzfVillage);
        return "success";
    }

    @RequestMapping(value = "getGzfPalacesByVillage")
    @ResponseBody
    public List<GzfPalaces> getGzfPalacesByVillage(String villageId) {
        GzfPalaces gzfPalaces = new GzfPalaces();
        gzfPalaces.setGzfVillageId(villageId);
        List<GzfPalaces> gzfPalacesList = gzfPalacesService.findList(gzfPalaces);
        return gzfPalacesList;
    }

}
