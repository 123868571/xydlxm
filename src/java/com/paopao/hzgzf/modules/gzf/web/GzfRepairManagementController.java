/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairManagement;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairProject;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairManagementService;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairManagerProjectService;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairProjectService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 报修管理Controller
 * @author Hongjun
 * @version 2016-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfRepairManagement")
public class GzfRepairManagementController extends BaseController {

    @Autowired
    private GzfRepairManagementService     gzfRepairManagementService;
    @Autowired
    private GzfRepairManagerProjectService gzfRepairManagerProjectService;
    @Autowired
    private GzfRepairProjectService        gzfRepairProjectService;
    @Autowired
    private GzfVillageService              gzfVillageService;
    @Autowired
    private GzfHouseInfoService            gzfHouseInfoService;
    @Autowired
    private GzfHouseholdInfoService        gzfHouseholdInfoService;
    @Autowired
    private GzfPalacesService              gzfPalacesService;

    @Autowired
    private GzfHousePersonService          gzfHousePersonService;

    @ModelAttribute
    public GzfRepairManagement get(@RequestParam(required = false) String id) {
        GzfRepairManagement entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfRepairManagementService.get(id);
        }
        if (entity == null) {
            entity = new GzfRepairManagement();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfRepairManagement:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfRepairManagement gzfRepairManagement, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfRepairManagement> page = gzfRepairManagementService.findPage(
            new Page<GzfRepairManagement>(request, response), gzfRepairManagement);
        List<GzfRepairManagement> list = page.getList();
        if (!list.isEmpty()) {
            for (GzfRepairManagement gzfrm : list) {
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
        return "modules/gzf/gzfRepairManagementList";
    }

    @RequiresPermissions("gzf:gzfRepairManagement:view")
    @RequestMapping(value = "form")
    public String form(GzfRepairManagement gzfRepairManagement, Model model) {
        if (StringUtils.isEmpty(gzfRepairManagement.getRepairNum())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            gzfRepairManagement.setRepairNum(sdf.format(new Date()));
        }
        model.addAttribute("gzfRepairManagement", gzfRepairManagement);
        model.addAttribute("repairTypeList",
            gzfRepairProjectService.findList(new GzfRepairProject()));
        model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
        return "modules/gzf/gzfRepairManagementForm";
    }

    @RequiresPermissions("gzf:gzfRepairManagement:view")
    @RequestMapping(value = "info")
    public String info(GzfRepairManagement gzfRepairManagement, Model model) {
        if (StringUtils.isEmpty(gzfRepairManagement.getRepairNum())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            gzfRepairManagement.setRepairNum(sdf.format(new Date()));
        }
        if (gzfRepairManagement.getGzfHouseInfoId() != null
            && !"".equals(gzfRepairManagement.getGzfHouseInfoId())) {

            GzfHousePerson gzfHousePersonQuery = new GzfHousePerson();
            gzfHousePersonQuery.setGzfHouseInfoId(gzfRepairManagement.getGzfHouseInfoId());
            List<GzfHousePerson> gzfHousePersonResults = gzfHousePersonService
                .findList(gzfHousePersonQuery);
            String hhid = null;
            if (gzfHousePersonResults != null && gzfHousePersonResults.size() > 0) {
                hhid = gzfHousePersonResults.get(0).getGzfHouseholdInfoId();
                gzfRepairManagement.setGzfHouseholdInfo(gzfHousePersonResults.get(0)
                    .getGzfHouseholdInfo());
            }

            GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfRepairManagement.getGzfHouseInfoId());
            if (gzfhi != null) {
                if (gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())) {
                    GzfPalaces gzfP = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                    if (gzfP != null) {
                        gzfhi.setGzfPalaces(gzfP);
                    }
                }

                gzfRepairManagement.setGzfHouseInfo(gzfhi);
            }
        }
        model.addAttribute("gzfRepairManagement", gzfRepairManagement);
        model.addAttribute("repairTypeList",
            gzfRepairProjectService.findList(new GzfRepairProject()));
        model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
        return "modules/gzf/gzfRepairManagementInfo";
    }

    @RequiresPermissions("gzf:gzfRepairManagement:edit")
    @RequestMapping(value = "save")
    public String save(GzfRepairManagement gzfRepairManagement, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfRepairManagement)) {
            return form(gzfRepairManagement, model);
        }
        gzfRepairManagement.setMaintenanceId("1");
        gzfRepairManagementService.save(gzfRepairManagement);
        addMessage(redirectAttributes, "保存报修管理成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfRepairManagement/?repage";
    }

    @RequiresPermissions("gzf:gzfRepairManagement:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfRepairManagement gzfRepairManagement,
                         RedirectAttributes redirectAttributes) {
        gzfRepairManagementService.delete(gzfRepairManagement);
        addMessage(redirectAttributes, "删除报修管理成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfRepairManagement/?repage";
    }

}