package com.paopao.hzgzf.modules.gzf.web;

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
import com.paopao.hzgzf.modules.gzf.entity.GzfElectric;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfNaturalGas;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;
import com.paopao.hzgzf.modules.gzf.entity.GzfWaterMeter;
import com.paopao.hzgzf.modules.gzf.service.GzfElectricService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfNaturalGasService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.gzf.service.GzfWaterMeterService;

@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseCheck")
public class GzfHouseCheckController extends BaseController {

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
    @Autowired
    private GzfWaterMeterService      gzfWaterMeterService;
    @Autowired
    private GzfNaturalGasService      gzfNaturalGasService;
    @Autowired
    private GzfElectricService        gzfElectricService;

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

    @RequiresPermissions("gzf:gzfHouseCheck:view")
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
        return "modules/gzf/gzfHouseCheckList";
    }

    @RequiresPermissions("gzf:gzfHouseCheck:view")
    @RequestMapping(value = "form")
    public String form(GzfHousePerson gzfHousePerson, Model model) {
        if (gzfHousePerson.getId() != null) {
            GzfHousePerson gzfhp = gzfHousePersonService.get(gzfHousePerson.getId());
            if (gzfhp != null) {
                if (gzfhp.getGzfHouseholdInfoId() != null) {
                    GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfhp
                        .getGzfHouseholdInfoId());
                    if (gzfhhi != null) {
                        if (gzfhhi.getGzfPaymentStandardId() != null) {
                            GzfPaymentStandard gzfps = gzfPaymentStandardService.get(gzfhhi
                                .getGzfPaymentStandardId());
                            if (gzfps != null) {
                                gzfhhi.setGzfPaymentStandard(gzfps);
                            }
                        }
                        gzfhp.setGzfHouseholdInfo(gzfhhi);
                    }
                }

                if (gzfhp.getGzfHouseInfoId() != null) {
                    GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfhp.getGzfHouseInfoId());
                    if (gzfhi != null) {
                        if (gzfhi.getGzfPalacesId() != null) {
                            GzfPalaces gzfp = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                            if (gzfp != null) {
                                gzfhi.setGzfPalaces(gzfp);
                            }
                        }
                        gzfhp.setGzfHouseInfo(gzfhi);
                    }
                }

            }

            model.addAttribute("gzfHousePerson", gzfhp);
        } else {
            model.addAttribute("gzfHousePerson", new GzfHousePerson());
        }
        model.addAttribute("sysdate", new Date());
        return "modules/gzf/gzfHouseCheckForm";
    }

    @RequiresPermissions("gzf:gzfHouseCheck:view")
    @RequestMapping(value = "checkin")
    public String checkin(GzfHousePerson gzfHousePerson, Model model,
                          RedirectAttributes redirectAttributes) {
        if (gzfHousePerson.getId() != null) {
            GzfHousePerson gzfhp = gzfHousePersonService.get(gzfHousePerson.getId());
            GzfWaterMeter gzfWaterMeter = new GzfWaterMeter();
            gzfWaterMeter.setGzfHouseholdInfoId(gzfhp.getGzfHouseholdInfoId());
            gzfWaterMeter.setGzfHouseInfoId(gzfhp.getGzfHouseInfoId());
            gzfWaterMeter.setNum(Double.valueOf(gzfHousePerson.getWater()));
            gzfWaterMeter.setTime(new Date());
            gzfWaterMeter.setUpdateDate(new Date());
            gzfWaterMeterService.save(gzfWaterMeter);

            GzfElectric gzfElectric = new GzfElectric();
            gzfElectric.setGzfHouseholdInfoId(gzfhp.getGzfHouseholdInfoId());
            gzfElectric.setGzfHouseInfoId(gzfhp.getGzfHouseInfoId());
            gzfElectric.setNum(Double.valueOf(gzfHousePerson.getElec()));
            gzfElectric.setTime(new Date());
            gzfElectric.setUpdateDate(new Date());
            gzfElectricService.save(gzfElectric);

            GzfNaturalGas gzfNaturalGas = new GzfNaturalGas();
            gzfNaturalGas.setGzfHouseholdInfoId(gzfhp.getGzfHouseholdInfoId());
            gzfNaturalGas.setGzfHouseInfoId(gzfhp.getGzfHouseInfoId());
            gzfNaturalGas.setNum(Double.valueOf(gzfHousePerson.getGas()));
            gzfNaturalGas.setTime(new Date());
            gzfNaturalGas.setUpdateDate(new Date());
            gzfNaturalGasService.save(gzfNaturalGas);

            model.addAttribute("gzfHousePerson", gzfhp);
        } else {
            model.addAttribute("gzfHousePerson", new GzfHousePerson());
        }
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseCheck/?repage";
    }
}
