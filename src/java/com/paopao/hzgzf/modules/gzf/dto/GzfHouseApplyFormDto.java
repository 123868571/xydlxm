/**  
 * Project Name:hzgzf  
 * File Name:GzfHouseApplyFormDto.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.dto  
 * Date:2016年4月15日下午2:38:16  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.paopao.hzgzf.common.mapper.JsonMapper;
import com.paopao.hzgzf.modules.gzf.dto.GzfHouseApplyFormContent.GzfHouseApplyFormContent1;
import com.paopao.hzgzf.modules.gzf.dto.GzfHouseApplyFormContent.GzfHouseApplyFormContent2;
import com.paopao.hzgzf.modules.gzf.dto.GzfHouseApplyFormContent.GzfHouseApplyFormContent3;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseApplyForm;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseFamilyMember;
import com.paopao.hzgzf.modules.sys.entity.Area;
import com.paopao.hzgzf.modules.sys.entity.Dict;
import com.paopao.hzgzf.modules.sys.utils.DictContants;
import com.paopao.hzgzf.modules.sys.utils.DictUtils;

/**
 * ClassName: GzfHouseApplyFormDto <br/>
 * Function: 申请表dto. <br/>
 * date: 2016年4月15日 下午2:38:16 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
public class GzfHouseApplyFormDto {

    /**
     * 基bean
     */
    private GzfHouseApplyForm          bean;

    /**
     * 表单内容
     */
    private GzfHouseApplyFormContent   formContent;
    /**
     * 审核状态
     */
    private Map<String, Integer>       checkStatusMap;

    /**
     * 确认状态
     */
    private Map<String, Integer>       checkVerifyMap;

    /**
     * 审核内容Map
     */
    private Map<String, CheckContent>  checkContentMap;

    /**
     * 审核内容
     */
    private List<CheckContent>         checkContents;

    /**
     * 数据字典中的审核名
     */
    private String                     dictCheckName;

    /**
     * 家庭成员
     */
    private List<GzfHouseFamilyMember> familyMembers;

    /**
     * 全部审核状态的状态
     * 
     * @see AllCheckStatusEnum
     */
    private AllCheckStatusEnum         allCheckStatus;

    /**
     * 当前审核步骤,根据checkStatusMap的状态,按顺序判断,值为checkStatusMap的key
     */
    private String                     currentCheckStep;

    /**
     * 主申请人所属区域(对应表applyMajorAreaId字段)
     */
    private Area                       applyMajorArea;

    public GzfHouseApplyFormDto() {

    }

    public GzfHouseApplyFormDto(GzfHouseApplyForm bean) {
        if (bean != null) {
            this.bean = bean;
            int checkStatus = bean.getCheckStatus();
            int checkVerify = bean.getCheckVerify();
            String checkContent = bean.getCheckContent();
            checkStatusMap = new HashMap<String, Integer>();
            checkVerifyMap = new HashMap<String, Integer>();
            checkContentMap = new HashMap<String, CheckContent>();
            dictCheckName = DictContants.APPLY_FORM_TYPE + bean.getFormType() + "_check";

            List<Dict> checks = DictUtils.getDictList(dictCheckName);
            int checkedBits = 0;// 用于计算全部审核步骤的状态
            int allBits = 0;// 全部审核位
            for (Dict dict : checks) {
                String key = dict.getValue();
                int bit = 0;
                if (key.matches("\\d+")) {
                    bit = Integer.parseInt(key);
                    allBits += bit;
                } else {
                    continue;
                }

                if (bit > 0) {
                    // 0未审核,1已审核
                    int cs = (checkStatus & bit) / bit;
                    // 0未通过,1通过
                    int cv = (checkVerify & bit) / bit;
                    checkStatusMap.put(key, cs);
                    checkVerifyMap.put(key, cv);
                    checkContentMap.put(key, new CheckContent());

                    if (cs == 0 && StringUtils.isEmpty(currentCheckStep)) {
                        currentCheckStep = key;
                    }

                    if (cs != 0) {
                        checkedBits += bit;
                        if (cv == 0) {
                            allCheckStatus = AllCheckStatusEnum.ALERT;
                        }
                    }

                }
            }
            if (allCheckStatus != AllCheckStatusEnum.ALERT) {
                if (allBits == checkedBits) {
                    allCheckStatus = AllCheckStatusEnum.CHECKED;
                } else if (checkedBits > 0) {
                    allCheckStatus = AllCheckStatusEnum.CHECKING;
                } else {
                    allCheckStatus = AllCheckStatusEnum.NOCHECKED;
                }
            }

            if (StringUtils.isNotEmpty(checkContent)) {
                JsonMapper json = JsonMapper.getInstance();
                JavaType javaType = json.createCollectionType(ArrayList.class, CheckContent.class);
                this.checkContents = json.fromJson(checkContent, javaType);
                for (CheckContent cc : checkContents) {
                    checkContentMap.put(cc.bit + "", cc);
                }
            }

            initFormContent(bean.getFormType());
        }
    }

    /**
     * 
     * 根据内容和申请表类型初始化申请表内容对象. <br/>
     * 
     * @author yuliqian
     * @param formType
     * @since JDK 1.6
     */
    public void initFormContent(int formType) {
        JsonMapper json = JsonMapper.getInstance();
        switch (formType) {
            case 1:
                if (StringUtils.isNotEmpty(bean.getFormContent())) {
                    formContent = json.fromJson(bean.getFormContent(), GzfHouseApplyFormContent1.class);
                } else {
                    formContent = new GzfHouseApplyFormContent1();
                }
                break;
            case 2:
                if (StringUtils.isNotEmpty(bean.getFormContent())) {
                    formContent = json.fromJson(bean.getFormContent(), GzfHouseApplyFormContent2.class);
                } else {
                    formContent = new GzfHouseApplyFormContent2();
                }
                break;
            case 3:
                if (StringUtils.isNotEmpty(bean.getFormContent())) {
                    formContent = json.fromJson(bean.getFormContent(), GzfHouseApplyFormContent3.class);
                } else {
                    formContent = new GzfHouseApplyFormContent3();
                }
                break;
            default:
        }
    }

    /**
     * bean.
     * 
     * @return the bean
     * @since JDK 1.6
     */
    public GzfHouseApplyForm getBean() {
        return bean;
    }

    /**
     * bean.
     * 
     * @param bean the bean to set
     * @since JDK 1.6
     */
    public void setBean(GzfHouseApplyForm bean) {
        this.bean = bean;
    }

    /**
     * formContent.
     * 
     * @return the formContent
     * @since JDK 1.6
     */
    public GzfHouseApplyFormContent getFormContent() {
        return formContent;
    }

    /**
     * formContent.
     * 
     * @param formContent the formContent to set
     * @since JDK 1.6
     */
    public void setFormContent(GzfHouseApplyFormContent formContent) {
        this.formContent = formContent;
    }

    /**
     * checkStatusMap.
     * 
     * @return the checkStatusMap
     * @since JDK 1.6
     */
    public Map<String, Integer> getCheckStatusMap() {
        return checkStatusMap;
    }

    /**
     * checkVerifyMap.
     * 
     * @return the checkVerifyMap
     * @since JDK 1.6
     */
    public Map<String, Integer> getCheckVerifyMap() {
        return checkVerifyMap;
    }

    /**
     * checkContentMap.
     * 
     * @return the checkContentMap
     * @since JDK 1.6
     */
    public Map<String, CheckContent> getCheckContentMap() {
        return checkContentMap;
    }

    /**
     * checkContents.
     * 
     * @return the checkContents
     * @since JDK 1.6
     */
    public List<CheckContent> getCheckContents() {
        return checkContents;
    }

    /**
     * checkContents.
     * 
     * @param checkContents the checkContents to set
     * @since JDK 1.6
     */
    public void setCheckContents(List<CheckContent> checkContents) {
        this.checkContents = checkContents;
    }

    /**
     * dictCheckName.
     * 
     * @return the dictCheckName
     * @since JDK 1.6
     */
    public String getDictCheckName() {
        return dictCheckName;
    }

    /**
     * familyMembers.
     * 
     * @return the familyMembers
     * @since JDK 1.6
     */
    public List<GzfHouseFamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    /**
     * familyMembers.
     * 
     * @param familyMembers the familyMembers to set
     * @since JDK 1.6
     */
    public void setFamilyMembers(List<GzfHouseFamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }

    /**
     * allCheckStatus.
     * 
     * @return the allCheckStatus
     * @since JDK 1.6
     */
    public AllCheckStatusEnum getAllCheckStatus() {
        return allCheckStatus;
    }

    /**
     * currentCheckStep.
     * 
     * @return the currentCheckStep
     * @since JDK 1.6
     */
    public String getCurrentCheckStep() {
        return currentCheckStep;
    }

    /**
     * currentCheckStep.
     * 
     * @param currentCheckStep the currentCheckStep to set
     * @since JDK 1.6
     */
    public void setCurrentCheckStep(String currentCheckStep) {
        this.currentCheckStep = currentCheckStep;
    }

    /**
     * applyMajorArea.
     * 
     * @return the applyMajorArea
     * @since JDK 1.6
     */
    public Area getApplyMajorArea() {
        return applyMajorArea;
    }

    /**
     * applyMajorArea.
     * 
     * @param applyMajorArea the applyMajorArea to set
     * @since JDK 1.6
     */
    public void setApplyMajorArea(Area applyMajorArea) {
        this.applyMajorArea = applyMajorArea;
    }

    /**
     * 
     * ClassName: CheckContent <br/>
     * Function: 审核内容Json对象. <br/>
     * date: 2016年4月15日 下午2:42:01 <br/>
     * 
     * @author yuliqian
     * @version GzfHouseApplyFormDto
     * @since JDK 1.6
     */
    public static class CheckContent implements Serializable {
        /**
         * @since JDK 1.6
         */
        private static final long serialVersionUID = 1L;

        /**
         * 审核校验位
         */
        private int               bit;

        /**
         * 审核的结果描述
         */
        private String            result;

        /**
         * 审核时间
         */
        private String            updateTime;

        /**
         * bit.
         * 
         * @return the bit
         * @since JDK 1.6
         */
        public int getBit() {
            return bit;
        }

        /**
         * bit.
         * 
         * @param bit the bit to set
         * @since JDK 1.6
         */
        public void setBit(int bit) {
            this.bit = bit;
        }

        /**
         * result.
         * 
         * @return the result
         * @since JDK 1.6
         */
        public String getResult() {
            return result;
        }

        /**
         * result.
         * 
         * @param result the result to set
         * @since JDK 1.6
         */
        public void setResult(String result) {
            this.result = result;
        }

        /**
         * updateTime.
         * 
         * @return the updateTime
         * @since JDK 1.6
         */
        public String getUpdateTime() {
            return updateTime;
        }

        /**
         * updateTime.
         * 
         * @param updateTime the updateTime to set
         * @since JDK 1.6
         */
        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

    }

    public enum AllCheckStatusEnum {
        NOCHECKED(0, "提交"), CHECKING(1, "审核中"), CHECKED(2, "完成"), ALERT(-1, "已拒绝");

        private AllCheckStatusEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getName(int value) {
            for (AllCheckStatusEnum StatusEnum : AllCheckStatusEnum.values()) {
                if (StatusEnum.getValue() == value)
                    return StatusEnum.getName();
            }
            return "";
        }

        private int    value;

        private String name;

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
