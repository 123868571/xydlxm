/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 房屋与人员Entity
 *
 * @author Hongjun
 * @version 2016-01-08
 */
public class GzfHousePerson extends DataEntity<GzfHousePerson> {

    private static final long serialVersionUID = 1L;
    private String            gzfHouseInfoId;       // 房屋
    private String            gzfHouseholdInfoId;   // 人员
    private String            review;               // 审核
    private String            bind;                 // 绑定
    private Date              startDate;
    private Date              endDate;
    private Date              effectiveDate;
    private String            accountId;            //帐户id

    private String            allSelect;

    private GzfHouseInfo      gzfHouseInfo;

    private Double            num;                  // 度数

    private int               timeDiff;

    private Date              startTime;
    private Date              endTime;
    private String            newAllSelect;

    private List<String>      palacesIds;

    private String            housePerson1;
    private String            housePerson2;

    private String            water;
    private String            elec;
    private String            gas;
    private Date              checkoutDate;
    private int               device;
    private String            deviceRemarks;
    private Double            destroy;
    private String            apply;
    private int               sync;
    private int               del;

    private String            waterOrder;
    private String            waterPhoto;
    private String            elecOrder;
    private String            elecPhoto;
    private String            gasOrder;
    private String            gasPhoto;
    private String            auditRemarks;
    private Integer           checkoutStatus;

    public Integer getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(Integer checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks;
    }

    public String getWaterOrder() {
        return waterOrder;
    }

    public void setWaterOrder(String waterOrder) {
        this.waterOrder = waterOrder;
    }

    public String getWaterPhoto() {
        return waterPhoto;
    }

    public void setWaterPhoto(String waterPhoto) {
        this.waterPhoto = waterPhoto;
    }

    public String getElecOrder() {
        return elecOrder;
    }

    public void setElecOrder(String elecOrder) {
        this.elecOrder = elecOrder;
    }

    public String getElecPhoto() {
        return elecPhoto;
    }

    public void setElecPhoto(String elecPhoto) {
        this.elecPhoto = elecPhoto;
    }

    public String getGasOrder() {
        return gasOrder;
    }

    public void setGasOrder(String gasOrder) {
        this.gasOrder = gasOrder;
    }

    public String getGasPhoto() {
        return gasPhoto;
    }

    public void setGasPhoto(String gasPhoto) {
        this.gasPhoto = gasPhoto;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public String getDeviceRemarks() {
        return deviceRemarks;
    }

    public void setDeviceRemarks(String deviceRemarks) {
        this.deviceRemarks = deviceRemarks;
    }

    public Double getDestroy() {
        return destroy;
    }

    public void setDestroy(Double destroy) {
        this.destroy = destroy;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getElec() {
        return elec;
    }

    public void setElec(String elec) {
        this.elec = elec;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getHousePerson1() {
        return housePerson1;
    }

    public void setHousePerson1(String housePerson1) {
        this.housePerson1 = housePerson1;
    }

    public String getHousePerson2() {
        return housePerson2;
    }

    public void setHousePerson2(String housePerson2) {
        this.housePerson2 = housePerson2;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNewAllSelect() {
        return newAllSelect;
    }

    public void setNewAllSelect(String newAllSelect) {
        this.newAllSelect = newAllSelect;
    }

    public int getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(int timeDiff) {
        this.timeDiff = timeDiff;
    }

    private String newSelect;

    public String getNewSelect() {
        return newSelect;
    }

    public void setNewSelect(String newSelect) {
        this.newSelect = newSelect;
    }

    private GzfHouseholdInfo gzfHouseholdInfo;

    private String           isReview;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(Integer jobCategory) {
        this.jobCategory = jobCategory;
    }

    public Integer getHousehold() {
        return household;
    }

    public void setHousehold(Integer household) {
        this.household = household;
    }

    private Integer age;
    private Integer sex;
    private Integer industry;
    private Integer education;
    private Integer jobCategory;
    private Integer household;

    public String getIsReview() {
        return isReview;
    }

    public void setIsReview(String isReview) {
        this.isReview = isReview;
    }

    public String getAllSelect() {
        return allSelect;
    }

    public void setAllSelect(String allSelect) {
        this.allSelect = allSelect;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public GzfHouseholdInfo getGzfHouseholdInfo() {
        return gzfHouseholdInfo;
    }

    public void setGzfHouseholdInfo(GzfHouseholdInfo gzfHouseholdInfo) {
        this.gzfHouseholdInfo = gzfHouseholdInfo;
    }

    public GzfHouseInfo getGzfHouseInfo() {
        return gzfHouseInfo;
    }

    public void setGzfHouseInfo(GzfHouseInfo gzfHouseInfo) {
        this.gzfHouseInfo = gzfHouseInfo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public GzfHousePerson() {
        super();
    }

    public GzfHousePerson(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "房屋长度必须介于 1 和 64 之间")
    public String getGzfHouseInfoId() {
        return gzfHouseInfoId;
    }

    public void setGzfHouseInfoId(String gzfHouseInfoId) {
        this.gzfHouseInfoId = gzfHouseInfoId;
    }

    @Length(min = 1, max = 64, message = "人员长度必须介于 1 和 64 之间")
    public String getGzfHouseholdInfoId() {
        return gzfHouseholdInfoId;
    }

    public void setGzfHouseholdInfoId(String gzfHouseholdInfoId) {
        this.gzfHouseholdInfoId = gzfHouseholdInfoId;
    }

    @Length(min = 0, max = 11, message = "审核长度必须介于 0 和 11 之间")
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Length(min = 0, max = 11, message = "绑定长度必须介于 0 和 11 之间")
    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public List<String> getPalacesIds() {
        return palacesIds;
    }

    public void setPalacesIds(List<String> palacesIds) {
        this.palacesIds = palacesIds;
    }

    public Date getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(Date endRentDate) {
        this.endRentDate = endRentDate;
    }

    private Date endRentDate;

    public Date getEndRentEndDate() {
        return endRentEndDate;
    }

    public void setEndRentEndDate(Date endRentEndDate) {
        this.endRentEndDate = endRentEndDate;
    }

    private Date endRentEndDate;

    public Date getStartRentEndDate() {
        return startRentEndDate;
    }

    public void setStartRentEndDate(Date startRentEndDate) {
        this.startRentEndDate = startRentEndDate;
    }

    private Date startRentEndDate;

}
