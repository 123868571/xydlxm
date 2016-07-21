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

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;

/**
 * 
 * @author yang
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseholdAgreement")
public class gzfHouseholdAgreementController extends BaseController {
    @Autowired
    private GzfHousePersonService     gzfHousePersonService;

    @Autowired
    private GzfHouseholdInfoService   gzfHouseholdInfoService;

    @Autowired
    private GzfHouseInfoService       gzfHouseInfoService;

    @Autowired
    private GzfPalacesService         gzfPalacesService;

    @Autowired
    private GzfPaymentStandardService gzfPaymentStandardService;

    @ModelAttribute
    public GzfHousePerson get(@RequestParam(required = false) String id) {
        GzfHousePerson entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfHousePersonService.get(id);
        }
        if (entity == null) {
            entity = new GzfHousePerson();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfHouseholdAgreement:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        gzfHousePerson.setBind("1");
        Page<GzfHousePerson> page = gzfHousePersonService.findPage(new Page<GzfHousePerson>(
            request, response), gzfHousePerson);
        List<GzfHousePerson> list = page.getList();
        if (!list.isEmpty()) {
            for (GzfHousePerson gzfhp : list) {

                if (gzfhp != null) {
                    if (gzfhp.getGzfHouseholdInfoId() != null && !"".equals(gzfhp)) {
                        GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfhp
                            .getGzfHouseholdInfoId());
                        if (gzfhhi != null) {
                            gzfhp.setGzfHouseholdInfo(gzfhhi);
                        }
                    }

                    if (gzfhp.getGzfHouseInfoId() != null && !"".equals(gzfhp.getGzfHouseInfoId())) {
                        GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfhp.getGzfHouseInfoId());
                        if (gzfhi != null) {
                            if (gzfhi.getGzfPalacesId() != null
                                && !"".equals(gzfhi.getGzfPalacesId())) {
                                GzfPalaces gzfp = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                                if (gzfp != null) {
                                    gzfhi.setGzfPalaces(gzfp);
                                }
                            }
                            gzfhp.setGzfHouseInfo(gzfhi);
                        }
                    }
                }

            }
        }
        page.setList(list);
        model.addAttribute("page", page);
        return "modules/gzf/gzfHouseholdAgreementList";
    }

    @RequiresPermissions("gzf:gzfHouseholdAgreement:edit")
    @RequestMapping(value = "form")
    public String form(GzfHousePerson gzfHousePerson, Model model) {
        if (gzfHousePerson != null) {
            GzfHousePerson gzfhp = gzfHousePersonService.get(gzfHousePerson.getId());
            if (gzfhp != null) {
                model.addAttribute(
                    "gzfHousePerson",
                    housePerson(gzfhp.getId(), gzfhp.getGzfHouseholdInfoId(),
                        gzfhp.getGzfHouseInfoId()));
            }
        } else {
            model.addAttribute("gzfHousePerson", gzfHousePerson);
        }
        return "modules/gzf/gzfHouseholdAgreementForm";
    }

    public GzfHousePerson housePerson(String personId, String id, String houseId) {
        GzfHousePerson gzfhp = gzfHousePersonService.get(personId);
        GzfHouseInfo gzfhi = new GzfHouseInfo();
        if (houseId != null && !"".equals(houseId)) {
            gzfhi = gzfHouseInfoService.get(houseId);
            if (gzfhi != null) {
                gzfhp.setGzfHouseInfo(gzfhi);
            }
        }
        if (id != null && !"".equals(id)) {
            GzfHouseholdInfo gzfHouseholdInfo = gzfHouseholdInfoService.get(id);
            if (gzfHouseholdInfo != null) {
                if (gzfHouseholdInfo.getGzfPaymentStandardId() != null
                    && !"".equals(gzfHouseholdInfo.getGzfPaymentStandardId())) {
                    GzfPaymentStandard gzfps = gzfPaymentStandardService.get(gzfHouseholdInfo
                        .getGzfPaymentStandardId());
                    if (gzfps != null) {
                        if (gzfps.getRentUnitPrice() != null
                            && !"".equals(gzfps.getRentUnitPrice())) {
                            if (gzfhi != null) {
                                if (gzfhi.getUseArea() != null) {
                                    gzfps.setRentUnitPrice(gzfps.getRentUnitPrice()
                                                           * gzfhi.getUseArea());
                                }
                            }
                        }
                        gzfHouseholdInfo.setGzfPaymentStandard(gzfps);
                    }
                }
                gzfhp.setGzfHouseholdInfo(gzfHouseholdInfo);
            }
        }
        return gzfhp;
    }
}
