/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.paopao.hzgzf.common.beanvalidator.BeanValidators;
import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.utils.excel.ExportExcel;
import com.paopao.hzgzf.common.utils.excel.ImportExcel;
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

/**
 * 人员信息Controller
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseholdInfo")
public class GzfHouseholdInfoController extends BaseController {

    @Autowired
    private GzfHouseholdInfoService   gzfHouseholdInfoService;

    @Autowired
    private GzfPaymentStandardService gzfPaymentStandardService;

    @Autowired
    private GzfHousePersonService     gzfHousePersonService;

    @Autowired
    private GzfHouseInfoService       gzfHouseInfoService;

    @Autowired
    private GzfPalacesService         gzfPalacesService;

    @ModelAttribute
    public GzfHouseholdInfo get(@RequestParam(required = false) String id) {
        GzfHouseholdInfo entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfHouseholdInfoService.get(id);
        }
        if (entity == null) {
            entity = new GzfHouseholdInfo();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfHouseholdInfo:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        if (gzfHouseholdInfo.getGzfHousePerson() == null) {
            GzfHousePerson gzfHousePerson = new GzfHousePerson();
            gzfHouseholdInfo.setGzfHousePerson(gzfHousePerson);
        }
        Page<GzfHouseholdInfo> page = gzfHouseholdInfoService.findPage(new Page<GzfHouseholdInfo>(
            request, response), gzfHouseholdInfo);
        List<GzfHouseholdInfo> gzfhiList = page.getList();
        List<GzfHouseholdInfo> resultList = new ArrayList<GzfHouseholdInfo>();
        if (!gzfhiList.isEmpty()) {
            for (GzfHouseholdInfo gzfhhi : gzfhiList) {
                GzfHousePerson gzfhp = new GzfHousePerson();
                gzfhp.setGzfHouseholdInfoId(gzfhhi.getId());
                gzfhp.setBind("1");
                GzfHousePerson gzfhp1 = gzfHousePersonService.query(gzfhp);
                if (gzfhp1 != null) {
                    GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfhp1.getGzfHouseInfoId());
                    if (gzfhi != null) {
                        GzfPalaces gzfp = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                        if (gzfp != null) {
                            gzfhi.setGzfPalaces(gzfp);
                        }
                        gzfhp1.setGzfHouseInfo(gzfhi);
                    }
                    gzfhhi.setGzfHousePerson(gzfhp1);
                }
                resultList.add(gzfhhi);
            }
        }
        page.setList(resultList);
        model.addAttribute("page", page);
        return "modules/gzf/gzfHouseholdInfoList";
    }

    @RequiresPermissions("gzf:gzfHouseholdInfo:view")
    @RequestMapping(value = "detail")
    public String detail(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        if (gzfHouseholdInfo != null) {
            if (!gzfHouseholdInfo.getId().isEmpty()) {
                GzfHousePerson gzfp = new GzfHousePerson();
                gzfp.setGzfHouseholdInfoId(gzfHouseholdInfo.getId());
                gzfp.setDel(1);
                Page<GzfHousePerson> page = gzfHousePersonService.findPage(
                    new Page<GzfHousePerson>(request, response), gzfp);
                List<GzfHousePerson> list = page.getList();
                List<GzfHousePerson> resultList = new ArrayList<GzfHousePerson>();
                if (!list.isEmpty()) {
                    for (GzfHousePerson gzfhp : list) {

                        if (gzfhp.getStartDate() != null && gzfhp.getEndDate() != null) {
                            Calendar c1 = Calendar.getInstance();
                            Calendar c2 = Calendar.getInstance();
                            c1.setTime(gzfhp.getStartDate());
                            c2.setTime(gzfhp.getEndDate());
                            int year1 = c1.get(Calendar.YEAR);
                            int year2 = c2.get(Calendar.YEAR);
                            gzfhp.setTimeDiff(Math.abs(year2 - year1));
                        }

                        if (!gzfhp.getGzfHouseInfoId().isEmpty()) {
                            GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfhp.getGzfHouseInfoId());
                            if (gzfhi != null) {

                                GzfPalaces gzfp1 = gzfPalacesService.get(gzfhi.getId());
                                if (gzfp1 != null) {
                                    gzfhi.setGzfPalaces(gzfp1);
                                }
                                gzfhp.setGzfHouseInfo(gzfhi);
                            }
                        }
                        resultList.add(gzfhp);
                    }

                }
                page.setList(resultList);
                GzfHouseholdInfo gzfhhi1 = gzfHouseholdInfoService.get(gzfHouseholdInfo.getId());
                model.addAttribute("gzfHouseholdInfo", gzfhhi1);
                model.addAttribute("page", page);
            }
        } else {
            Page<GzfHousePerson> page = null;
            model.addAttribute("page", page);
        }
        return "modules/gzf/gzfHouseholdUseHistory";
    }

    @RequiresPermissions("gzf:gzfHouseholdInfo:view")
    @RequestMapping(value = "form")
    public String form(GzfHouseholdInfo gzfHouseholdInfo, Model model) {
        model.addAttribute("gzfHouseholdInfo", gzfHouseholdInfo);
        model.addAttribute("gzfPaymentStandardList",
            gzfPaymentStandardService.findList(new GzfPaymentStandard()));
        return "modules/gzf/gzfHouseholdInfoForm";
    }

    @RequiresPermissions("gzf:gzfHouseholdInfo:edit")
    @RequestMapping(value = "save")
    public String save(GzfHouseholdInfo gzfHouseholdInfo, Model model,
                       RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfHouseholdInfo)) {
            return form(gzfHouseholdInfo, model);
        }
        GzfHouseholdInfo his = null;
        try {
            his = gzfHouseholdInfoService.getByCardId(gzfHouseholdInfo.getCardid());
        } catch (Exception e) {
            logger.error("", e);
            addMessage(redirectAttributes, "添加失败！失败信息：身份证号重复：" + gzfHouseholdInfo.getCardid());
            return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/?repage";
        }
        gzfHouseholdInfoService.save(gzfHouseholdInfo);
        addMessage(redirectAttributes, "保存人员信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/?repage";
    }

    @RequiresPermissions("gzf:gzfHouseholdInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfHouseholdInfo gzfHouseholdInfo, RedirectAttributes redirectAttributes) {
        gzfHouseholdInfoService.delete(gzfHouseholdInfo);
        addMessage(redirectAttributes, "删除人员信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseholdInfo/?repage";
    }

    @RequiresPermissions("gzf:gzfHouseholdInfo:edit")
    @RequestMapping(value = "moredelete")
    @ResponseBody
    public String moredelete(String[] valArr) {
        if (valArr.length <= 0) {
            return "false";
        } else {
            for (String str : valArr) {
                GzfHouseholdInfo gzfHouseholdInfo = new GzfHouseholdInfo();
                gzfHouseholdInfo.setId(str);
                try {
                    gzfHouseholdInfoService.delete(gzfHouseholdInfo);
                } catch (Exception e) {
                    return "error";
                }
            }
        }

        //gzfVillageService.delete(gzfVillage);
        return "success";
    }

    /**
     * 导出数据
     * @param user
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(GzfHouseholdInfo gzfHouseholdInfo, HttpServletRequest request,
                             HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "人员数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<GzfHouseholdInfo> page = gzfHouseholdInfoService.findPage(
                new Page<GzfHouseholdInfo>(request, response, -1), gzfHouseholdInfo);
            new ExportExcel("人员数据", GzfHouseholdInfo.class).setDataList(page.getList())
                .write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/gzf/gzfHouseholdInfo/list?repage";
    }

    /**
     * 导入数据
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<GzfHouseholdInfo> list = ei.getDataList(GzfHouseholdInfo.class);
            for (GzfHouseholdInfo gzfHouseholdInfo : list) {
                try {
                    if (checkExport(gzfHouseholdInfo)) {
                        BeanValidators.validateWithException(validator, gzfHouseholdInfo);
                        gzfHouseholdInfoService.save(gzfHouseholdInfo);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>身份证 " + gzfHouseholdInfo.getCardid() + " 已存在; ");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>身份证 " + gzfHouseholdInfo.getCardid() + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex,
                        ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>身份证 " + gzfHouseholdInfo.getCardid() + " 导入失败："
                                      + ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/gzf/gzfHouseholdInfo/list?repage";
    }

    private boolean checkExport(GzfHouseholdInfo gzfHouseholdInfo) {
        GzfHouseholdInfo info = gzfHouseholdInfoService.getByCardId(gzfHouseholdInfo.getCardid());
        gzfHouseholdInfo.setGzfPaymentStandardId(gzfHouseholdInfo.getGzfPaymentStandard().getId());
        if (info == null) {
            return true;
        }
        return false;
    }

    /**
     * 下载导入数据模板
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response,
                                     RedirectAttributes redirectAttributes) {
        try {
            String fileName = "人员数据导入模板.xlsx";
            List<GzfHouseholdInfo> list = Lists.newArrayList();
            new ExportExcel("人员数据", GzfHouseholdInfo.class, 2).setDataList(list)
                .write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/gzf/gzfHouseholdInfo/list?repage";
    }

}