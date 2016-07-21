/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.datacontract.schemas._2004._07.communityserversync.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tempuri.AssignRoom;
import org.tempuri.UpdateRenter;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;
import com.paopao.hzgzf.modules.gzf.webservice.DataServiceStub;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;

/**
 * 房屋审核
 * 
 * @author TU
 * @version $Id: GzfHousePersonAuditController.java, v 0.1 2016-1-10 下午9:34:54
 *          TU Exp $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHousePersonAudit")
public class GzfHousePersonAuditController extends BaseController {

    @Autowired
    private GzfHousePersonService     gzfHousePersonService;

    @Autowired
    private GzfVillageService         gzfVillageService;

    @Autowired
    private GzfPalacesService         gzfPalacesService;

    @Autowired
    private GzfHouseInfoService       gzfHouseInfoService;

    @Autowired
    private GzfHouseholdInfoService   gzfHouseholdInfoService;

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

    @RequiresPermissions("gzf:gzfHousePersonAudit:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        if (gzfHousePerson == null) {
            gzfHousePerson = new GzfHousePerson();
            gzfHousePerson.setReview("0");
        }
        Page<GzfHousePerson> page = gzfHousePersonService.findPage(new Page<GzfHousePerson>(
            request, response), gzfHousePerson);
        List<GzfHousePerson> list1 = page.getList();
        List<GzfHousePerson> resultList = new ArrayList<GzfHousePerson>();
        if (!list1.isEmpty()) {
            for (GzfHousePerson gzfhp : list1) {
                if (!gzfhp.getGzfHouseholdInfoId().isEmpty()) {
                    GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfhp
                        .getGzfHouseholdInfoId());
                    if (gzfhhi != null) {
                        gzfhp.setGzfHouseholdInfo(gzfhhi);
                    }
                }
                if (!gzfhp.getGzfHouseInfoId().isEmpty()) {
                    GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfhp.getGzfHouseInfoId());
                    if (gzfhi != null) {
                        gzfhp.setGzfHouseInfo(gzfhi);
                    }
                }
                resultList.add(gzfhp);
            }
        }
        page.setList(resultList);
        model.addAttribute("page", page);
        return "modules/gzf/gzfHousePersonAuditList";
    }

    @RequiresPermissions("gzf:gzfHousePersonAudit:view")
    @RequestMapping(value = "form")
    public String form(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        model.addAttribute("gzfHousePerson", gzfHousePerson);
        return "modules/gzf/gzfhousePersonReviewHistory";
    }

    @RequiresPermissions("gzf:gzfHousePersonAudit:view")
    @RequestMapping(value = "information")
    public String information(GzfHousePerson gzfHousePerson, Model model) {
        if (gzfHousePerson != null) {
            GzfHousePerson gzfhp = new GzfHousePerson();
            if (!gzfHousePerson.getGzfHouseholdInfoId().isEmpty()) {
                GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfHousePerson
                    .getGzfHouseholdInfoId());
                if (gzfhhi != null) {
                    gzfhp.setGzfHouseholdInfo(gzfhhi);
                }

            }
            if (!gzfHousePerson.getGzfHouseInfoId().isEmpty()) {
                GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfHousePerson.getGzfHouseInfoId());
                if (gzfhi != null) {
                    GzfPalaces gzfp = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                    if (gzfp != null) {
                        gzfhi.setGzfPalaces(gzfp);
                    }

                    gzfhp.setGzfHouseInfo(gzfhi);
                }
            }
            model.addAttribute("gzfHousePerson", gzfhp);
        } else {
            model.addAttribute("gzfHousePerson", gzfHousePerson);
        }

        model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
        return "modules/gzf/gzfHousePersonAuditInformation";
    }

    @RequestMapping(value = "findName")
    @ResponseBody
    public List<GzfPalaces> findName(String gzfVillageId, HttpServletRequest request,
                                     HttpServletResponse response) {
        List<GzfPalaces> resultList = new ArrayList<GzfPalaces>();
        if (!gzfVillageId.isEmpty()) {
            GzfPalaces gzfp = new GzfPalaces();
            gzfp.setGzfVillageId(gzfVillageId);
            resultList = gzfPalacesService.findList(gzfp);
        }
        return resultList;

    }

    @RequestMapping(value = "findPalace")
    @ResponseBody
    public List<GzfHouseInfo> findPalace(String gzfPalacesId, HttpServletRequest request,
                                         HttpServletResponse response) {
        List<GzfHouseInfo> resultList = new ArrayList<GzfHouseInfo>();
        if (!gzfPalacesId.isEmpty()) {
            GzfHouseInfo gzfhi = new GzfHouseInfo();
            gzfhi.setGzfPalacesId(gzfPalacesId);
            resultList = gzfHouseInfoService.findList(gzfhi);
        }
        return resultList;
    }

    @RequestMapping(value = "findHouse")
    @ResponseBody
    public List<GzfHouseInfo> findHouse(String id, HttpServletRequest request,
                                        HttpServletResponse response) {
        List<GzfHouseInfo> resultList = new ArrayList<GzfHouseInfo>();
        if (!id.isEmpty()) {
            GzfHouseInfo gzfhi = gzfHouseInfoService.get(id);
            if (gzfhi != null) {
                resultList.add(gzfhi);
            }
        }
        return resultList;
    }

    @RequiresPermissions("gzf:gzfHousePersonAudit:edit")
    @RequestMapping(value = "save")
    public String save(GzfHousePerson gzfHousePerson, Model model,
                       RedirectAttributes redirectAttributes) {
        boolean flag = false;
        if (gzfHousePerson != null) {
            if (gzfHousePerson.getIsNewRecord()) {
                addMessage(redirectAttributes, "审核失败");
                return "redirect:" + Global.getAdminPath() + "/gzf/gzfHousePersonAudit/?repage";
            } else {
                if ("1".equals(gzfHousePerson.getIsReview())) {
                    gzfHousePerson.setReview("1");
                    if (!StringUtils.isEmpty(gzfHousePerson.getGzfHouseInfoId())) {
                        GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfHousePerson
                            .getGzfHouseInfoId());
                        if (gzfhi != null) {
                            gzfhi.setHouseStat(2);// 已租
                            gzfHouseInfoService.save(gzfhi, false);
                            flag = true;
                        }
                    }

                    addMessage(redirectAttributes, "审核成功");
                } else if ("2".equals(gzfHousePerson.getIsReview())) {
                    gzfHousePerson.setReview("2");
                    addMessage(redirectAttributes, "审核驳回");
                } else {
                    addMessage(redirectAttributes, "审核失败");
                    return "redirect:" + Global.getAdminPath() + "/gzf/gzfHousePersonAudit/?repage";
                }
            }

        }
        gzfHousePerson.setCheckoutStatus(0);
        gzfHousePersonService.save(gzfHousePerson);

        try {
            // 更新租客信息
            if (flag) {
                DataServiceStub dataServiceStub = null;
                try {
                    dataServiceStub = new DataServiceStub();
                } catch (AxisFault e1) {
                    logger.error("", e1);
                }
                Renter renter = new Renter();
                renter.setCode(gzfHousePerson.getGzfHouseholdInfo().getCode());
                renter.setName(gzfHousePerson.getGzfHouseholdInfo().getName());
                renter.setIdCardNumber(gzfHousePerson.getGzfHouseholdInfo().getCardid());
                renter.setPhoneNumber(gzfHousePerson.getGzfHouseholdInfo().getPhone());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(gzfHousePerson.getStartDate());
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(gzfHousePerson.getEndDate());
                renter.setContractStartDate(calendar);
                renter.setContractEndDate(calendarEnd);
                renter.setMaxCardCount(Integer.valueOf(gzfHousePerson.getGzfHouseholdInfo().getCardnum()));
                UpdateRenter updateRenter = new UpdateRenter();
                updateRenter.setRenter(renter);
                dataServiceStub.updateRenter(updateRenter);

                gzfHousePerson.setGzfHouseInfo(gzfHouseInfoService.get(gzfHousePerson
                    .getGzfHouseInfoId()));
                gzfHousePerson.getGzfHouseholdInfo().setGzfPaymentStandard(
                    gzfPaymentStandardService.get(gzfHousePerson.getGzfHouseholdInfo()
                        .getGzfPaymentStandardId()));
                // 配租
                AssignRoom assignRoom20 = new AssignRoom();
                assignRoom20.setRenterCode(gzfHousePerson.getGzfHouseholdInfo().getCode());
                assignRoom20.setRoomCode(gzfHousePerson.getGzfHouseInfo().getCode());
                assignRoom20.setContractNumber(Integer.valueOf(String.valueOf(gzfHousePerson
                    .getCreateDate().getTime() / 1000)));
                assignRoom20.setMonthlyRent(gzfHousePerson.getGzfHouseholdInfo()
                    .getGzfPaymentStandard().getRentUnitPrice()
                                            * gzfHousePerson.getGzfHouseInfo().getUseArea());
                dataServiceStub.assignRoom(assignRoom20);
            }

        } catch (Exception e) {
            logger.error("", e);
            gzfHousePerson.setSync(1);
            gzfHousePersonService.save(gzfHousePerson);
        }

        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHousePersonAudit/?repage";
    }

    @RequiresPermissions("gzf:gzfHousePersonAudit:edit")
    @RequestMapping(value = "audit")
    public String audit(GzfHousePerson gzfHousePerson, Model model,
                        RedirectAttributes redirectAttributes) {
        GzfHousePerson gzfHP = new GzfHousePerson();
        if (gzfHousePerson != null) {
            if (!gzfHousePerson.getId().isEmpty()) {
                gzfHP = gzfHousePersonService.get(gzfHousePerson.getId());
                if (gzfHP != null) {
                    if (!gzfHP.getGzfHouseholdInfoId().isEmpty()) {
                        GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfHP
                            .getGzfHouseholdInfoId());
                        if (gzfhhi != null) {
                            gzfHP.setGzfHouseholdInfo(gzfhhi);
                        }
                    }
                    if (!gzfHP.getGzfHouseInfoId().isEmpty()) {
                        GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfHP.getGzfHouseholdInfoId());
                        if (gzfhi != null) {
                            gzfHP.setGzfHouseInfo(gzfhi);
                        }
                    }
                }

            }
        }
        model.addAttribute("gzfHousePerson", gzfHP);
        model.addAttribute("audit", UserUtils.getUser().getName());
        return "modules/gzf/gzfhousePersonAudit";
    }

    @RequiresPermissions("gzf:gzfHousePersonAudit:edit")
    @RequestMapping(value = "moreReview")
    @ResponseBody
    public String moreReview(String[] valArr) {
        if (valArr.length <= 0) {
            return "false";
        } else {
            for (String str : valArr) {
                try {
                    GzfHousePerson gzfHP = gzfHousePersonService.get(str);
                    gzfHP.setReview("1");
                    gzfHP.setCheckoutStatus(0);
                    gzfHousePersonService.save(gzfHP);
                    GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfHP.getGzfHouseInfoId());
                    if (gzfhi != null) {
                        gzfhi.setHouseStat(2);// 已租
                        gzfHouseInfoService.save(gzfhi, false);
                    }
                } catch (Exception e) {
                    return "error";
                }
            }
        }

        // gzfVillageService.delete(gzfVillage);
        return "success";
    }

}