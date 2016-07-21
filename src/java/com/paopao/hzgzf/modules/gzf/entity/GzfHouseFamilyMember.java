/**  
 * Project Name:hzgzf  
 * File Name:GzfHouseFamilyMember.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.entity  
 * Date:2016年4月16日下午9:00:27  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.entity;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * ClassName: GzfHouseFamilyMember <br/>
 * Function: 家庭成员表. <br/>
 * date: 2016年4月16日 下午9:00:27 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
public class GzfHouseFamilyMember extends DataEntity<GzfHouseFamilyMember> {

    /**
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -7388553053387502017L;

    /**
     * 所属申请表id
     */
    private String            applyFormId;
    /**
     * 家庭成员姓名
     */
    private String            memberName;
    /**
     * 家庭成员身份证号
     */
    private String            memberIdcard;
    /**
     * 成员电话
     */
    private String            memberPhone;
    /**
     * 成员婚姻状况
     */
    private int               memberMaritalStatus;
    /**
     * 成员所在单位
     */
    private String            memberCompany;
    /**
     * 成员年收入(单位:元)
     */
    private String            memberIncome;
    /**
     * 成员与主申请人关系
     */
    private int               memberRelation;

    /**
     * applyFormId.
     * 
     * @return the applyFormId
     * @since JDK 1.6
     */
    public String getApplyFormId() {
        return applyFormId;
    }

    /**
     * applyFormId.
     * 
     * @param applyFormId the applyFormId to set
     * @since JDK 1.6
     */
    public void setApplyFormId(String applyFormId) {
        this.applyFormId = applyFormId;
    }

    /**
     * memberName.
     * 
     * @return the memberName
     * @since JDK 1.6
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * memberName.
     * 
     * @param memberName the memberName to set
     * @since JDK 1.6
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * memberIdcard.
     * 
     * @return the memberIdcard
     * @since JDK 1.6
     */
    public String getMemberIdcard() {
        return memberIdcard;
    }

    /**
     * memberIdcard.
     * 
     * @param memberIdcard the memberIdcard to set
     * @since JDK 1.6
     */
    public void setMemberIdcard(String memberIdcard) {
        this.memberIdcard = memberIdcard;
    }

    /**
     * memberPhone.
     * 
     * @return the memberPhone
     * @since JDK 1.6
     */
    public String getMemberPhone() {
        return memberPhone;
    }

    /**
     * memberPhone.
     * 
     * @param memberPhone the memberPhone to set
     * @since JDK 1.6
     */
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    /**
     * memberMaritalStatus.
     * 
     * @return the memberMaritalStatus
     * @since JDK 1.6
     */
    public int getMemberMaritalStatus() {
        return memberMaritalStatus;
    }

    /**
     * memberMaritalStatus.
     * 
     * @param memberMaritalStatus the memberMaritalStatus to set
     * @since JDK 1.6
     */
    public void setMemberMaritalStatus(int memberMaritalStatus) {
        this.memberMaritalStatus = memberMaritalStatus;
    }

    /**
     * memberCompany.
     * 
     * @return the memberCompany
     * @since JDK 1.6
     */
    public String getMemberCompany() {
        return memberCompany;
    }

    /**
     * memberCompany.
     * 
     * @param memberCompany the memberCompany to set
     * @since JDK 1.6
     */
    public void setMemberCompany(String memberCompany) {
        this.memberCompany = memberCompany;
    }

    /**
     * memberIncome.
     * 
     * @return the memberIncome
     * @since JDK 1.6
     */
    public String getMemberIncome() {
        return memberIncome;
    }

    /**
     * memberIncome.
     * 
     * @param memberIncome the memberIncome to set
     * @since JDK 1.6
     */
    public void setMemberIncome(String memberIncome) {
        this.memberIncome = memberIncome;
    }

    /**
     * memberRelation.
     * 
     * @return the memberRelation
     * @since JDK 1.6
     */
    public int getMemberRelation() {
        return memberRelation;
    }

    /**
     * memberRelation.
     * 
     * @param memberRelation the memberRelation to set
     * @since JDK 1.6
     */
    public void setMemberRelation(int memberRelation) {
        this.memberRelation = memberRelation;
    }

}
