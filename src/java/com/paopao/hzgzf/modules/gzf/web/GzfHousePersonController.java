/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfVillageService;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.service.GzfAccountService;
import com.paopao.hzgzf.modules.sys.utils.DictUtils;

/**
 * 房屋与人员Controller
 * @author Hongjun
 * @version 2016-01-08
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHousePerson")
public class GzfHousePersonController extends BaseController {

    @Autowired
    private GzfHousePersonService   gzfHousePersonService;

    @Autowired
    private GzfVillageService       gzfVillageService;

    @Autowired
    private GzfPalacesService       gzfPalacesService;

    @Autowired
    private GzfHouseInfoService     gzfHouseInfoService;

    @Autowired
    private GzfHouseholdInfoService gzfHouseholdInfoService;
    @Autowired
    private GzfAccountService       gzfAccountService;

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

    @RequiresPermissions("gzf:gzfHousePerson:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfHousePerson> page = gzfHousePersonService.findPage(new Page<GzfHousePerson>(
            request, response), gzfHousePerson);
        model.addAttribute("page", page);
        return "modules/gzf/gzfHousePersonList";
    }

    @RequiresPermissions("gzf:gzfHousePerson:view")
    @RequestMapping(value = "form")
    public String form(GzfHousePerson gzfHousePerson/*,HttpServletRequest request*/, Model model) {
    	//根据uuid查询数据，并set到model
        model.addAttribute("gzfHousePerson", gzfHousePerson);
        model.addAttribute("gzfVillage", gzfVillageService.findList(new GzfVillage()));
        return "modules/gzf/gzfHousePersonForm";
    }
    
    @RequiresPermissions("gzf:gzfHousePerson:view")
    @RequestMapping(value = "formChange")
    public String formChange(GzfHousePerson gzfHousePerson,HttpServletRequest request, Model model) {
    	String infoUuid = null;
    	Object uuidObj = request.getParameter("gzfHouseholdInfoId");
    	if(uuidObj != null){
    		infoUuid = String.valueOf(uuidObj);	
    	}else{
    		return form(gzfHousePerson,model);
    	}
    	GzfHousePerson gzfHousePersonQuery = new GzfHousePerson();
    	gzfHousePersonQuery.setGzfHouseholdInfoId(infoUuid);
    	GzfHousePerson gzfHousePersonResult = gzfHousePersonService.query(gzfHousePersonQuery);
    	//根据uuid查询数据，并set到model
        model.addAttribute("gzfHousePerson", gzfHousePersonResult);
        List<GzfVillage> gzfVillageList = gzfVillageService.findList(new GzfVillage());
        model.addAttribute("gzfVillage", gzfVillageList);
        return "modules/gzf/gzfHousePersonForm";
    }

    @RequiresPermissions("gzf:gzfHousePerson:view")
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
        return "modules/gzf/gzfHoursePersonInformation";
    }

    @RequiresPermissions("gzf:gzfHousePerson:edit")
    @RequestMapping(value = "save")
    public String save(GzfHousePerson gzfHousePerson, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfHousePerson)) {
            return form(gzfHousePerson, model);
        }
        gzfHousePerson.setCheckoutStatus(0);
        if (gzfHousePerson != null) {
            gzfHousePerson.setGzfHouseholdInfo(gzfHouseholdInfoService.get(gzfHousePerson
                .getGzfHouseholdInfoId()));
            if (gzfHousePerson.getIsNewRecord()) {
                gzfHousePerson.setReview("0");
                gzfHousePerson.setBind("1");
            } else {
                gzfHousePerson.setBind("3");
                GzfHousePerson gzfHousePerson1 = new GzfHousePerson();
                gzfHousePerson1.setReview("0");
                gzfHousePerson1.setBind("1");
                gzfHousePerson1.setStartDate(gzfHousePerson.getStartDate());
                gzfHousePerson1.setEndDate(gzfHousePerson.getEndDate());
                gzfHousePerson1.setEffectiveDate(gzfHousePerson.getEffectiveDate());
                gzfHousePersonService.save(gzfHousePerson);
                addMessage(redirectAttributes, "变更房屋与人员成功");
                return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/list";
            }

        }
        gzfHousePerson.setCreateDate(new Date());

        GzfHousePerson v1 = new GzfHousePerson();
        v1.setGzfHouseholdInfoId(gzfHousePerson.getGzfHouseholdInfoId());
        List vaildList = gzfHousePersonService.findList(v1);
        v1.setGzfHouseholdInfoId("");
        v1.setGzfHouseInfoId(gzfHousePerson.getGzfHouseInfoId());
        List validList2 = gzfHousePersonService.findList(v1);
        if (!vaildList.isEmpty() || !validList2.isEmpty()) {
            addMessage(redirectAttributes, "房屋已经与人员绑定过");
            return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/list";
        }
        try {
            gzfHousePersonService.save(gzfHousePerson);
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            addMessage(redirectAttributes, "房屋已经与人员绑定过");
            return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/list";
        }
        GzfHousePerson person = gzfHousePersonService.get(gzfHousePerson);
        GzfAccount acc = gzfAccountService.generateAccount(person);//生成帐户信息
        gzfHousePerson.setAccountId(acc.getId());
        gzfHousePersonService.save(gzfHousePerson);
        addMessage(redirectAttributes, "保存房屋与人员成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/list";
    }

    @RequiresPermissions("gzf:gzfHousePerson:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfHousePerson gzfHousePerson, RedirectAttributes redirectAttributes) {
        gzfHousePersonService.delete(gzfHousePerson);
        addMessage(redirectAttributes, "删除房屋与人员成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHousePerson/?repage";
    }

    @RequiresPermissions("gzf:gzfHousePerson:view")
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

    @RequiresPermissions("gzf:gzfHousePerson:view")
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

    @RequiresPermissions("gzf:gzfHousePerson:view")
    @RequestMapping(value = "findHouse")
    @ResponseBody
    public List<GzfHouseInfo> findHouse(String id, HttpServletRequest request,
                                        HttpServletResponse response) {
        List<GzfHouseInfo> resultList = new ArrayList<GzfHouseInfo>();
        if (!id.isEmpty()) {
            GzfHouseInfo gzfhi = gzfHouseInfoService.get(id);
            if (gzfhi != null) {
                GzfHousePerson gzfHousePerson = new GzfHousePerson();
                gzfHousePerson.setBind("1");
                gzfHousePerson.setGzfHouseInfoId(id);
                GzfHousePerson person = gzfHousePersonService.query1(gzfHousePerson);
                gzfhi.setGzfHousePerson(person);

                gzfhi.setHouseTypeStr(DictUtils.getDictLabel(String.valueOf(gzfhi.getHouseType()),
                    "house_type", ""));
                gzfhi.setHouseProperty(DictUtils.getDictLabel(String.valueOf(gzfhi.getHouseStat()),
                    "house_status", ""));
                resultList.add(gzfhi);
            }
        }
        return resultList;
    }

    @RequestMapping(value = "removeRoom")
    @ResponseBody
    public List<GzfHouseInfo> removeRoom(String gzfPalacesId, HttpServletRequest request,
                                         HttpServletResponse response) {
        List<GzfHouseInfo> gs = null;
        List<GzfHouseInfo> newgs = new ArrayList<GzfHouseInfo>();
        if (gzfPalacesId != null) {
            GzfHouseInfo gzfHouseInfo = new GzfHouseInfo();
            gzfHouseInfo.setGzfPalacesId(gzfPalacesId);
            gs = gzfHouseInfoService.findList(gzfHouseInfo);
        }
        if (gs != null && !gs.isEmpty()) {
            for (GzfHouseInfo gzfHouseInfo : gs) {
                GzfHousePerson gzfHousePerson = new GzfHousePerson();
                gzfHousePerson.setBind("1");
                gzfHousePerson.setGzfHouseInfoId(gzfHouseInfo.getId());
                GzfHousePerson person = gzfHousePersonService.query1(gzfHousePerson);
                if (person != null)
                    newgs.add(gzfHouseInfo);
            }
        }

        return newgs;
    }
}