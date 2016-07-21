package com.paopao.hzgzf.modules.gzf.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.paopao.hzgzf.modules.pay.service.PayCommonService;

/**
 * 退房申请
 * 
 * @author TU
 * @version $Id: GzfHouseCheckOutApplicationController.java, v 0.1 2016-4-17 下午10:16:23 TU Exp $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseCheckOutApplication")
public class GzfHouseCheckOutApplicationController extends BaseController {

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
    private PayCommonService          payCommonService;

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

    @RequiresPermissions("gzf:gzfHouseCheckOutApplication:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        gzfHousePerson.setBind("1");
        gzfHousePerson.setCheckoutStatus(0);
        Page<GzfHousePerson> page = gzfHousePersonService.findPage(new Page<GzfHousePerson>(
            request, response), gzfHousePerson);
        model.addAttribute("page", page);
        return "modules/gzf/gzfHouseCheckOutApplicationList";
    }

    @RequiresPermissions("gzf:gzfHouseCheckOutApplication:view")
    @RequestMapping(value = "form")
    public String form(GzfHousePerson gzfhp, Model model) {
        if (gzfhp != null) {
            if (gzfhp.getGzfHouseholdInfoId() != null) {
                GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService
                    .get(gzfhp.getGzfHouseholdInfoId());
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
            Map param1 = new HashMap();
            param1.put("endDate", new Date());
            Map<String, Double> ret = null;
            double houseRentFee = 0;
            double manageFee = 0;
            double consumFee = 0, freeFee = 0;
            try {
                ret = payCommonService.getFeceFee(gzfhp, param1);
                houseRentFee = Double.valueOf(ret.get("houseRentFee")) / 100;
                manageFee = Double.valueOf(ret.get("manageFee")) / 100;
                consumFee = Double.valueOf(ret.get("consumFee")) / 100;//能耗费
                freeFee = Double.valueOf(ret.get("freeFee")) / 100;
                model.addAttribute("houseRentFee", houseRentFee < 0 ? "应退" + Math.abs(houseRentFee)
                    : "应缴" + houseRentFee);
                model.addAttribute("manageFee", manageFee < 0 ? "应退" + Math.abs(houseRentFee)
                    : "应缴" + manageFee);
                model.addAttribute("consumFee", consumFee < 0 ? "应退" + Math.abs(consumFee)
                    : "应缴" + consumFee);
                model.addAttribute("freeFee", freeFee < 0 ? "应退" + Math.abs(freeFee) : "应缴"
                                                                                       + freeFee);
            } catch (Exception e) {
                logger.error("", e);
            }
            //            if (houseRentFee != 0) {
            //                model.addAttribute("message", "房租费未结清,退房进行失败");
            //            }
            //            if (manageFee != 0) {
            //                model.addAttribute("message", "物业应缴/退的费用未结清,退房进行失败");
            //            }

        }
        model.addAttribute("gzfHousePerson", gzfhp);

        model.addAttribute("sysdate", new Date());

        return "modules/gzf/gzfHouseCheckOutApplicationForm";
    }

    @RequiresPermissions("gzf:gzfHouseCheckOutApplication:view")
    @RequestMapping(value = "checkout")
    public String checkout(GzfHousePerson gzfHousePerson, Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            Map param = new HashMap();
            param.put("endDate", new Date());
            param.put("water", gzfHousePerson.getWater());
            param.put("electric", gzfHousePerson.getElec());
            param.put("gas", gzfHousePerson.getGas());
            param.put("dissipationFee", gzfHousePerson.getDestroy());
            gzfHousePerson.setCheckoutDate(new Date());
            gzfHousePerson.setCheckoutStatus(1);
            gzfHousePersonService.save(gzfHousePerson);
            Map retMap = payCommonService.houseCheckOut(gzfHousePerson, param);
            //                Map retMap = new HashMap();
            //                retMap.put("houseRent", 1);
            //                retMap.put("management", 2);
            //                retMap.put("waterFee", 3);
            //                retMap.put("electricityFee", 4);
            //                retMap.put("naturalgasFee", 5);
            //                retMap.put("deposite", 6);
            //                model.addAttribute("retMap", retMap);
            //房租、物业、水、电、天燃气、押金
            //            model.addAttribute(
            //                "message",
            //                "房租费:" + retMap.get("houseRent") + "物业费:" + retMap.get("management") + "押金:"
            //                        + retMap.get("deposite") + "水费:" + retMap.get("waterFee") + "电费:"
            //                        + retMap.get("electricityFee") + "天燃气费:" + retMap.get("naturalgasFee"));
            model.addAttribute("houseRent", Double.valueOf((Double) retMap.get("houseRent")) / 100);
            model.addAttribute("management",
                Double.valueOf((Double) retMap.get("management")) / 100);
            model.addAttribute("waterFee", Double.valueOf((Double) retMap.get("waterFee")) / 100);
            model.addAttribute("electricityFee",
                Double.valueOf((Double) retMap.get("electricityFee")) / 100);
            model.addAttribute("naturalgasFee",
                Double.valueOf((Double) retMap.get("naturalgasFee")) / 100);
            model.addAttribute("deposite", Double.valueOf((Double) retMap.get("deposite")) / 100);
        } catch (Exception e) {
            logger.error("", e);
        }
        model.addAttribute("gzfHousePerson", gzfHousePerson);
        return "modules/gzf/gzfHouseCheckOutApplicationResult";
    }
}
