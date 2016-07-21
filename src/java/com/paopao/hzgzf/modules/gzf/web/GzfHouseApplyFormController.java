/**  
 * Project Name:hzgzf  
 * File Name:GzfApplyFormController.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.web  
 * Date:2016年4月11日下午2:14:50  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.paopao.hzgzf.common.mapper.JsonMapper;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.IdcardUtils;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.dto.GzfHouseApplyFormDto;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseApplyForm;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseFamilyMember;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseApplyFormService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseFamilyMemberService;
import com.paopao.hzgzf.modules.sys.entity.Area;
import com.paopao.hzgzf.modules.sys.service.AreaService;

/**
 * ClassName: GzfHouseApplyFormController <br/>
 * Function: 公租房申请表. <br/>
 * date: 2016年4月11日 下午2:14:50 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseApplyForm")
public class GzfHouseApplyFormController extends BaseController {
    @Autowired
    private GzfHouseApplyFormService    gzfHouseApplyFormService;
    @Autowired
    private GzfHouseFamilyMemberService gzfHouseFamilyMemberService;
    @Autowired
    private AreaService                 areaService;

    @ModelAttribute
    public GzfHouseApplyForm get(@RequestParam(required = false) String id) {
        GzfHouseApplyForm entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfHouseApplyFormService.get(id);
        }
        if (entity == null) {
            entity = new GzfHouseApplyForm();
        }
        return entity;
    }

    /**
     * 
     * 申请表列表页. <br/>
     * 
     * @author yuliqian
     * @param request
     * @param response
     * @param model
     * @param formType
     * @return
     * @since JDK 1.6
     */
    @RequiresPermissions("gzf:gzfHouseApplyForm:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHouseApplyForm applyForm, HttpServletRequest request, HttpServletResponse response, Model model) {
        GzfHouseholdInfo houseHoldInfo = new GzfHouseholdInfo();
        model.addAttribute("houseHoldInfo", houseHoldInfo);
        Page<GzfHouseApplyFormDto> page = gzfHouseApplyFormService.findDtoPage(new Page<GzfHouseApplyForm>(request, response), applyForm);
        // if (StringUtils.isNotBlank(housePersonId)) {
        // GzfHousePerson housePerson =
        // gzfHousePersonService.get(housePersonId);
        // if (housePerson != null) {
        // houseHoldInfo =
        // gzfHouseholdInfoService.get(housePerson.getGzfHouseholdInfoId());
        //
        // GzfHouseApplyForm form = gzfHouseApplyFormService.get(housePersonId);
        // if (form != null) {
        // model.addAttribute("form", form);
        // }
        // }
        // }
        // addMessage(model, "已经初始化过房屋信息");
        model.addAttribute("page", page);
        return "modules/gzf/gzfHouseApplyFormList";
    }

    /**
     * 
     * 申请表页面. <br/>
     * 
     * @author yuliqian
     * @param request
     * @param response
     * @param model
     * @param formType
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = { "apply/{formType}" })
    public String apply(GzfHouseApplyForm applyForm, HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int formType) {
        // GzfHouseApplyFormContent1 formContent = new
        // GzfHouseApplyFormContent.GzfHouseApplyFormContent1();
        // formContent.setSelfHouseAddr("杭州莫干山路");
        // formContent.setSelfHouseOwner("老余");
        // formContent.setRentHouseAddr("西湖区");
        // formContent.setRentHouseOwner("小余");
        // formContent.setSensusStatus(1);
        // formContent.setHaveCompany(1);
        // formContent.setCompanyName("19楼");
        // formContent.setWorkingStatus(1);
        // formContent.setHardshipType(1);
        // formContent.setHardshipCard("33333444444");
        // formContent.setHardshipCardExpired(DateUtils.formatDate(DateUtils.addDays(new
        // Date(), 3)));
        // formContent.setOtherSituation("1,2,3");
        // System.out.println(JsonMapper.getInstance().toJson(formContent));

        GzfHouseApplyFormDto dto = gzfHouseApplyFormService.getDto(applyForm);
        String serial = dto.getBean().getSerial();
        if (StringUtils.isEmpty(applyForm.getId())) {
            dto.initFormContent(formType);
            serial = genSerial();
        }

        if (dto != null) {
            Area area = areaService.get(dto.getBean().getApplyMajorAreaId());
            dto.setApplyMajorArea(area);
        }

        model.addAttribute("dto", dto);
        model.addAttribute("serial", serial);

        if (dto.getFamilyMembers() != null && !dto.getFamilyMembers().isEmpty()) {
            model.addAttribute("members", JsonMapper.getInstance().toJson(dto.getFamilyMembers()));
        }

        return "modules/gzf/gzfHouseApplyForm" + formType;
    }

    /**
     * 
     * 生成编号. <br/>
     * 
     * @author yuliqian
     * @return
     * @since JDK 1.6
     */
    private String genSerial() {
        String s = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            s += random.nextInt(10);
        }
        String serial = "ZS" + DateUtils.getDate("yyyyMMdd") + s;

        GzfHouseApplyForm searchForm = new GzfHouseApplyForm();
        searchForm.setSerial(serial);
        List<GzfHouseApplyForm> forms = gzfHouseApplyFormService.findList(searchForm);
        if (forms != null && !forms.isEmpty()) {
            return genSerial();
        }
        return serial;
    }

    /**
     * 
     * 保存申请表. <br/>
     * 
     * @author yuliqian
     * @param request
     * @param response
     * @param model
     * @param formContent
     * @param formType
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = { "save" })
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response, Model model, GzfHouseApplyForm applyForm, @RequestParam(required = true, value = "formContent") String formContent,
                       @RequestParam(required = true, value = "familyMembers") String familyMembers) {
        formContent = formContent.replaceAll("&quot;", "\"");
        familyMembers = familyMembers.replaceAll("&quot;", "\"");

        // 成员数据
        List<GzfHouseFamilyMember> members = null;
        if (StringUtils.isNotEmpty(familyMembers)) {
            JsonMapper json = JsonMapper.getInstance();
            JavaType javaType = json.createCollectionType(ArrayList.class, GzfHouseFamilyMember.class);
            members = json.fromJson(familyMembers, javaType);

        }

        // TODO 数据校验
        if (StringUtils.isEmpty(applyForm.getApplyMajorIdcard()) || !IdcardUtils.validateCard(applyForm.getApplyMajorIdcard())) {
            return "申请人身份证号错误!";
        }
        if (members != null && !members.isEmpty()) {
            for (GzfHouseFamilyMember member : members) {
                if (StringUtils.isEmpty(member.getMemberIdcard()) || !IdcardUtils.validateCard(member.getMemberIdcard())) {
                    return "家庭成员[" + member.getMemberName() + "]身份证号错误!";
                }
            }
        }

        // 新增申请需判断是否已申请有房
        if (StringUtils.isEmpty(applyForm.getId())) {
            GzfHouseApplyForm searchForm = new GzfHouseApplyForm();
            searchForm.setApplyMajorIdcard(applyForm.getApplyMajorIdcard());
            List<GzfHouseApplyForm> forms = gzfHouseApplyFormService.findList(searchForm);
            if (forms != null && !forms.isEmpty()) {
                return "申请人已提交过申请!";
            } else {
                if (members != null && !members.isEmpty()) {
                    for (GzfHouseFamilyMember member : members) {
                        GzfHouseFamilyMember search = new GzfHouseFamilyMember();
                        search.setMemberIdcard(member.getMemberIdcard());
                        List<GzfHouseFamilyMember> ms = gzfHouseFamilyMemberService.findList(search);
                        if (ms != null && !ms.isEmpty()) {
                            return "家庭成员[" + member.getMemberName() + "]已存在于其他申请表的成员中!";
                        }

                        GzfHouseApplyForm search2 = new GzfHouseApplyForm();
                        search2.setApplyMajorIdcard(member.getMemberIdcard());
                        List<GzfHouseApplyForm> forms2 = gzfHouseApplyFormService.findList(search2);
                        if (forms2 != null && !forms2.isEmpty()) {
                            return "家庭成员[" + member.getMemberName() + "]已做为主申请人存在!";
                        }
                    }
                }
            }
        }

        applyForm.setFormContent(formContent);
        gzfHouseApplyFormService.save(applyForm);

        if (members != null && !members.isEmpty()) {
            for (GzfHouseFamilyMember member : members) {
                member.setApplyFormId(applyForm.getId());
                gzfHouseFamilyMemberService.save(member);
            }
        }

        return "success";
    }

    /**
     * 
     * 提交审核. <br/>
     * 
     * @author yuliqian
     * @param request
     * @param response
     * @param model
     * @param id 申请表id
     * @param bit 审核位
     * @param checkVerify 是否同意
     * @param checkContent 审核意见
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = { "check" })
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(required = true, value = "id") String id,
                        @RequestParam(required = true, value = "bit") int bit, @RequestParam(required = true, value = "checkVerify") int checkVerify,
                        @RequestParam(required = false, defaultValue = "", value = "checkContent") String checkContent) {
        if (StringUtils.isEmpty(id)) {
            return "id不能为空";
        }

        gzfHouseApplyFormService.check(id, bit, checkVerify, checkContent);
        return "success";
    }

    /**
     * 
     * 申请详情. <br/>
     * 
     * @author yuliqian
     * @param request
     * @param response
     * @param model
     * @param id
     * @param bit
     * @param checkVerify
     * @param checkContent
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = { "information" })
    public String information(GzfHouseApplyForm applyForm, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isEmpty(applyForm.getId())) {
            return "id不能为空";
        }

        GzfHouseApplyFormDto dto = gzfHouseApplyFormService.getDto(applyForm);
        model.addAttribute("dto", dto);

        return "modules/gzf/gzfHouseApplyFormInformation";
    }
}
