/**  
 * Project Name:hzgzf  
 * File Name:GzfHouseApplyFormContent.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.dto  
 * Date:2016年4月16日下午4:55:45  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.dto;

import java.io.Serializable;

/**
 * ClassName: GzfHouseApplyFormContent <br/>
 * Function: 申请表表内容详情标识接口. <br/>
 * date: 2016年4月16日 下午4:55:45 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
public interface GzfHouseApplyFormContent extends Serializable {
    /**
     * 
     * ClassName: GzfHouseApplyFormContent1 <br/>
     * Function: 申请表类型1(困难户)的详情. <br/>
     * date: 2016年4月16日 下午4:56:54 <br/>
     * 
     * @author yuliqian
     * @version GzfHouseApplyFormContent
     * @since JDK 1.6
     */
    public static class GzfHouseApplyFormContent1 implements GzfHouseApplyFormContent {
        /**
         * @since JDK 1.6
         */
        private static final long serialVersionUID = 1L;
        /**
         * 自有住房地址
         */
        private String            selfHouseAddr;
        /**
         * 自有住房产权人
         */
        private String            selfHouseOwner;
        /**
         * 借住房地址
         */
        private String            rentHouseAddr;
        /**
         * 借住房产权人
         */
        private String            rentHouseOwner;
        /**
         * 户籍性质,农or非农
         */
        private int               sensusStatus;
        /**
         * 是否有工作单位,无和有
         */
        private int               haveCompany;
        /**
         * 在无工作单位时的状态,失业or待业
         */
        private int               noWorkingStatus;
        /**
         * 有工作单位时的单位名称
         */
        private String            companyName;
        /**
         * 在有工作单位时的工作状态,工作or退休
         */
        private int               workingStatus;

        /**
         * 困难户类型,低保or特困
         */
        private int               hardshipType;

        /**
         * 困难证证号
         */
        private String            hardshipCard;

        /**
         * 困难证截止日期
         */
        private String            hardshipCardExpired;

        /**
         * 特困其他情况,多选,用逗号分隔
         */
        private String            otherSituation;

        /**
         * selfHouseAddr.
         * 
         * @return the selfHouseAddr
         * @since JDK 1.6
         */
        public String getSelfHouseAddr() {
            return selfHouseAddr;
        }

        /**
         * selfHouseAddr.
         * 
         * @param selfHouseAddr the selfHouseAddr to set
         * @since JDK 1.6
         */
        public void setSelfHouseAddr(String selfHouseAddr) {
            this.selfHouseAddr = selfHouseAddr;
        }

        /**
         * selfHouseOwner.
         * 
         * @return the selfHouseOwner
         * @since JDK 1.6
         */
        public String getSelfHouseOwner() {
            return selfHouseOwner;
        }

        /**
         * selfHouseOwner.
         * 
         * @param selfHouseOwner the selfHouseOwner to set
         * @since JDK 1.6
         */
        public void setSelfHouseOwner(String selfHouseOwner) {
            this.selfHouseOwner = selfHouseOwner;
        }

        /**
         * rentHouseAddr.
         * 
         * @return the rentHouseAddr
         * @since JDK 1.6
         */
        public String getRentHouseAddr() {
            return rentHouseAddr;
        }

        /**
         * rentHouseAddr.
         * 
         * @param rentHouseAddr the rentHouseAddr to set
         * @since JDK 1.6
         */
        public void setRentHouseAddr(String rentHouseAddr) {
            this.rentHouseAddr = rentHouseAddr;
        }

        /**
         * rentHouseOwner.
         * 
         * @return the rentHouseOwner
         * @since JDK 1.6
         */
        public String getRentHouseOwner() {
            return rentHouseOwner;
        }

        /**
         * rentHouseOwner.
         * 
         * @param rentHouseOwner the rentHouseOwner to set
         * @since JDK 1.6
         */
        public void setRentHouseOwner(String rentHouseOwner) {
            this.rentHouseOwner = rentHouseOwner;
        }

        /**
         * sensusStatus.
         * 
         * @return the sensusStatus
         * @since JDK 1.6
         */
        public int getSensusStatus() {
            return sensusStatus;
        }

        /**
         * sensusStatus.
         * 
         * @param sensusStatus the sensusStatus to set
         * @since JDK 1.6
         */
        public void setSensusStatus(int sensusStatus) {
            this.sensusStatus = sensusStatus;
        }

        /**
         * haveCompany.
         * 
         * @return the haveCompany
         * @since JDK 1.6
         */
        public int getHaveCompany() {
            return haveCompany;
        }

        /**
         * haveCompany.
         * 
         * @param haveCompany the haveCompany to set
         * @since JDK 1.6
         */
        public void setHaveCompany(int haveCompany) {
            this.haveCompany = haveCompany;
        }

        /**
         * noWorkingStatus.
         * 
         * @return the noWorkingStatus
         * @since JDK 1.6
         */
        public int getNoWorkingStatus() {
            return noWorkingStatus;
        }

        /**
         * noWorkingStatus.
         * 
         * @param noWorkingStatus the noWorkingStatus to set
         * @since JDK 1.6
         */
        public void setNoWorkingStatus(int noWorkingStatus) {
            this.noWorkingStatus = noWorkingStatus;
        }

        /**
         * companyName.
         * 
         * @return the companyName
         * @since JDK 1.6
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * companyName.
         * 
         * @param companyName the companyName to set
         * @since JDK 1.6
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        /**
         * workingStatus.
         * 
         * @return the workingStatus
         * @since JDK 1.6
         */
        public int getWorkingStatus() {
            return workingStatus;
        }

        /**
         * workingStatus.
         * 
         * @param workingStatus the workingStatus to set
         * @since JDK 1.6
         */
        public void setWorkingStatus(int workingStatus) {
            this.workingStatus = workingStatus;
        }

        /**
         * hardshipType.
         * 
         * @return the hardshipType
         * @since JDK 1.6
         */
        public int getHardshipType() {
            return hardshipType;
        }

        /**
         * hardshipType.
         * 
         * @param hardshipType the hardshipType to set
         * @since JDK 1.6
         */
        public void setHardshipType(int hardshipType) {
            this.hardshipType = hardshipType;
        }

        /**
         * hardshipCard.
         * 
         * @return the hardshipCard
         * @since JDK 1.6
         */
        public String getHardshipCard() {
            return hardshipCard;
        }

        /**
         * hardshipCard.
         * 
         * @param hardshipCard the hardshipCard to set
         * @since JDK 1.6
         */
        public void setHardshipCard(String hardshipCard) {
            this.hardshipCard = hardshipCard;
        }

        /**
         * hardshipCardExpired.
         * 
         * @return the hardshipCardExpired
         * @since JDK 1.6
         */
        public String getHardshipCardExpired() {
            return hardshipCardExpired;
        }

        /**
         * hardshipCardExpired.
         * 
         * @param hardshipCardExpired the hardshipCardExpired to set
         * @since JDK 1.6
         */
        public void setHardshipCardExpired(String hardshipCardExpired) {
            this.hardshipCardExpired = hardshipCardExpired;
        }

        /**
         * otherSituation.
         * 
         * @return the otherSituation
         * @since JDK 1.6
         */
        public String getOtherSituation() {
            return otherSituation;
        }

        /**
         * otherSituation.
         * 
         * @param otherSituation the otherSituation to set
         * @since JDK 1.6
         */
        public void setOtherSituation(String otherSituation) {
            this.otherSituation = otherSituation;
        }

    }

    /**
     * 
     * ClassName: GzfHouseApplyFormContent2 <br/>
     * Function: 申请表2表单详情. <br/>
     * Reason: 新就业大学毕业生申请使用. <br/>
     * date: 2016年4月20日 下午8:47:53 <br/>
     * 
     * @author yuliqian
     * @version GzfHouseApplyFormContent
     * @since JDK 1.6
     */
    public static class GzfHouseApplyFormContent2 implements GzfHouseApplyFormContent {

        /**
         * @since JDK 1.6
         */
        private static final long serialVersionUID = 8051635930377207460L;

        /**
         * 单位注册地址
         */
        private String            companyAddr;
        /**
         * 单位名称
         */
        private String            companyName;

        /**
         * 组织机构代码证
         */
        private String            organizationCode;
        /**
         * 劳动合同起始时间
         */
        private String            laborContractStart;
        /**
         * 社保首次缴纳时间
         */
        private String            socialInsuranceStart;
        /**
         * 学历(dict value值)
         */
        private int               education;
        /**
         * 本科毕业时间
         */
        private String            collegeFinishTime;
        /**
         * 毕业学校
         */
        private String            college;
        /**
         * 自有住房地址
         */
        private String            selfHouseAddr;
        /**
         * 自有住房产权人
         */
        private String            selfHouseOwner;
        /**
         * 借住房地址
         */
        private String            rentHouseAddr;
        /**
         * 借住房产权人
         */
        private String            rentHouseOwner;

        /**
         * companyAddr.
         * 
         * @return the companyAddr
         * @since JDK 1.6
         */
        public String getCompanyAddr() {
            return companyAddr;
        }

        /**
         * companyAddr.
         * 
         * @param companyAddr the companyAddr to set
         * @since JDK 1.6
         */
        public void setCompanyAddr(String companyAddr) {
            this.companyAddr = companyAddr;
        }

        /**
         * company.
         * 
         * @return the company
         * @since JDK 1.6
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * company.
         * 
         * @param company the company to set
         * @since JDK 1.6
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        /**
         * organizationCode.
         * 
         * @return the organizationCode
         * @since JDK 1.6
         */
        public String getOrganizationCode() {
            return organizationCode;
        }

        /**
         * organizationCode.
         * 
         * @param organizationCode the organizationCode to set
         * @since JDK 1.6
         */
        public void setOrganizationCode(String organizationCode) {
            this.organizationCode = organizationCode;
        }

        /**
         * laborContractStart.
         * 
         * @return the laborContractStart
         * @since JDK 1.6
         */
        public String getLaborContractStart() {
            return laborContractStart;
        }

        /**
         * laborContractStart.
         * 
         * @param laborContractStart the laborContractStart to set
         * @since JDK 1.6
         */
        public void setLaborContractStart(String laborContractStart) {
            this.laborContractStart = laborContractStart;
        }

        /**
         * socialInsuranceStart.
         * 
         * @return the socialInsuranceStart
         * @since JDK 1.6
         */
        public String getSocialInsuranceStart() {
            return socialInsuranceStart;
        }

        /**
         * socialInsuranceStart.
         * 
         * @param socialInsuranceStart the socialInsuranceStart to set
         * @since JDK 1.6
         */
        public void setSocialInsuranceStart(String socialInsuranceStart) {
            this.socialInsuranceStart = socialInsuranceStart;
        }

        /**
         * education.
         * 
         * @return the education
         * @since JDK 1.6
         */
        public int getEducation() {
            return education;
        }

        /**
         * education.
         * 
         * @param education the education to set
         * @since JDK 1.6
         */
        public void setEducation(int education) {
            this.education = education;
        }

        /**
         * collegeFinishTime.
         * 
         * @return the collegeFinishTime
         * @since JDK 1.6
         */
        public String getCollegeFinishTime() {
            return collegeFinishTime;
        }

        /**
         * collegeFinishTime.
         * 
         * @param collegeFinishTime the collegeFinishTime to set
         * @since JDK 1.6
         */
        public void setCollegeFinishTime(String collegeFinishTime) {
            this.collegeFinishTime = collegeFinishTime;
        }

        /**
         * college.
         * 
         * @return the college
         * @since JDK 1.6
         */
        public String getCollege() {
            return college;
        }

        /**
         * college.
         * 
         * @param college the college to set
         * @since JDK 1.6
         */
        public void setCollege(String college) {
            this.college = college;
        }

        /**
         * selfHouseAddr.
         * 
         * @return the selfHouseAddr
         * @since JDK 1.6
         */
        public String getSelfHouseAddr() {
            return selfHouseAddr;
        }

        /**
         * selfHouseAddr.
         * 
         * @param selfHouseAddr the selfHouseAddr to set
         * @since JDK 1.6
         */
        public void setSelfHouseAddr(String selfHouseAddr) {
            this.selfHouseAddr = selfHouseAddr;
        }

        /**
         * selfHouseOwner.
         * 
         * @return the selfHouseOwner
         * @since JDK 1.6
         */
        public String getSelfHouseOwner() {
            return selfHouseOwner;
        }

        /**
         * selfHouseOwner.
         * 
         * @param selfHouseOwner the selfHouseOwner to set
         * @since JDK 1.6
         */
        public void setSelfHouseOwner(String selfHouseOwner) {
            this.selfHouseOwner = selfHouseOwner;
        }

        /**
         * rentHouseAddr.
         * 
         * @return the rentHouseAddr
         * @since JDK 1.6
         */
        public String getRentHouseAddr() {
            return rentHouseAddr;
        }

        /**
         * rentHouseAddr.
         * 
         * @param rentHouseAddr the rentHouseAddr to set
         * @since JDK 1.6
         */
        public void setRentHouseAddr(String rentHouseAddr) {
            this.rentHouseAddr = rentHouseAddr;
        }

        /**
         * rentHouseOwner.
         * 
         * @return the rentHouseOwner
         * @since JDK 1.6
         */
        public String getRentHouseOwner() {
            return rentHouseOwner;
        }

        /**
         * rentHouseOwner.
         * 
         * @param rentHouseOwner the rentHouseOwner to set
         * @since JDK 1.6
         */
        public void setRentHouseOwner(String rentHouseOwner) {
            this.rentHouseOwner = rentHouseOwner;
        }

    }

    /**
     * 
     * ClassName: GzfHouseApplyFormContent3 <br/>
     * Function: 申请表3的表单详情. <br/>
     * Reason: 稳定就业外来务工人员申请使用. <br/>
     * date: 2016年4月20日 下午8:55:32 <br/>
     * 
     * @author yuliqian
     * @version GzfHouseApplyFormContent
     * @since JDK 1.6
     */
    public static class GzfHouseApplyFormContent3 implements GzfHouseApplyFormContent {

        /**
         * @since JDK 1.6
         */
        private static final long serialVersionUID = 8051635930377207460L;

        /**
         * 单位所在城区
         */
        private String            companyArea;
        /**
         * 工作单位
         */
        private String            companyName;

        /**
         * 证件号码(类型?)
         */
        private int               paperwork;
        /**
         * 劳动合同起始时间
         */
        private String            laborContractStart;
        /**
         * 社保首次缴纳时间
         */
        private String            socialInsuranceStart;
        /**
         * 优先保障(dict_value)
         */
        private int               priority;
        /**
         * 自有住房地址
         */
        private String            selfHouseAddr;
        /**
         * 自有住房产权人
         */
        private String            selfHouseOwner;
        /**
         * 借住房地址
         */
        private String            rentHouseAddr;
        /**
         * 借住房产权人
         */
        private String            rentHouseOwner;

        /**
         * companyArea.
         * 
         * @return the companyArea
         * @since JDK 1.6
         */
        public String getCompanyArea() {
            return companyArea;
        }

        /**
         * companyArea.
         * 
         * @param companyArea the companyArea to set
         * @since JDK 1.6
         */
        public void setCompanyArea(String companyArea) {
            this.companyArea = companyArea;
        }

        /**
         * company.
         * 
         * @return the company
         * @since JDK 1.6
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * company.
         * 
         * @param company the company to set
         * @since JDK 1.6
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        /**
         * paperwork.
         * 
         * @return the paperwork
         * @since JDK 1.6
         */
        public int getPaperwork() {
            return paperwork;
        }

        /**
         * paperwork.
         * 
         * @param paperwork the paperwork to set
         * @since JDK 1.6
         */
        public void setPaperwork(int paperwork) {
            this.paperwork = paperwork;
        }

        /**
         * laborContractStart.
         * 
         * @return the laborContractStart
         * @since JDK 1.6
         */
        public String getLaborContractStart() {
            return laborContractStart;
        }

        /**
         * laborContractStart.
         * 
         * @param laborContractStart the laborContractStart to set
         * @since JDK 1.6
         */
        public void setLaborContractStart(String laborContractStart) {
            this.laborContractStart = laborContractStart;
        }

        /**
         * socialInsuranceStart.
         * 
         * @return the socialInsuranceStart
         * @since JDK 1.6
         */
        public String getSocialInsuranceStart() {
            return socialInsuranceStart;
        }

        /**
         * socialInsuranceStart.
         * 
         * @param socialInsuranceStart the socialInsuranceStart to set
         * @since JDK 1.6
         */
        public void setSocialInsuranceStart(String socialInsuranceStart) {
            this.socialInsuranceStart = socialInsuranceStart;
        }

        /**
         * priority.
         * 
         * @return the priority
         * @since JDK 1.6
         */
        public int getPriority() {
            return priority;
        }

        /**
         * priority.
         * 
         * @param priority the priority to set
         * @since JDK 1.6
         */
        public void setPriority(int priority) {
            this.priority = priority;
        }

        /**
         * selfHouseAddr.
         * 
         * @return the selfHouseAddr
         * @since JDK 1.6
         */
        public String getSelfHouseAddr() {
            return selfHouseAddr;
        }

        /**
         * selfHouseAddr.
         * 
         * @param selfHouseAddr the selfHouseAddr to set
         * @since JDK 1.6
         */
        public void setSelfHouseAddr(String selfHouseAddr) {
            this.selfHouseAddr = selfHouseAddr;
        }

        /**
         * selfHouseOwner.
         * 
         * @return the selfHouseOwner
         * @since JDK 1.6
         */
        public String getSelfHouseOwner() {
            return selfHouseOwner;
        }

        /**
         * selfHouseOwner.
         * 
         * @param selfHouseOwner the selfHouseOwner to set
         * @since JDK 1.6
         */
        public void setSelfHouseOwner(String selfHouseOwner) {
            this.selfHouseOwner = selfHouseOwner;
        }

        /**
         * rentHouseAddr.
         * 
         * @return the rentHouseAddr
         * @since JDK 1.6
         */
        public String getRentHouseAddr() {
            return rentHouseAddr;
        }

        /**
         * rentHouseAddr.
         * 
         * @param rentHouseAddr the rentHouseAddr to set
         * @since JDK 1.6
         */
        public void setRentHouseAddr(String rentHouseAddr) {
            this.rentHouseAddr = rentHouseAddr;
        }

        /**
         * rentHouseOwner.
         * 
         * @return the rentHouseOwner
         * @since JDK 1.6
         */
        public String getRentHouseOwner() {
            return rentHouseOwner;
        }

        /**
         * rentHouseOwner.
         * 
         * @param rentHouseOwner the rentHouseOwner to set
         * @since JDK 1.6
         */
        public void setRentHouseOwner(String rentHouseOwner) {
            this.rentHouseOwner = rentHouseOwner;
        }

    }
}
