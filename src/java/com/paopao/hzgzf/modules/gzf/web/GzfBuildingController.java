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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfBuilding;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfBuildingService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

/**
 * 楼栋Controller
 * @author Hongjun
 * @version 2015-12-20
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfBuilding")
public class GzfBuildingController extends BaseController {

    @Autowired
    private GzfBuildingService gzfBuildingService;

    @Autowired
    private GzfPalacesService  gzfPalacesService;

    @Autowired
    private GzfVillageService  gzfVillageService;

    @ModelAttribute
    public GzfBuilding get(@RequestParam(required = false) String id) {
        GzfBuilding entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfBuildingService.get(id);
        }
        if (entity == null) {
            entity = new GzfBuilding();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfBuilding:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfBuilding gzfBuilding, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfBuilding> page = gzfBuildingService.findPage(new Page<GzfBuilding>(request,
            response), gzfBuilding);
        model.addAttribute("page", page);
        model.addAttribute("gzfVillageId", gzfBuilding.getGzfVillageId());
        return "modules/gzf/gzfBuildingList";
    }

    @RequiresPermissions("gzf:gzfBuilding:view")
    @RequestMapping(value = "form")
    public String form(GzfBuilding gzfBuilding, Model model) {
        model.addAttribute("gzfBuilding", gzfBuilding);
        GzfVillage village = new GzfVillage();
        village.setId(gzfBuilding.getGzfVillageId());
        GzfPalaces gzfPalaces = new GzfPalaces();
        gzfPalaces.setGzfVillageId(gzfBuilding.getGzfVillageId());
        model.addAttribute("gzfPalacesList", gzfPalacesService.findList(gzfPalaces));
        model.addAttribute("gzfVillageList", gzfVillageService.findList(village));

        toList(gzfBuilding.getMinBuildNum(), model, "minBuildList");
        toList(gzfBuilding.getMinFloorNum(), model, "minFloorList");
        toList(gzfBuilding.getMinRoomNum(), model, "minRoomList");
        toList(gzfBuilding.getMinUnitNum(), model, "minUnitList");

        toList(gzfBuilding.getMaxBuildNum(), model, "maxBuildList");
        toList(gzfBuilding.getMaxFloorNum(), model, "maxFloorList");
        toList(gzfBuilding.getMaxRoomNum(), model, "maxRoomList");
        toList(gzfBuilding.getMaxUnitNum(), model, "maxUnitList");
        return "modules/gzf/gzfBuildingForm";
    }

    private void toList(String minBuildNum, Model model, String modelName) {
        List<NameID> minBList = new ArrayList<NameID>();
        if (!StringUtils.isEmpty(minBuildNum))
            for (int i = 0; i < Integer.valueOf(minBuildNum); i++) {
                NameID n = new NameID();
                n.setId(i + 1);
                n.setName(i + 1);
                minBList.add(n);
            }
        model.addAttribute(modelName, minBList);
    }

    class NameID {
        private int name;
        private int id;

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @RequiresPermissions("gzf:gzfBuilding:edit")
    @RequestMapping(value = "save")
    public String save(GzfBuilding gzfBuilding, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfBuilding)) {
            return form(gzfBuilding, model);
        }
        gzfBuildingService.save(gzfBuilding);
        addMessage(redirectAttributes, "保存楼栋成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfBuilding/?repage&gzfVillageId="
               + gzfBuilding.getGzfVillageId();
    }

    @RequiresPermissions("gzf:gzfBuilding:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfBuilding gzfBuilding, RedirectAttributes redirectAttributes) {
        gzfBuildingService.delete(gzfBuilding);
        addMessage(redirectAttributes, "删除楼栋成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfBuilding/?repage&gzfVillageId="
               + gzfBuilding.getGzfVillageId();
    }

    @RequiresPermissions("gzf:gzfBuilding:edit")
    @RequestMapping(value = "moredelete")
    @ResponseBody
    public String moredelete(String[] valArr) {
        if (valArr.length <= 0) {
            return "false";
        } else {
            for (String str : valArr) {
                GzfBuilding gzfB = new GzfBuilding();
                gzfB.setId(str);
                try {
                    gzfBuildingService.delete(gzfB);
                } catch (Exception e) {
                    return "error";
                }
            }
        }

        //gzfVillageService.delete(gzfVillage);
        return "success";
    }

}