/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.service.GzfBuildingService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePropertyService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;

/**
 * 分房管理
 * 
 * @author TU
 * @version $Id: GzfHouseDistributionController.java, v 0.1 2016-4-18 下午10:21:27 TU Exp $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseDistribution")
public class GzfHouseDistributionController extends BaseController {

    @Autowired
    private GzfHouseInfoService     gzfHouseInfoService;
    @Autowired
    private GzfPalacesService       gzfPalacesService;
    @Autowired
    private GzfBuildingService      gzfBuildingService;
    @Autowired
    private GzfHousePropertyService gzfHousePropertyService;
    @Autowired
    private GzfHousePersonService   gzfHousePersonService;
    @Autowired
    private GzfHouseholdInfoService gzfHouseholdInfoService;

    /**
     * 分房人员
     * 
     * @param gzfHouseholdInfo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = { "setup1", "" })
    public String setup1(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        if (gzfHouseholdInfo.getGzfHousePerson() == null) {
            GzfHousePerson gzfHousePerson = new GzfHousePerson();
            gzfHouseholdInfo.setGzfHousePerson(gzfHousePerson);
        }
        model.addAttribute("apply1", gzfHouseholdInfoService.countHousehold("1"));
        model.addAttribute("apply2", gzfHouseholdInfoService.countHousehold("2"));
        model.addAttribute("apply3", gzfHouseholdInfoService.countHousehold("3"));
        gzfHouseholdInfo.setNextStep(2);
        Page<GzfHouseholdInfo> page = gzfHouseholdInfoService.findPageList(
            new Page<GzfHouseholdInfo>(request, response), gzfHouseholdInfo);
        model.addAttribute("page", page);
        return "modules/gzf/gzfHouseDistributionSetup1";
    }

    /**
     * 下次分房
     * 
     * @param gzfHouseholdInfo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "next1")
    public String next1(GzfHouseholdInfo gzfHouseholdInfo, Model model,
                        RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotBlank(gzfHouseholdInfo.getId())) {
            gzfHouseholdInfo = gzfHouseholdInfoService.get(gzfHouseholdInfo);
        }
        gzfHouseholdInfo.setNextStep(1);
        gzfHouseholdInfoService.save(gzfHouseholdInfo);
        addMessage(redirectAttributes, "保存信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseDistribution/?repage";
    }

    /**
     * 选择房源
     * 
     * @param gzfHouseInfo
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "setup2")
    public String setup2(GzfHouseInfo gzfHouseInfo, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        model.addAttribute("apply1", gzfHouseInfoService.countHouse("1"));
        model.addAttribute("apply2", gzfHouseInfoService.countHouse("2"));
        model.addAttribute("apply3", gzfHouseInfoService.countHouse("3"));
        gzfHouseInfo.setNextStep(2);
        Page<GzfHouseInfo> page = gzfHouseInfoService.findPageList(new Page<GzfHouseInfo>(request,
            response), gzfHouseInfo);
        model.addAttribute("page", page);
        return "modules/gzf/gzfHouseDistributionSetup2";
    }

    @RequestMapping(value = "next2")
    public String next2(GzfHouseInfo gzfHouseInfo, Model model,
                        RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotBlank(gzfHouseInfo.getId())) {
            gzfHouseInfo = gzfHouseInfoService.get(gzfHouseInfo);
        }
        gzfHouseInfo.setNextStep(1);
        gzfHouseInfoService.save(gzfHouseInfo);
        addMessage(redirectAttributes, "保存信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseDistribution/setup2/?repage";
    }

    /**
     * 分配房屋
     * 
     * @param gzfHousePerson
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "assign")
    public String assign(GzfHousePerson gzfHousePerson, Model model,
                         RedirectAttributes redirectAttributes, GzfHouseholdInfo gzfHouseholdInfo,
                         GzfHouseInfo gzfHouseInfo, HttpServletRequest request,
                         HttpServletResponse response) {
        //人员
        gzfHouseholdInfo.setNextStep(2);
        Page<GzfHouseholdInfo> page = gzfHouseholdInfoService.findPageList(
            new Page<GzfHouseholdInfo>(request, response, -1), gzfHouseholdInfo);

        //房屋
        gzfHouseInfo.setNextStep(2);
        Page<GzfHouseInfo> house = gzfHouseInfoService.findPageList(new Page<GzfHouseInfo>(request,
            response, -1), gzfHouseInfo);

        List<GzfHouseholdInfo> holdList = page.getList();

        List<GzfHouseInfo> houseList = house.getList();

        for (int i = 0; i < houseList.size(); i++) {
            GzfHouseInfo h = houseList.get(i);
            if (holdList.size() > 0) {
                for (int j = 0; j < holdList.size(); j++) {
                    GzfHouseholdInfo hold = holdList.get(j);
                    gzfHousePerson = new GzfHousePerson();
                    gzfHousePerson.setGzfHouseholdInfoId(hold.getId());
                    gzfHousePerson.setGzfHouseInfoId(h.getId());
                    gzfHousePerson.setCheckoutStatus(0);
                    gzfHousePerson.setReview("0");
                    gzfHousePerson.setBind("1");
                    try {
                        gzfHousePersonService.save(gzfHousePerson);
                        houseList.remove(h);
                        holdList.remove(hold);
                        i--;
                        j--;
                        break;
                    } catch (DuplicateKeyException e) {
                        logger.error("", e);
                    }
                }
            }
        }
        //        for (GzfHouseInfo h : houseList) {
        //            for (GzfHouseholdInfo hold : holdList) {
        //                if (personMap.containsKey(hold.getId())) {
        //                    continue;
        //                }
        //                personMap.put(h.getId(),hold.getId());
        //                gzfHousePerson = new GzfHousePerson();
        //                gzfHousePerson.setGzfHouseholdInfoId(hold.getId());
        //                gzfHousePerson.setGzfHouseInfoId(h.getId());
        //                gzfHousePerson.setCheckoutStatus(0);
        //                gzfHousePerson.setReview("0");
        //                gzfHousePerson.setBind("1");
        //                try {
        //                    gzfHousePersonService.save(gzfHousePerson);
        //                } catch (DuplicateKeyException e) {
        //                    logger.error("", e);
        //                }
        //            }
        //        }
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/list";
    }

    Map<String, String> personMap = new HashMap<String, String>();

    class HosuseHoldAndHouse {
        private String holdid;
        private String houseid;

        public String getHoldid() {
            return holdid;
        }

        public void setHoldid(String holdid) {
            this.holdid = holdid;
        }

        public String getHouseid() {
            return houseid;
        }

        public void setHouseid(String houseid) {
            this.houseid = houseid;
        }

    }

}