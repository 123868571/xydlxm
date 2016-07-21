/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.List;

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
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePropertyService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.service.GzfAccountService;

/**
 * 调房
 * 
 * @author TU
 * @version $Id: GzfHouseChangeController.java, v 0.1 2016-2-29 下午11:14:14 TU
 *          Exp $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseChange")
public class GzfHouseChangeController extends BaseController {

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

    @RequestMapping(value = { "list", "" })
    public String list(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        model.addAttribute("gzfHouseInfo", gzfHouseholdInfo);
        return "modules/gzf/gzfHouseChangeSetup1";
    }

    @RequestMapping(value = "setup2")
    public String setup2(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        model.addAttribute("gzfHouseInfo", gzfHouseholdInfo);
        return "modules/gzf/gzfHouseChangeSetup2";
    }

    @RequestMapping(value = "setup3")
    public String setup3(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        GzfHousePerson ghp = gzfHousePersonService.get(gzfHousePerson.getHousePerson1());
        ghp.setHousePerson1(gzfHousePerson.getHousePerson1());
        ghp.setHousePerson2(gzfHousePerson.getHousePerson2());
        model.addAttribute("gzfHousePerson", ghp);
        GzfHousePerson ghp2 = gzfHousePersonService.get(gzfHousePerson.getHousePerson2());
        model.addAttribute("gzfHousePerson2", ghp2);
        return "modules/gzf/gzfHouseChangeSetup3";
    }

    @RequestMapping(value = "setup4")
    public String setup4(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                         HttpServletResponse response, Model model,
                         RedirectAttributes redirectAttributes) {

        GzfHousePerson ghp = gzfHousePersonService.get(gzfHousePerson.getHousePerson1());
        gzfHousePersonService.delete(ghp);
        GzfHousePerson ghp2 = gzfHousePersonService.get(gzfHousePerson.getHousePerson2());
        model.addAttribute("gzfHousePerson2", ghp2);
        gzfHousePersonService.delete(ghp2);

        GzfHousePerson gzfHousePersonNew = new GzfHousePerson();
        gzfHousePersonNew.setGzfHouseholdInfoId(ghp.getGzfHouseholdInfoId());
        gzfHousePersonNew.setGzfHouseholdInfo(ghp.getGzfHouseholdInfo());
        gzfHousePersonNew.setGzfHouseInfoId(ghp2.getGzfHouseInfoId());
        gzfHousePersonNew.setReview("0");
        gzfHousePersonNew.setBind(ghp.getBind());
        gzfHousePersonNew.setCreateBy(ghp.getCreateBy());
        gzfHousePersonNew.setRemarks(ghp.getRemarks());
        gzfHousePersonNew.setAccountId(ghp.getAccountId());
        gzfHousePersonNew.setStartDate(ghp.getStartDate());
        gzfHousePersonNew.setEndDate(ghp.getEndDate());
        gzfHousePersonNew.setEffectiveDate(ghp.getEffectiveDate());
        gzfHousePersonNew.setCheckoutStatus(0);

        GzfHousePerson gzfHousePersonNew2 = new GzfHousePerson();
        gzfHousePersonNew2.setGzfHouseholdInfoId(ghp2.getGzfHouseholdInfoId());
        gzfHousePersonNew2.setGzfHouseholdInfo(ghp2.getGzfHouseholdInfo());
        gzfHousePersonNew2.setGzfHouseInfoId(ghp.getGzfHouseInfoId());
        gzfHousePersonNew2.setReview("0");
        gzfHousePersonNew2.setBind(ghp2.getBind());
        gzfHousePersonNew2.setCreateBy(ghp2.getCreateBy());
        gzfHousePersonNew2.setRemarks(ghp2.getRemarks());
        gzfHousePersonNew2.setAccountId(ghp2.getAccountId());
        gzfHousePersonNew2.setStartDate(ghp2.getStartDate());
        gzfHousePersonNew2.setEndDate(ghp2.getEndDate());
        gzfHousePersonNew2.setEffectiveDate(ghp2.getEffectiveDate());
        gzfHousePersonNew2.setCheckoutStatus(0);

        // 交换住户信息
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
        

//        gzfHousePersonService.save(gzfHousePersonNew);
        try {
        	gzfHousePersonService.save(gzfHousePersonNew2);
        } catch (DuplicateKeyException e) {
        	logger.error("", e);
        	addMessage(redirectAttributes, "房屋已经与人员绑定过");
        	return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/list";
        }
        GzfAccount ac2 = gzfAccountService.get(ghp2.getAccountId());//更新帐户信息
        if (ac2 == null) {
        	throw new RuntimeException("帐户未查到");
        }
        ac2.setCustId(gzfHousePersonNew2.getId());
        gzfAccountService.save(ac2);

        //        GzfAccount ac2 = new GzfAccount();
        //        ac2.setId(ghp2.getAccountId());
        //        gzfAccountService.delete(ac2);//del帐户信息
        //        GzfAccount acc2 = gzfAccountService.generateAccount(gzfHousePersonNew2);// 生成帐户信息
        //        gzfHousePersonNew2.setAccountId(acc2.getId());
        //        gzfHousePersonService.save(gzfHousePersonNew2);

       


        model.addAttribute("gzfHousePerson", gzfHousePersonService.get(gzfHousePersonNew.getId()));
        model
            .addAttribute("gzfHousePerson2", gzfHousePersonService.get(gzfHousePersonNew2.getId()));

        return "modules/gzf/gzfHouseChangeSetup4";
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