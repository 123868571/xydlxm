/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePropertyService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.service.GzfAccountService;
import com.paopao.hzgzf.modules.pay.service.PayCommonService;

/**
 * 换房
 * 
 * @author TU
 * @version $Id: GzfHouseChangeController.java, v 0.1 2016-2-29 下午11:14:14 TU Exp $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseWardsController")
public class GzfHouseWardsController extends BaseController {

    @Autowired
    private GzfHouseInfoService     gzfHouseInfoService;

    @Autowired
    private GzfPalacesService       gzfPalacesService;

    @Autowired
    private GzfHousePropertyService gzfHousePropertyService;

    @Autowired
    private GzfHousePersonService   gzfHousePersonService;

    @Autowired
    private GzfHouseholdInfoService gzfHouseholdInfoService;

    @Autowired
    private GzfAccountService       gzfAccountService;

    @Autowired
    private GzfVillageService       gzfVillageService;
    @Autowired
    private PayCommonService        payCommonService;

    @RequestMapping(value = { "list", "" })
    public String list(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        model.addAttribute("gzfHouseInfo", gzfHouseholdInfo);
        return "modules/gzf/gzfHouseWardsSetup1";
    }

    @RequestMapping(value = "setup2")
    public String setup3(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        GzfHousePerson ghp = gzfHousePersonService.get(gzfHousePerson.getHousePerson1());
        ghp.setHousePerson1(gzfHousePerson.getHousePerson1());

        Map param = new HashMap();
        param.put("endDate", new Date());
        param.put("water", gzfHousePerson.getWater());
        param.put("electric", gzfHousePerson.getElec());
        param.put("gas", gzfHousePerson.getGas());
        ghp.setCheckoutDate(new Date());
        try {
            Map retMap = payCommonService.houseCheckOut(ghp, param);
        } catch (Exception e) {
            logger.error("", e);
        }

        model.addAttribute("gzfHousePerson", ghp);
        model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
        return "modules/gzf/gzfHouseWardsSetup2";
    }

    @RequestMapping(value = "setup3")
    public String setup4(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                         HttpServletResponse response, Model model,
                         RedirectAttributes redirectAttributes) {

        GzfHousePerson ghp = gzfHousePersonService.get(gzfHousePerson.getHousePerson1());
        gzfHousePersonService.delete(ghp);

        GzfHousePerson gzfHousePersonNew = new GzfHousePerson();
        gzfHousePersonNew.setGzfHouseholdInfoId(ghp.getGzfHouseholdInfoId());
        gzfHousePersonNew.setGzfHouseInfoId(gzfHousePerson.getGzfHouseInfoId());
        gzfHousePersonNew.setReview("0");
        gzfHousePersonNew.setBind("1");
        gzfHousePersonNew.setStartDate(gzfHousePerson.getStartDate());
        gzfHousePersonNew.setEndDate(gzfHousePerson.getEndDate());
        gzfHousePersonNew.setEffectiveDate(gzfHousePerson.getEffectiveDate());
        gzfHousePersonNew.setCheckoutStatus(0);

        //住户信息
        try {
            gzfHousePersonService.save(gzfHousePersonNew);
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            addMessage(redirectAttributes, "房屋已经与人员绑定过");
            return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/list";
        }

        GzfAccount ac = gzfAccountService.get(ghp.getAccountId());//更新帐户信息
        if (ac == null) {
            throw new RuntimeException("帐户未查到");
        }
        ac.setCustId(gzfHousePersonNew.getId());
        gzfAccountService.save(ac);

        //        gzfHousePersonNew = gzfHousePersonService.get(gzfHousePersonNew.getId());
        //        GzfAccount acc = gzfAccountService.generateAccount(gzfHousePersonNew);//生成帐户信息
        //        gzfHousePersonNew.setAccountId(acc.getId());
        //        gzfHousePersonService.save(gzfHousePersonNew);

        model.addAttribute("gzfHousePerson", gzfHousePersonNew);
        return "modules/gzf/gzfHouseWardsSetup3";
    }

    @RequestMapping(value = "setup2Form")
    @ResponseBody
    public GzfHouseholdInfo setup2Form(GzfHouseholdInfo gzfHouseholdInfo,
                                       HttpServletRequest request, HttpServletResponse response,
                                       Model model) {
        List<GzfHouseholdInfo> gzfhhi = gzfHouseholdInfoService.getByNameAndPhone(gzfHouseholdInfo);
        if (gzfhhi != null && !gzfhhi.isEmpty()) {
            GzfHousePerson gzfhp1 = new GzfHousePerson();
            gzfhp1.setGzfHouseholdInfoId(gzfhhi.get(0).getId());
            gzfhp1.setBind("1");
            GzfHousePerson gzfhp = gzfHousePersonService.query(gzfhp1);
            gzfhp.getGzfHouseInfo().setVillageName(
                gzfhp.getGzfHouseInfo().getGzfPalaces().getGzfVillage().getName());
            gzfhp.getGzfHouseInfo().setPalacesName(
                gzfhp.getGzfHouseInfo().getGzfPalaces().getName());
            gzfHouseholdInfo.setGzfHousePerson(gzfhp);
        }
        return gzfHouseholdInfo;
    }

}