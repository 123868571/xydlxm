/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;

/**
 * 刷身份证
 * 
 * @author TU
 * @version $Id: GzfBrushCardController.java, v 0.1 2016-1-10 下午10:41:33 TU Exp
 *          $
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfBrushCard")
public class GzfBrushCardController extends BaseController {
    @Autowired
    private GzfHouseholdInfoService gzfHouseholdInfoService;

    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/gzf/gzfIdcMovie";
    }

    /**
     * 
     * 身份校验,查询是否已存在于人员表中. <br/>
     * 
     * @author yuliqian
     * @param gzfHousePerson
     * @param request
     * @param response
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = { "check" })
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(required = true, value = "idCard") String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return "身份证号错误!";
        }
        // String idCard1 = "610303198311024532";
        GzfHouseholdInfo holdInfo = gzfHouseholdInfoService.getByCardId(idCard);
        if (holdInfo != null) {
            return "exist";
        }
        return "success";
    }

}