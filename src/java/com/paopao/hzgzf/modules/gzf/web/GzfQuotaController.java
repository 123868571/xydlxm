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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfQuota;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfQuotaService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 额度限制Controller
 * @author Hongjun
 * @version 2016-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfQuota")
public class GzfQuotaController extends BaseController {

    @Autowired
    private GzfQuotaService   gzfQuotaService;
    @Autowired
    private GzfPalacesService gzfPalacesService;

    @Autowired
    private GzfVillageService gzfVillageService;

    @ModelAttribute
    public GzfQuota get(@RequestParam(required = false) String id) {
        GzfQuota entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfQuotaService.get(id);
        }
        if (entity == null) {
            entity = new GzfQuota();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfQuota:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfQuota gzfQuota, HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        Page<GzfQuota> page = gzfQuotaService.findPage(new Page<GzfQuota>(request, response),
            gzfQuota);
        model.addAttribute("page", page);
        return "modules/gzf/gzfQuotaList";
    }

    @RequiresPermissions("gzf:gzfQuota:view")
    @RequestMapping(value = "form")
    public String form(GzfQuota gzfQuota, Model model) {
        model.addAttribute("gzfQuota", gzfQuota);
        GzfVillage village = new GzfVillage();
        GzfPalaces gzfPalaces = new GzfPalaces();
        model.addAttribute("gzfPalacesList", gzfPalacesService.findList(gzfPalaces));
        model.addAttribute("gzfVillageList", gzfVillageService.findList(village));
        return "modules/gzf/gzfQuotaForm";
    }

    @RequiresPermissions("gzf:gzfQuota:edit")
    @RequestMapping(value = "save")
    public String save(GzfQuota gzfQuota, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfQuota)) {
            return form(gzfQuota, model);
        }
        gzfQuotaService.save(gzfQuota);
        addMessage(redirectAttributes, "保存额度限制成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfQuota/?repage";
    }

    @RequiresPermissions("gzf:gzfQuota:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfQuota gzfQuota, RedirectAttributes redirectAttributes) {
        gzfQuotaService.delete(gzfQuota);
        addMessage(redirectAttributes, "删除额度限制成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfQuota/?repage";
    }

}