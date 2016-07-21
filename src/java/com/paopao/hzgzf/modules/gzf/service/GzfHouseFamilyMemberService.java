/**  
 * Project Name:hzgzf  
 * File Name:GzfHouseApplyFormService.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.service  
 * Date:2016年4月11日下午4:50:59  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.gzf.dao.GzfHouseFamilyMemberDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseFamilyMember;

/**
 * ClassName: GzfHouseFamilyMemberService <br/>
 * Function: 公租房申请表-成员表服务. <br/>
 * date: 2016年4月11日 下午4:50:59 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
@Service
@Transactional(readOnly = true)
public class GzfHouseFamilyMemberService extends CrudService<GzfHouseFamilyMemberDao, GzfHouseFamilyMember> {

    @Override
    public GzfHouseFamilyMember get(String id) {
        return super.get(id);
    }

    @Override
    public List<GzfHouseFamilyMember> findList(GzfHouseFamilyMember bean) {
        return super.findList(bean);
    }

    @Override
    public Page<GzfHouseFamilyMember> findPage(Page<GzfHouseFamilyMember> page, GzfHouseFamilyMember bean) {
        return super.findPage(page, bean);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(GzfHouseFamilyMember bean) {
        super.save(bean);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(GzfHouseFamilyMember bean) {
        super.delete(bean);
    }

}
