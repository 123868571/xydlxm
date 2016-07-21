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
import com.paopao.hzgzf.modules.gzf.entity.GzfFaultCategory;
import com.paopao.hzgzf.modules.gzf.entity.GzfMaintenanceLog;
import com.paopao.hzgzf.modules.gzf.service.GzfFaultCategoryService;
import com.paopao.hzgzf.modules.gzf.service.GzfMaintenanceLogService;

/**
 * 维修日志Controller
 * @author Hongjun
 * @version 2016-01-17
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfMaintenanceLog")
public class GzfMaintenanceLogController extends BaseController {

    @Autowired
    private GzfMaintenanceLogService gzfMaintenanceLogService;

    @Autowired
    private GzfFaultCategoryService  gzfFaultCategoryService;

    @ModelAttribute
    public GzfMaintenanceLog get(@RequestParam(required = false) String id) {
        GzfMaintenanceLog entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfMaintenanceLogService.get(id);
        }
        if (entity == null) {
            entity = new GzfMaintenanceLog();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfMaintenanceLog:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfMaintenanceLog gzfMaintenanceLog, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfMaintenanceLog> page = gzfMaintenanceLogService.findPage(
            new Page<GzfMaintenanceLog>(request, response), gzfMaintenanceLog);
        model.addAttribute("page", page);
        model.addAttribute("typeList", gzfFaultCategoryService.findList(new GzfFaultCategory()));
        return "modules/gzf/gzfMaintenanceLogList";
    }

    @RequiresPermissions("gzf:gzfMaintenanceLog:view")
    @RequestMapping(value = "form")
    public String form(GzfMaintenanceLog gzfMaintenanceLog, Model model) {
        model.addAttribute("gzfMaintenanceLog", gzfMaintenanceLog);
        model.addAttribute("typeList", gzfFaultCategoryService.findList(new GzfFaultCategory()));
        return "modules/gzf/gzfMaintenanceLogForm";
    }

    @RequiresPermissions("gzf:gzfMaintenanceLog:edit")
    @RequestMapping(value = "save")
    public String save(GzfMaintenanceLog gzfMaintenanceLog, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfMaintenanceLog)) {
            return form(gzfMaintenanceLog, model);
        }
        gzfMaintenanceLogService.save(gzfMaintenanceLog);
        addMessage(redirectAttributes, "保存维修日志成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfMaintenanceLog/?repage";
    }

    @RequiresPermissions("gzf:gzfMaintenanceLog:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfMaintenanceLog gzfMaintenanceLog, RedirectAttributes redirectAttributes) {
        gzfMaintenanceLogService.delete(gzfMaintenanceLog);
        addMessage(redirectAttributes, "删除维修日志成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfMaintenanceLog/?repage";
    }

}