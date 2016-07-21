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
import org.springframework.dao.DuplicateKeyException;
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
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseProperty;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfMaintenance;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfRepairManagerProject;
import com.paopao.hzgzf.modules.gzf.entity.NameId;
import com.paopao.hzgzf.modules.gzf.service.GzfBuildingService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePropertyService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseholdInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfMaintenanceService;
import com.paopao.hzgzf.modules.gzf.service.GzfPalacesService;
import com.paopao.hzgzf.modules.gzf.service.GzfRepairManagerProjectService;

/**
 * 房屋信息Controller
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseInfo")
public class GzfHouseInfoController extends BaseController {

    @Autowired
    private GzfHouseInfoService            gzfHouseInfoService;
    @Autowired
    private GzfPalacesService              gzfPalacesService;
    @Autowired
    private GzfBuildingService             gzfBuildingService;
    @Autowired
    private GzfHousePropertyService        gzfHousePropertyService;
    @Autowired
    private GzfHousePersonService          gzfHousePersonService;
    @Autowired
    private GzfHouseholdInfoService        gzfHouseholdInfoService;
    @Autowired
    private GzfMaintenanceService          gzfMaintenanceService;
    @Autowired
    private GzfRepairManagerProjectService gzfRepairManagerProjectService;

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

    @RequiresPermissions("gzf:gzfHouseInfo:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHouseInfo gzfHouseInfo, HttpServletRequest request,
                       HttpServletResponse response, Model model) {
        String rom = gzfHouseInfo.getRoom();
        if (!StringUtils.isEmpty(gzfHouseInfo.getRoom())) {
            String[] rooms = StringUtils.split(gzfHouseInfo.getRoom(), "-");
            if (rooms.length == 3) {
                gzfHouseInfo.setBuildNum(Integer.valueOf(rooms[0]));
                gzfHouseInfo.setUnit(Integer.valueOf(rooms[1]));
                gzfHouseInfo.setRoom(rooms[2]);
            } else if (rooms.length == 2) {
                gzfHouseInfo.setUnit(Integer.valueOf(rooms[0]));
                gzfHouseInfo.setRoom(rooms[1]);
            }
        }

        Page<GzfHouseInfo> page = gzfHouseInfoService.findPage(new Page<GzfHouseInfo>(request,
            response), gzfHouseInfo);
        gzfHouseInfo.setRoom(rom);
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
        return "modules/gzf/gzfHouseInfoList";
    }

    @RequiresPermissions("gzf:gzfHouseInfo:view")
    @RequestMapping(value = "information")
    public String information(GzfHouseInfo gzfHouseInfo, HttpServletRequest request,
                              HttpServletResponse response, Model model) {
        GzfHouseInfo gzfHouseInfo1 = new GzfHouseInfo();
        if (gzfHouseInfo != null) {
            if (!gzfHouseInfo.getId().isEmpty()) {
                GzfHousePerson gzfhp = new GzfHousePerson();
                gzfhp.setGzfHouseInfoId(gzfHouseInfo.getId());
                gzfhp.setStartTime(gzfHouseInfo.getStartTime());
                gzfhp.setEndTime(gzfHouseInfo.getEndTime());
                gzfhp.setNewAllSelect(gzfHouseInfo.getAllSelect());
                gzfhp.setDel(1);
                Page<GzfHousePerson> page = gzfHousePersonService.findPage(
                    new Page<GzfHousePerson>(request, response), gzfhp);
                List<GzfHousePerson> list = page.getList();
                List<GzfHousePerson> resultList = new ArrayList<GzfHousePerson>();
                if (!list.isEmpty()) {
                    for (GzfHousePerson gzfhp1 : list) {

                        if (gzfhp1.getStartDate() != null && gzfhp1.getEndDate() != null) {
                            Calendar c1 = Calendar.getInstance();
                            Calendar c2 = Calendar.getInstance();
                            c1.setTime(gzfhp1.getStartDate());
                            c2.setTime(gzfhp1.getEndDate());
                            int year1 = c1.get(Calendar.YEAR);
                            int year2 = c2.get(Calendar.YEAR);
                            gzfhp1.setTimeDiff(Math.abs(year2 - year1));
                        }

                        if (!gzfhp1.getGzfHouseholdInfoId().isEmpty()) {
                            GzfHouseholdInfo gzfhi = gzfHouseholdInfoService.get(gzfhp1
                                .getGzfHouseholdInfoId());
                            if (gzfhi != null) {
                                gzfhp1.setGzfHouseholdInfo(gzfhi);
                            }
                        }
                        resultList.add(gzfhp1);
                    }
                }
                page.setList(resultList);

                if (gzfHouseInfo.getId() != null) {
                    gzfHouseInfo1 = gzfHouseInfoService.get(gzfHouseInfo.getId());
                    if (gzfHouseInfo1 != null) {
                        if (gzfHouseInfo1.getGzfPalacesId() != null) {
                            GzfPalaces gzfPalaces = gzfPalacesService.get(gzfHouseInfo1
                                .getGzfPalacesId());
                            if (gzfPalaces != null) {
                                gzfHouseInfo1.setGzfPalaces(gzfPalaces);
                            }
                        }
                    }
                }
                model.addAttribute("page", page);
            }
        } else {
            Page<GzfHousePerson> page = null;
            model.addAttribute("page", page);
        }
        model.addAttribute("gzfHouseInfo", gzfHouseInfo1 == null ? new GzfHouseInfo()
            : gzfHouseInfo1);
        return "modules/gzf/gzfBuildUseHistory";
    }

    @RequiresPermissions("gzf:gzfHouseInfo:view")
    @RequestMapping(value = "form")
    public String form(GzfHouseInfo gzfHouseInfo, Model model) {
        model.addAttribute("gzfHouseInfo", gzfHouseInfo);
        model.addAttribute("gzfPalacesList", gzfPalacesService.findList(new GzfPalaces()));
        model.addAttribute("gzfHousePropertyList",
            gzfHousePropertyService.findList(new GzfHouseProperty()));

        toList(String.valueOf(gzfHouseInfo.getBuildNum()), model, "buildNumList");
        toList(String.valueOf(gzfHouseInfo.getUnit()), model, "unitList");
        toList(String.valueOf(gzfHouseInfo.getRoom()), model, "roomList");

        return "modules/gzf/gzfHouseInfoForm";
    }

    private void toList(String num, Model model, String modelName) {
        if (StringUtils.isNotEmpty(num) && !StringUtils.equals("null", num)) {
            List<NameId> minBList = new ArrayList<NameId>();
            NameId n = new NameId();
            n.setId(num);
            n.setName(num);
            minBList.add(n);
            model.addAttribute(modelName, minBList);
        }
    }

    @RequiresPermissions("gzf:gzfHouseInfo:view")
    @RequestMapping(value = "detail")
    public String detail(GzfHouseInfo gzfHouseInfo, GzfMaintenance gzfMaintenance,
                         HttpServletRequest request, HttpServletResponse response, Model model) {
        GzfHouseholdInfo gzfhhi = new GzfHouseholdInfo();
        GzfHouseInfo gzfhi1 = new GzfHouseInfo();
        if (gzfHouseInfo != null) {
            if (!gzfHouseInfo.getId().isEmpty()) {
                GzfHousePerson gzfhp = new GzfHousePerson();
                gzfhp.setGzfHouseInfoId(gzfHouseInfo.getId());
                gzfhp.setBind("1");
                GzfHousePerson gzfhp1 = gzfHousePersonService.query1(gzfhp);
                if (gzfhp1 != null) {
                    gzfhhi = gzfHouseholdInfoService.get(gzfhp1.getGzfHouseholdInfoId());

                }
                gzfhi1 = gzfHouseInfoService.get(gzfHouseInfo.getId());
                if (gzfhi1 != null) {
                    if (!gzfhi1.getGzfPalacesId().isEmpty()) {
                        GzfPalaces gzfp = gzfPalacesService.get(gzfhi1.getGzfPalacesId());
                        if (gzfp != null) {
                            gzfhi1.setGzfPalaces(gzfp);
                        }
                    }

                }

            }
        }
        model.addAttribute("gzfHouseholdInfo", gzfhhi);
        model.addAttribute("gzfHouseInfo", gzfhi1 == null ? new GzfHouseInfo() : gzfhi1);

        gzfMaintenance.setGzfHouseInfoId(gzfhi1.getId());
        Page<GzfMaintenance> page = gzfMaintenanceService.findPage(new Page<GzfMaintenance>(
            request, response), gzfMaintenance);

        List<GzfMaintenance> list = page.getList();
        if (!list.isEmpty()) {
            for (GzfMaintenance gzfrm : list) {
                if (gzfrm.getGzfHouseInfoId() != null && !"".equals(gzfrm.getGzfHouseInfoId())) {
                    GzfHouseInfo gzfhi = gzfHouseInfoService.get(gzfrm.getGzfHouseInfoId());
                    if (gzfhi != null) {
                        if (gzfhi.getGzfPalacesId() != null && !"".equals(gzfhi.getGzfPalacesId())) {
                            GzfPalaces gzfP = gzfPalacesService.get(gzfhi.getGzfPalacesId());
                            if (gzfP != null) {
                                gzfhi.setGzfPalaces(gzfP);
                            }
                        }

                        gzfrm.setGzfHouseInfo(gzfhi);
                    }
                }
                GzfRepairManagerProject gzfRepairManagerProject = new GzfRepairManagerProject();
                gzfRepairManagerProject.setGzfRepairManagerId(gzfrm.getId());
                List<GzfRepairManagerProject> gps = gzfRepairManagerProjectService
                    .findList(gzfRepairManagerProject);
                StringBuilder sb = new StringBuilder();
                for (GzfRepairManagerProject gp : gps) {
                    sb.append(gp.getGzfRepairProject().getName()).append(",");
                }
                gzfrm.setProjects(sb.toString());
            }
        }
        page.setList(list);
        model.addAttribute("page", page);

        return "modules/gzf/gzfHouseInformation";
    }

    @RequiresPermissions("gzf:gzfHouseInfo:edit")
    @RequestMapping(value = "save")
    public String save(GzfHouseInfo gzfHouseInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, gzfHouseInfo)) {
            return form(gzfHouseInfo, model);
        }
        try {
            gzfHouseInfoService.save(gzfHouseInfo, true);
        } catch (DuplicateKeyException e) {
            logger.error("已经初始化过房屋信息", e);
            addMessage(redirectAttributes, "已经初始化过房屋信息");
            return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseInfo/?repage";
        }
        addMessage(redirectAttributes, "保存房屋信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseInfo/?repage";
    }

    @RequiresPermissions("gzf:gzfHouseInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfHouseInfo gzfHouseInfo, RedirectAttributes redirectAttributes) {
        gzfHouseInfoService.delete(gzfHouseInfo);
        addMessage(redirectAttributes, "删除房屋信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseInfo/?repage";
    }

    @RequiresPermissions("gzf:gzfHouseInfo:edit")
    @RequestMapping(value = "deleteArr")
    @ResponseBody
    public String deleteArr(String[] valArr, RedirectAttributes redirectAttributes) {
        System.out.println("================");
        for (String id : valArr) {
            System.out.println(id);
            try {
                GzfHouseInfo gffHouseInfo = new GzfHouseInfo();
            } catch (Exception e) {
                return "";
            }
        }
        return "";
        /*gzfHouseInfoService.delete(gzfHouseInfo);
        addMessage(redirectAttributes, "删除房屋信息成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseInfo/?repage";*/
    }

    @RequiresPermissions("gzf:gzfHouseInfo:edit")
    @RequestMapping(value = "moredelete")
    @ResponseBody
    public String moredelete(String[] valArr) {
        if (valArr.length <= 0) {
            return "false";
        } else {
            for (String str : valArr) {
                GzfHouseInfo gzfHouseInfo = new GzfHouseInfo();
                gzfHouseInfo.setId(str);
                try {
                    gzfHouseInfoService.delete(gzfHouseInfo);
                } catch (Exception e) {
                    return "error";
                }
            }
        }

        //gzfVillageService.delete(gzfVillage);
        return "success";
    }

    @RequiresPermissions("gzf:gzfHouseInfo:view")
    @RequestMapping(value = "findName")
    @ResponseBody
    public GzfPalaces findName(String gzfPalacesId, HttpServletRequest request,
                               HttpServletResponse response) {

        GzfPalaces gzfPalaces = new GzfPalaces();
        if (gzfPalacesId != null) {
            gzfPalaces = gzfPalacesService.get(gzfPalacesId);
            GzfHouseInfo gzfHouseInfo = new GzfHouseInfo();
            gzfHouseInfo.setGzfPalacesId(gzfPalacesId);
        }
        return gzfPalaces;

    }

    @RequestMapping(value = "removeRoom")
    @ResponseBody
    public List<GzfHouseInfo> removeRoom(GzfHouseInfo gzfHouseInfo, HttpServletRequest request,
                                         HttpServletResponse response) {
        List<GzfHouseInfo> gs = null;
        gzfHouseInfo.setGzfPalacesId(gzfHouseInfo.getGzfPalacesId());
        gzfHouseInfo.setBuildNum(gzfHouseInfo.getBuildNum());
        gzfHouseInfo.setUnit(gzfHouseInfo.getUnit());
        gs = gzfHouseInfoService.findList(gzfHouseInfo);
        return gs;
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
    public String exportFile(GzfHouseInfo gzfHouseInfo, HttpServletRequest request,
                             HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "房屋数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<GzfHouseInfo> page = gzfHouseInfoService.findPage(new Page<GzfHouseInfo>(request,
                response, -1), gzfHouseInfo);
            new ExportExcel("房屋数据", GzfHouseInfo.class).setDataList(page.getList())
                .write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出房屋失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/gzf/gzfHouseInfo/list?repage";
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
            List<GzfHouseInfo> list = ei.getDataList(GzfHouseInfo.class);
            for (GzfHouseInfo gzfHouseInfo : list) {
                try {
                    if (checkExport(gzfHouseInfo)) {
                        BeanValidators.validateWithException(validator, gzfHouseInfo);
                        gzfHouseInfoService.save(gzfHouseInfo, true);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>房屋 " + gzfHouseInfo.getGzfPalaces() + "苑"
                                          + gzfHouseInfo.getBuildNum() + "楼栋"
                                          + gzfHouseInfo.getUnit() + "单元" + gzfHouseInfo.getRoom()
                                          + "房间号" + " 已存在; ");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>房屋 " + gzfHouseInfo.getGzfPalaces() + "苑"
                                      + gzfHouseInfo.getBuildNum() + "楼栋" + gzfHouseInfo.getUnit()
                                      + "单元" + gzfHouseInfo.getRoom() + "房间号" + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex,
                        ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>房屋 " + gzfHouseInfo.getGzfPalaces() + "苑"
                                      + gzfHouseInfo.getBuildNum() + "楼栋" + gzfHouseInfo.getUnit()
                                      + "单元" + gzfHouseInfo.getRoom() + "房间号" + " 导入失败："
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
        return "redirect:" + adminPath + "/gzf/gzfHouseInfo/list?repage";
    }

    private boolean checkExport(GzfHouseInfo gzfHouseInfo) {
        //1.先找苑
        GzfPalaces gzfPalaces = gzfHouseInfo.getGzfPalaces();
        if (gzfPalaces != null) {
            GzfHouseProperty pro = gzfHouseInfo.getGzfHouseProperty();
            if (pro != null) {
                gzfHouseInfo.setGzfHousePropertyId(pro.getId());
            }
            //2.查找重复
            gzfHouseInfo.setGzfPalacesId(gzfPalaces.getId());
            GzfHouseInfo ghis = gzfHouseInfoService.getByHouseInfo(gzfHouseInfo);
            if (ghis == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 下载导入用户数据模板
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response,
                                     RedirectAttributes redirectAttributes) {
        try {
            String fileName = "房屋数据导入模板.xlsx";
            List<GzfHouseInfo> list = Lists.newArrayList();
            new ExportExcel("房屋数据", GzfHouseInfo.class, 2).setDataList(list)
                .write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/gzf/gzfHouseInfo/list?repage";
    }
}