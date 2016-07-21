package com.paopao.hzgzf.modules.gzf.web;

import java.util.List;
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

import com.paopao.hzgzf.common.config.Global;
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
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.service.GzfAccountService;
import com.paopao.hzgzf.modules.pay.service.PayCommonService;

/**
 * 退房管理
 * 
 * @author TU
 * @version $Id: GzfHouseCheckOutController.java, v 0.1 2016-4-17 下午10:16:08 TU Exp $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseCheckOut")
public class GzfHouseCheckOutController extends BaseController {

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
    @Autowired
    private GzfAccountService         gzfAccountService;

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

    @RequiresPermissions("gzf:gzfHouseCheckOut:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        gzfHousePerson.setBind("1");
        gzfHousePerson.setCheckoutStatus(1);
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
        return "modules/gzf/gzfHouseCheckOutList";
    }

    @RequiresPermissions("gzf:gzfHouseCheckOut:edit")
    @RequestMapping(value = "form")
    public String form(GzfHousePerson gzfHousePerson, Model model) {
        model.addAttribute(
            "gzfHousePerson",
            housePerson(gzfHousePerson.getId(), gzfHousePerson.getGzfHouseholdInfoId(),
                gzfHousePerson.getGzfHouseInfoId()));

        Map retMap;
        try {
            retMap = payCommonService.getAcctItemMoney(gzfHousePerson);
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
            //            model.addAttribute("houseRent", retMap.get("houseRent"));
            //            model.addAttribute("management", retMap.get("management"));
            //            model.addAttribute("waterFee", retMap.get("waterFee"));
            //            model.addAttribute("electricityFee", retMap.get("electricityFee"));
            //            model.addAttribute("naturalgasFee", retMap.get("naturalgasFee"));
            //            model.addAttribute("deposite", retMap.get("deposite"));

            model.addAttribute("houseRent", Double.valueOf((Double) retMap.get("houseRent")) / 100);
            model.addAttribute("management",
                Double.valueOf((Double) retMap.get("management")) / 100);
            model.addAttribute("waterFee", Double.valueOf((Double) retMap.get("waterFee")) / 100);
            model.addAttribute("electricityFee",
                Double.valueOf((Double) retMap.get("electricityFee")) / 100);
            model.addAttribute("naturalgasFee",
                Double.valueOf((Double) retMap.get("naturalgasFee")) / 100);
            model.addAttribute("deposite", Double.valueOf((Double) retMap.get("deposite")) / 100);
            boolean checkflag = true;
            if ((Double) retMap.get("houseRent") != 0 || (Double) retMap.get("management") != 0
                || (Double) retMap.get("waterFee") != 0
                || (Double) retMap.get("electricityFee") != 0
                || (Double) retMap.get("naturalgasFee") != 0
                || (Double) retMap.get("deposite") != 0) {
                checkflag = false;
            }
            model.addAttribute("checkout", checkflag);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "modules/gzf/gzfHouseCheckOutForm";
    }

    @RequestMapping(value = "detail")
    public String detail(GzfHousePerson gzfHousePerson, Model model) {
        model.addAttribute("gzfHousePerson",
            gzfHousePersonService.getByHistory(gzfHousePerson.getId()));

        return "modules/gzf/gzfHouseCheckOutDetail";
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
                                if (gzfhi.getInnerArea() != null) {
                                    gzfps.setRentUnitPrice(gzfps.getRentUnitPrice()
                                                           * gzfhi.getInnerArea());
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

    @RequestMapping(value = "checkout")
    public String checkout(GzfHousePerson gzfHousePerson, Model model,
                           RedirectAttributes redirectAttributes) {

        gzfHousePersonService.save(gzfHousePerson);//保存单据到历史
        
        GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfHousePerson
                .getGzfHouseInfoId());
            if (gzfhi != null) 
            {
                gzfhi.setHouseStat(1);// 空闲
                gzfHouseInfoService.save(gzfhi, false);
            }

        gzfHousePersonService.delete(gzfHousePerson);
        GzfAccount entity = gzfAccountService.get(gzfHousePerson.getAccountId());
        gzfAccountService.delete(entity);
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseCheckOut/?repage";
    }
}
