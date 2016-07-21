package com.paopao.hzgzf.modules.gzf.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfBuilding;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfBuildingService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;

public class GzfHouseWardsControllerController extends BaseController {

    @Autowired
    private GzfBuildingService      gzfBuildingService;

    @Autowired
    private GzfPalacesService       gzfPalacesService;

    @Autowired
    private GzfVillageService       gzfVillageService;

    @Autowired
    private GzfHouseholdInfoService gzfHouseholdInfoService;

    @Autowired
    private GzfHousePersonService   gzfHousePersonService;

    @Autowired
    private GzfHouseInfoService     gzfHouseInfoService;

    @Autowired
    private GzfPalacesService       gzfPalacesServcie;

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

    @RequiresPermissions("gzf:gzfHouseWardsController:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        model.addAttribute("gzfHouseholdInfo", gzfHouseholdInfo);
        return "modules/gzf/gzfHouseWardsControllerForm";
    }

    @RequiresPermissions("gzf:gzfHouseWardsController:view")
    @RequestMapping(value = "form")
    public String form(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        GzfHouseholdInfo gzfhhi = new GzfHouseholdInfo();
        if (gzfHouseholdInfo != null) {
            gzfhhi = gzfHouseholdInfoService.getByEntity(gzfHouseholdInfo);
            if (gzfhhi != null) {
                GzfHousePerson gzfHousePerson = new GzfHousePerson();
                gzfHousePerson.setBind("1");
                gzfHousePerson.setReview("1");
                gzfHousePerson.setGzfHouseholdInfoId(gzfhhi.getId());
                List<GzfHousePerson> gzfhp = gzfHousePersonService.findList(gzfHousePerson);
                if (gzfhp.size() == 1) {
                    GzfHousePerson gzfHp = gzfhp.get(0);
                    if (gzfHp.getGzfHouseInfoId() != null && !"".equals(gzfHp.getGzfHouseInfoId())) {
                        GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfHp.getGzfHouseInfoId());
                        if (gzfhi != null) {
                            if (gzfhi.getGzfPalacesId() != null
                                && !"".equals(gzfhi.getGzfPalacesId())) {
                                GzfPalaces gzfp = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                                if (gzfp != null) {
                                    gzfhi.setGzfPalaces(gzfp);
                                }
                            }
                            gzfHp.setGzfHouseInfo(gzfhi);
                        }
                    }
                    gzfhhi.setGzfHousePerson(gzfHp);
                }

            }
        }
        model.addAttribute("gzfHouseholdInfo", gzfhhi);
        return "modules/gzf/gzfHouseWardsControllerForm";
    }

    @RequiresPermissions("gzf:gzfHouseWardsController:view")
    @RequestMapping(value = "form1")
    public String form1(GzfHousePerson gzfHousePerson, Model model) {
        model.addAttribute("gzfHousePerson", gzfHousePerson);
        model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
        return "modules/gzf/gzfHouseWardsForm1";
    }

    @RequiresPermissions("gzf:gzfHouseWardsController:edit")
    @RequestMapping(value = "save")
    public String save(GzfHousePerson gzfHousePerson, Model model,
                       RedirectAttributes redirectAttributes) {

        gzfHousePerson.setCheckoutStatus(0);
        if (gzfHousePerson != null) {
            if (gzfHousePerson.getIsNewRecord()) {
                gzfHousePerson.setReview("0");
                gzfHousePerson.setBind("1");
            } else {
                gzfHousePerson.setBind("3");
                gzfHousePersonService.save(gzfHousePerson);
                GzfHousePerson gzfHousePerson1 = new GzfHousePerson();
                gzfHousePerson1.setReview("0");
                gzfHousePerson1.setBind("1");
                gzfHousePerson1.setStartDate(gzfHousePerson.getStartDate());
                gzfHousePerson1.setEndDate(gzfHousePerson.getEndDate());
                gzfHousePerson1.setEffectiveDate(gzfHousePerson.getEffectiveDate());
                gzfHousePersonService.save(gzfHousePerson);
                addMessage(redirectAttributes, "换房成功");
                return "modules/gzf/gzfHouseWardsForm2";
            }

        }

        gzfHousePersonService.save(gzfHousePerson);
        addMessage(redirectAttributes, "换房成功");
        return "modules/gzf/gzfHouseWardsForm2";
    }
}