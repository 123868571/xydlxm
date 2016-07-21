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
import com.paopao.hzgzf.modules.gzf.entity.GzfNotice;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfNoticeService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 通知发布Controller
 * @author Hongjun
 * @version 2016-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfNotice")
public class GzfNoticeController extends BaseController {

    @Autowired
    private GzfNoticeService  gzfNoticeService;
    @Autowired
    private GzfPalacesService gzfPalacesService;
    @Autowired
    private GzfVillageService gzfVillageService;

    @ModelAttribute
    public GzfNotice get(@RequestParam(required = false) String id) {
        GzfNotice entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfNoticeService.get(id);
        }
        if (entity == null) {
            entity = new GzfNotice();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfNotice:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfNotice gzfNotice, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfNotice> page = gzfNoticeService.findPage(new Page<GzfNotice>(request, response),
            gzfNotice);
        model.addAttribute("page", page);
        return "modules/gzf/gzfNoticeList";
    }

    @RequiresPermissions("gzf:gzfNotice:view")
    @RequestMapping(value = "form")
    public String form(GzfNotice gzfNotice, Model model) {
        model.addAttribute("gzfNotice", gzfNotice);
        GzfVillage village = new GzfVillage();
        GzfPalaces gzfPalaces = new GzfPalaces();
        model.addAttribute("gzfPalacesList", gzfPalacesService.findList(gzfPalaces));
        model.addAttribute("gzfVillageList", gzfVillageService.findList(village));
        return "modules/gzf/gzfNoticeForm";
    }

    @RequiresPermissions("gzf:gzfNotice:edit")
    @RequestMapping(value = "save")
    public String save(GzfNotice gzfNotice, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfNotice)) {
            return form(gzfNotice, model);
        }
        gzfNoticeService.save(gzfNotice);
        addMessage(redirectAttributes, "保存通知发布成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfNotice/?repage";
    }

    @RequiresPermissions("gzf:gzfNotice:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfNotice gzfNotice, RedirectAttributes redirectAttributes) {
        gzfNoticeService.delete(gzfNotice);
        addMessage(redirectAttributes, "删除通知发布成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfNotice/?repage";
    }

}