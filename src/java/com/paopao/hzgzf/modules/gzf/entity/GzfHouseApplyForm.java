/**  
 * Project Name:hzgzf  
 * File Name:GzfHouseApplyForm.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.entity  
 * Date:2016年4月11日下午4:15:03  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.entity;

import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.modules.sys.utils.DictContants;

/**
 * ClassName: GzfHouseApplyForm <br/>
 * Function: 公租房申请表. <br/>
 * date: 2016年4月11日 下午4:15:03 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
public class GzfHouseApplyForm extends DataEntity<GzfHouseApplyForm> {

    /**
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 6355588901818516511L;

    /**
     * 申请编号
     */
    private String            serial;
    /**
     * 申请属性(市本级/区级),具体定义见dict
     */
    private int               applyType;
    /**
     * 主申请人姓名
     */
    private String            applyMajorName;

    /**
     * 主申请人联系电话
     */
    private String            applyMajorPhone;

    /**
     * 主申请人身份证号
     */
    private String            applyMajorIdcard;

    /**
     * 主申请人婚姻状况,状态说明可查数据字典
     */
    private int               applyMajorMaritalStatus;
    /**
     * 主申请人年收入(单位:元)
     */
    private String            applyMajorIncome;
    /**
     * 主申请人身份证正面
     */
    private String            applyMajorPhotoFront;
    /**
     * 主申请人身份证背面
     */
    private String            applyMajorPhotoBack;
    /**
     * 主申请人所属地区(sys_area表)
     */
    private String            applyMajorAreaId;
    /**
     * 主申请人所属社区(街道)
     */
    private String            applyMajorCommunity;
    /**
     * 申请表类型
     * 
     * @see DictContants.APPLY_FORM_TYPE
     */
    private int               formType;

    /**
     * 申请表详情,json格式表示
     */
    private String            formContent;

    /**
     * 支持32个审核状态位,0表示未审核,1表示已审核
     */
    private int               checkStatus;

    /**
     * 审核确认状态,状态位描述同check_status,状态0表示未通过审核,1表示通过审核
     */
    private int               checkVerify;

    /**
     * 审核的建议内容,json格式表示,[{'bit':4;result:'年收入过高'}]表示状态位的二进制位100审核原因为年收入过高
     */
    private String            checkContent;

    /**
     * serial.
     * 
     * @return the serial
     * @since JDK 1.6
     */
    public String getSerial() {
        return serial;
    }

    /**
     * serial.
     * 
     * @param serial the serial to set
     * @since JDK 1.6
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * applyType.
     * 
     * @return the applyType
     * @since JDK 1.6
     */
    public int getApplyType() {
        return applyType;
    }

    /**
     * applyType.
     * 
     * @param applyType the applyType to set
     * @since JDK 1.6
     */
    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }

    /**
     * applyMajorName.
     * 
     * @return the applyMajorName
     * @since JDK 1.6
     */
    public String getApplyMajorName() {
        return applyMajorName;
    }

    /**
     * applyMajorName.
     * 
     * @param applyMajorName the applyMajorName to set
     * @since JDK 1.6
     */
    public void setApplyMajorName(String applyMajorName) {
        this.applyMajorName = applyMajorName;
    }

    /**
     * applyMajorPhone.
     * 
     * @return the applyMajorPhone
     * @since JDK 1.6
     */
    public String getApplyMajorPhone() {
        return applyMajorPhone;
    }

    /**
     * applyMajorPhone.
     * 
     * @param applyMajorPhone the applyMajorPhone to set
     * @since JDK 1.6
     */
    public void setApplyMajorPhone(String applyMajorPhone) {
        this.applyMajorPhone = applyMajorPhone;
    }

    /**
     * applyMajorIdcard.
     * 
     * @return the applyMajorIdcard
     * @since JDK 1.6
     */
    public String getApplyMajorIdcard() {
        return applyMajorIdcard;
    }

    /**
     * applyMajorIdcard.
     * 
     * @param applyMajorIdcard the applyMajorIdcard to set
     * @since JDK 1.6
     */
    public void setApplyMajorIdcard(String applyMajorIdcard) {
        this.applyMajorIdcard = applyMajorIdcard;
    }

    /**
     * applyMajorMaritalStatus.
     * 
     * @return the applyMajorMaritalStatus
     * @since JDK 1.6
     */
    public int getApplyMajorMaritalStatus() {
        return applyMajorMaritalStatus;
    }

    /**
     * applyMajorMaritalStatus.
     * 
     * @param applyMajorMaritalStatus the applyMajorMaritalStatus to set
     * @since JDK 1.6
     */
    public void setApplyMajorMaritalStatus(int applyMajorMaritalStatus) {
        this.applyMajorMaritalStatus = applyMajorMaritalStatus;
    }

    /**
     * applyMajorPhotoFront.
     * 
     * @return the applyMajorPhotoFront
     * @since JDK 1.6
     */
    public String getApplyMajorPhotoFront() {
        return applyMajorPhotoFront;
    }

    /**
     * applyMajorPhotoFront.
     * 
     * @param applyMajorPhotoFront the applyMajorPhotoFront to set
     * @since JDK 1.6
     */
    public void setApplyMajorPhotoFront(String applyMajorPhotoFront) {
        this.applyMajorPhotoFront = applyMajorPhotoFront;
    }

    /**
     * applyMajorPhotoBack.
     * 
     * @return the applyMajorPhotoBack
     * @since JDK 1.6
     */
    public String getApplyMajorPhotoBack() {
        return applyMajorPhotoBack;
    }

    /**
     * applyMajorPhotoBack.
     * 
     * @param applyMajorPhotoBack the applyMajorPhotoBack to set
     * @since JDK 1.6
     */
    public void setApplyMajorPhotoBack(String applyMajorPhotoBack) {
        this.applyMajorPhotoBack = applyMajorPhotoBack;
    }

    /**
     * applyMajorAreaId.
     * 
     * @return the applyMajorAreaId
     * @since JDK 1.6
     */
    public String getApplyMajorAreaId() {
        return applyMajorAreaId;
    }

    /**
     * applyMajorAreaId.
     * 
     * @param applyMajorAreaId the applyMajorAreaId to set
     * @since JDK 1.6
     */
    public void setApplyMajorAreaId(String applyMajorAreaId) {
        this.applyMajorAreaId = applyMajorAreaId;
    }

    /**
     * applyMajorCommunity.
     * 
     * @return the applyMajorCommunity
     * @since JDK 1.6
     */
    public String getApplyMajorCommunity() {
        return applyMajorCommunity;
    }

    /**
     * applyMajorCommunity.
     * 
     * @param applyMajorCommunity the applyMajorCommunity to set
     * @since JDK 1.6
     */
    public void setApplyMajorCommunity(String applyMajorCommunity) {
        this.applyMajorCommunity = applyMajorCommunity;
    }

    /**
     * formType.
     * 
     * @return the formType
     * @since JDK 1.6
     */
    public int getFormType() {
        return formType;
    }

    /**
     * formType.
     * 
     * @param formType the formType to set
     * @since JDK 1.6
     */
    public void setFormType(int formType) {
        this.formType = formType;
    }

    /**
     * formContent.
     * 
     * @return the formContent
     * @since JDK 1.6
     */
    public String getFormContent() {
        return formContent;
    }

    /**
     * formContent.
     * 
     * @param formContent the formContent to set
     * @since JDK 1.6
     */
    public void setFormContent(String formContent) {
        this.formContent = formContent;
    }

    /**
     * checkStatus.
     * 
     * @return the checkStatus
     * @since JDK 1.6
     */
    public int getCheckStatus() {
        return checkStatus;
    }

    /**
     * checkStatus.
     * 
     * @param checkStatus the checkStatus to set
     * @since JDK 1.6
     */
    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * checkVerify.
     * 
     * @return the checkVerify
     * @since JDK 1.6
     */
    public int getCheckVerify() {
        return checkVerify;
    }

    /**
     * checkVerify.
     * 
     * @param checkVerify the checkVerify to set
     * @since JDK 1.6
     */
    public void setCheckVerify(int checkVerify) {
        this.checkVerify = checkVerify;
    }

    /**
     * checkContent.
     * 
     * @return the checkContent
     * @since JDK 1.6
     */
    public String getCheckContent() {
        return checkContent;
    }

    /**
     * checkContent.
     * 
     * @param checkContent the checkContent to set
     * @since JDK 1.6
     */
    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    /**
     * applyMajorIncome.
     * 
     * @return the applyMajorIncome
     * @since JDK 1.6
     */
    public String getApplyMajorIncome() {
        return applyMajorIncome;
    }

    /**
     * applyMajorIncome.
     * 
     * @param applyMajorIncome the applyMajorIncome to set
     * @since JDK 1.6
     */
    public void setApplyMajorIncome(String applyMajorIncome) {
        this.applyMajorIncome = applyMajorIncome;
    }

}
