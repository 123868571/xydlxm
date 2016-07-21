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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfMaintenance;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairProject;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfMaintenanceService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairManagementService;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairManagerProjectService;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairProjectService;

/**
 * 维修修管理Controller
 * @author Hongjun
 * @version 2016-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfMaintenance")
public class GzfMaintenanceController extends BaseController {

    @Autowired
    private GzfMaintenanceService          gzfMaintenanceService;

    @Autowired
    private GzfRepairManagementService     gzfRepairManagementService;

    @Autowired
    private GzfRepairManagerProjectService gzfRepairManagerProjectService;

    @Autowired
    private GzfRepairProjectService        gzfRepairProjectService;

    @Autowired
    private GzfHouseInfoService            gzfHouseInfoService;

    @Autowired
    private GzfPalacesService              gzfPalacesService;

    @ModelAttribute
    public GzfMaintenance get(@RequestParam(required = false) String id) {
        GzfMaintenance entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfMaintenanceService.get(id);
        }
        if (entity == null) {
            entity = new GzfMaintenance();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfMaintenance:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfMaintenance gzfMaintenance, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfMaintenance> page = gzfMaintenanceService.findPage(new Page<GzfMaintenance>(
            request, response), gzfMaintenance);

        List<GzfMaintenance> list = page.getList();
        if (!list.isEmpty()) {
            for (GzfMaintenance gzfrm : list) {
                if (gzfrm.getGzfHouseInfoId() != null && !"".equals(gzfrm.getGzfHouseInfoId())) {
                    GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfrm.getGzfHouseInfoId());
                    if (gzfhi != null) {
                        if (gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())) {
                            GzfPalaces gzfP = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                            if (gzfP != null) {
                                gzfhi.setGzfPalaces(gzfP);
                            }
                        }

                        gzfrm.setGzfHouseInfo(gzfhi);
                    }
                }
            }
        }
        page.setList(list);
        model.addAttribute("page", page);
        model.addAttribute("repairTypeList",
            gzfRepairProjectService.findList(new GzfRepairProject()));
        return "modules/gzf/gzfMaintenanceList";
    }

    @RequiresPermissions("gzf:gzfMaintenance:view")
    @RequestMapping(value = "form")
    public String form(GzfMaintenance gzfMaintenance, Model model) {
        if (gzfMaintenance.getGzfHouseInfoId() != null
            && !"".equals(gzfMaintenance.getGzfHouseInfoId())) {
            GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfMaintenance.getGzfHouseInfoId());
            if (gzfhi != null) {
                if (gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())) {
                    GzfPalaces gzfP = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                    if (gzfP != null) {
                        gzfhi.setGzfPalaces(gzfP);
                    }
                }

                gzfMaintenance.setGzfHouseInfo(gzfhi);
            }
        }
        model.addAttribute("gzfMaintenance", gzfMaintenance);
        model.addAttribute("repairTypeList",
            gzfRepairProjectService.findList(new GzfRepairProject()));
        return "modules/gzf/gzfMaintenanceForm";
    }

    @RequiresPermissions("gzf:gzfMaintenance:view")
    @RequestMapping(value = "info")
    public String info(GzfMaintenance gzfMaintenance, Model model) {
        if (gzfMaintenance.getGzfHouseInfoId() != null
            && !"".equals(gzfMaintenance.getGzfHouseInfoId())) {
            GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfMaintenance.getGzfHouseInfoId());
            if (gzfhi != null) {
                if (gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())) {
                    GzfPalaces gzfP = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                    if (gzfP != null) {
                        gzfhi.setGzfPalaces(gzfP);
                    }
                }

                gzfMaintenance.setGzfHouseInfo(gzfhi);
            }
        }
        model.addAttribute("gzfMaintenance", gzfMaintenance);
        return "modules/gzf/gzfMaintenanceInfo";
    }

    @RequiresPermissions("gzf:gzfMaintenance:view")
    @RequestMapping(value = "confirm")
    public String confirm(GzfMaintenance gzfMaintenance, Model model) {
        if (gzfMaintenance.getGzfHouseInfoId() != null
            && !"".equals(gzfMaintenance.getGzfHouseInfoId())) {
            GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfMaintenance.getGzfHouseInfoId());
            if (gzfhi != null) {
                if (gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())) {
                    GzfPalaces gzfP = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                    if (gzfP != null) {
                        gzfhi.setGzfPalaces(gzfP);
                    }
                }

                gzfMaintenance.setGzfHouseInfo(gzfhi);
            }
        }
        gzfMaintenance.setProcessStatus(2);
        model.addAttribute("gzfMaintenance", gzfMaintenance);
        return "modules/gzf/gzfMaintenanceConfirm";
    }

    @RequiresPermissions("gzf:gzfMaintenance:view")
    @RequestMapping(value = "process")
    public String process(GzfMaintenance gzfMaintenance, Model model) {
        if (gzfMaintenance.getGzfHouseInfoId() != null
            && !"".equals(gzfMaintenance.getGzfHouseInfoId())) {
            GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfMaintenance.getGzfHouseInfoId());
            if (gzfhi != null) {
                if (gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())) {
                    GzfPalaces gzfP = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                    if (gzfP != null) {
                        gzfhi.setGzfPalaces(gzfP);
                    }
                }

                gzfMaintenance.setGzfHouseInfo(gzfhi);
            }
        }
        gzfMaintenance.setProcessStatus(1);
        model.addAttribute("gzfMaintenance", gzfMaintenance);
        return "modules/gzf/gzfMaintenanceProcess";
    }

    @RequiresPermissions("gzf:gzfMaintenance:edit")
    @RequestMapping(value = "save")
    public String save(GzfMaintenance gzfMaintenance, Model model, HttpServletRequest request,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfMaintenance)) {
            return form(gzfMaintenance, model);
        }
        gzfMaintenanceService.save(gzfMaintenance);
        addMessage(redirectAttributes, "保存维修管理成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfMaintenance/?repage";
    }

    @RequiresPermissions("gzf:gzfMaintenance:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfMaintenance gzfMaintenance, RedirectAttributes redirectAttributes) {
        gzfMaintenanceService.delete(gzfMaintenance);
        addMessage(redirectAttributes, "删除维修管理成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfMaintenance/?repage";
    }

}