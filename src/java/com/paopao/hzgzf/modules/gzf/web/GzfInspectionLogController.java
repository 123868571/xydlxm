/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.paopao.hzgzf.modules.gzf.entity.GzfInspectionLog;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfInspectionLogService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 巡查日志Controller
 * @author Hongjun
 * @version 2016-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfInspectionLog")
public class GzfInspectionLogController extends BaseController {

    @Autowired
    private GzfInspectionLogService gzfInspectionLogService;
    @Autowired
    private GzfVillageService       gzfVillageService;

    @ModelAttribute
    public GzfInspectionLog get(@RequestParam(required = false) String id) {
        GzfInspectionLog entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfInspectionLogService.get(id);
        }
        if (entity == null) {
            entity = new GzfInspectionLog();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfInspectionLog:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfInspectionLog gzfInspectionLog, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfInspectionLog> page = gzfInspectionLogService.findPage(new Page<GzfInspectionLog>(
            request, response), gzfInspectionLog);
        model.addAttribute("page", page);
        return "modules/gzf/gzfInspectionLogList";
    }

    @RequiresPermissions("gzf:gzfInspectionLog:view")
    @RequestMapping(value = "form")
    public String form(GzfInspectionLog gzfInspectionLog, Model model) {
        if (StringUtils.isEmpty(gzfInspectionLog.getInspectionId())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            gzfInspectionLog.setInspectionId("XJ" + sdf.format(new Date()));
        }
        model.addAttribute("gzfInspectionLog", gzfInspectionLog);
        model.addAttribute("gzfVillageList", gzfVillageService.findList(new GzfVillage()));
        return "modules/gzf/gzfInspectionLogForm";
    }

    @RequiresPermissions("gzf:gzfInspectionLog:edit")
    @RequestMapping(value = "save")
    public String save(GzfInspectionLog gzfInspectionLog, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfInspectionLog)) {
            return form(gzfInspectionLog, model);
        }
        gzfInspectionLogService.save(gzfInspectionLog);
        addMessage(redirectAttributes, "保存巡查日志成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspectionLog/?repage";
    }

    @RequiresPermissions("gzf:gzfInspectionLog:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfInspectionLog gzfInspectionLog, RedirectAttributes redirectAttributes) {
        gzfInspectionLogService.delete(gzfInspectionLog);
        addMessage(redirectAttributes, "删除巡查日志成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfInspectionLog/?repage";
    }

}