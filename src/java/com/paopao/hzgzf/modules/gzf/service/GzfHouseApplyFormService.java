/**  
 * Project Name:hzgzf  
 * File Name:GzfHouseApplyFormService.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.service  
 * Date:2016年4月11日下午4:50:59  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.mapper.JsonMapper;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.modules.gzf.dao.GzfHouseApplyFormDao;
import com.paopao.hzgzf.modules.gzf.dto.GzfHouseApplyFormDto;
import com.paopao.hzgzf.modules.gzf.dto.GzfHouseApplyFormDto.CheckContent;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseApplyForm;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseFamilyMember;

/**
 * ClassName: GzfHouseApplyFormService <br/>
 * Function: 公租房申请表service. <br/>
 * date: 2016年4月11日 下午4:50:59 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
@Service
@Transactional(readOnly = true)
public class GzfHouseApplyFormService extends CrudService<GzfHouseApplyFormDao, GzfHouseApplyForm> {
    @Autowired
    private GzfHouseFamilyMemberService gzfHouseFamilyMemberService;

    @Override
    public GzfHouseApplyForm get(String id) {
        return super.get(id);
    }

    @Override
    public List<GzfHouseApplyForm> findList(GzfHouseApplyForm bean) {
        return super.findList(bean);
    }

    @Override
    public Page<GzfHouseApplyForm> findPage(Page<GzfHouseApplyForm> page, GzfHouseApplyForm bean) {
        return super.findPage(page, bean);
    }

    /**
     * 
     * 返回扩展类型page. <br/>
     * 
     * @author yuliqian
     * @param page
     * @param bean
     * @return
     * @since JDK 1.6
     */
    public Page<GzfHouseApplyFormDto> findDtoPage(Page<GzfHouseApplyForm> page, GzfHouseApplyForm bean) {
        Page<?> basePage = super.findPage(page, bean);
        List<GzfHouseApplyForm> baseBeans = (List<GzfHouseApplyForm>) basePage.getList();
        List dtos = new ArrayList();
        if (baseBeans != null && !baseBeans.isEmpty()) {
            for (GzfHouseApplyForm form : baseBeans) {
                dtos.add(getDto(form));
            }
            basePage.setList(dtos);
        }

        return (Page<GzfHouseApplyFormDto>) basePage;
    }

    /**
     * 
     * 由pojo获取dto. <br/>
     * 
     * @author yuliqian
     * @param bean
     * @return
     * @since JDK 1.6
     */
    public GzfHouseApplyFormDto getDto(GzfHouseApplyForm bean) {
        GzfHouseApplyFormDto dto = new GzfHouseApplyFormDto(bean);
        if (StringUtils.isNotEmpty(bean.getId())) {
            GzfHouseFamilyMember search = new GzfHouseFamilyMember();
            search.setApplyFormId(bean.getId());
            List<GzfHouseFamilyMember> list = gzfHouseFamilyMemberService.findList(search);
            dto.setFamilyMembers(list);
        }
        return dto;
    }

    @Override
    @Transactional(readOnly = false)
    public void save(GzfHouseApplyForm bean) {
        if (bean.getCheckContent() == null)
            bean.setCheckContent("");
        super.save(bean);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(GzfHouseApplyForm bean) {
        super.delete(bean);
    }

    /**
     * 
     * 审核. <br/>
     * 
     * @author yuliqian
     * @param id
     * @param bit
     * @param verify
     * @param checkContent
     * @since JDK 1.6
     */
    @Transactional(readOnly = false)
    public void check(String id, int bit, int verify, String checkContent) {
        if (StringUtils.isEmpty(id)) {
            return;
        }

        GzfHouseApplyForm form = this.get(id);
        if (form == null) {
            return;
        }
        GzfHouseApplyFormDto dto = new GzfHouseApplyFormDto(form);
        List<CheckContent> checkContents = dto.getCheckContents();
        if (checkContents == null) {
            checkContents = new ArrayList<CheckContent>();
        }

        CheckContent c = new CheckContent();
        c.setBit(bit);
        c.setResult(checkContent);
        c.setUpdateTime(DateUtils.getDateTime());

        for (CheckContent cc : checkContents) {
            if (cc.getBit() == c.getBit()) {
                checkContents.remove(cc);
                break;
            }
        }
        checkContents.add(c);

        GzfHouseApplyForm editForm = new GzfHouseApplyForm();
        editForm.setId(id);
        editForm.setCheckContent(JsonMapper.toJsonString(checkContents));
        editForm.setCheckStatus(form.getCheckStatus() | bit);
        editForm.setCheckVerify(form.getCheckVerify() | (verify == 1 ? bit : 0));

        super.dao.updateCheck(editForm);
    }
}
