/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import com.paopao.hzgzf.modules.gzf.service.GzfBuildingService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePropertyService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;

/**
 * 
 * 
 * @author TU
 * @version $Id: GzfWebSyncController.java, v 0.1 2016-3-28 上午12:32:45 TU Exp $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfWebSync")
public class GzfWebSyncController extends BaseController {

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

    @ModelAttribute
    public GzfHouseInfo get(@RequestParam(required = false) String id) {
        GzfHouseInfo entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfHouseInfoService.get(id);
        }
        if (entity == null) {
            entity = new GzfHouseInfo();
        }
        return entity;
    }

    @RequestMapping(value = { "list", "" })
    public String list(GzfHouseInfo gzfHouseInfo, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        Page<GzfHouseInfo> page = gzfHouseInfoService.findPage(new Page<GzfHouseInfo>(request,
            response), gzfHouseInfo);
        List<GzfHouseInfo> list = page.getList();
        List<GzfHouseInfo> resultList = new ArrayList<GzfHouseInfo>();
        if (!list.isEmpty()) {
            for (GzfHouseInfo gzfhi : list) {
                GzfHousePerson gzfp = new GzfHousePerson();
                gzfp.setGzfHouseInfoId(gzfhi.getId());
                gzfp.setBind("1");
                GzfHousePerson gzfp1 = gzfHousePersonService.query1(gzfp);
                if (gzfp1 != null) {
                    GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfp1
                        .getGzfHouseholdInfoId());
                    if (gzfhhi != null) {
                        gzfp1.setGzfHouseholdInfo(gzfhhi);
                    }
                    gzfhi.setGzfHousePerson(gzfp1);
                }
                resultList.add(gzfhi);
            }
        }
        page.setList(resultList);
        model.addAttribute("page", page);
        return "modules/gzf/gzfWebSyncList";
    }

    @RequestMapping(value = "save")
    public String save(GzfHouseInfo gzfHouseInfo, Model model, RedirectAttributes redirectAttributes) {
        try {
            gzfHouseInfo.setSync(0);
            gzfHouseInfoService.save(gzfHouseInfo, true);
        } catch (DuplicateKeyException e) {
            logger.error("已经初始化过房屋信息", e);
            addMessage(redirectAttributes, "已经初始化过房屋信息");
            return "redirect:" + Global.getAdminPath() + "/gzf/gzfWebSync/?repage";
        }
        addMessage(redirectAttributes, "保存信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfWebSync/?repage";
    }
}