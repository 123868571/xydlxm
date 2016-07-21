/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 维修修管理Entity
 * @author Hongjun
 * @version 2016-01-18
 */
public class GzfMaintenance extends DataEntity<GzfMaintenance> {

    private static final long       serialVersionUID = 1L;
    private String                  repairNum;              // 报修单号
    private String                  repairType;             // 报修项目
    private Date                    time;                   // 报修时间
    private String                  gzfHouseInfoId;         // 房屋
    private String                  gzfHouseholdInfoId;     // 租户
    private String                  name;                   // 报修人
    private String                  phone;                  // 报修人电话
    private String                  content;                // 故障现象
    private String                  maintenanceId;          // 维修状态(维修表id)
    private String                  houseInfo;

    private String                  gzfVillage;

    private String                  gzfPalacesId;

    private String                  allSelect;
    private Date                    startDate;
    private Date                    endDate;

    private Date                    repairTime;
    private String                  repairName;
    private String                  repairPhone;
    private String                  repairProcessType;
    private String                  repairContent;

    private GzfRepairManagerProject gzfRepairManagerProject;
    private GzfRepairProject        gzfRepairProject;

    private String                  projects;

    private Date                    confirmTime;
    private String                  confirmPerson;
    private String                  confirmRemarks;
    private Integer                 processStatus    = 0;

    public GzfRepairProject getGzfRepairProject() {
        return gzfRepairProject;
    }

    public void setGzfRepairProject(GzfRepairProject gzfRepairProject) {
        this.gzfRepairProject = gzfRepairProject;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getConfirmRemarks() {
        return confirmRemarks;
    }

    public void setConfirmRemarks(String confirmRemarks) {
        this.confirmRemarks = confirmRemarks;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getConfirmPerson() {
        return confirmPerson;
    }

    public void setConfirmPerson(String confirmPerson) {
        this.confirmPerson = confirmPerson;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public GzfRepairManagerProject getGzfRepairManagerProject() {
        return gzfRepairManagerProject;
    }

    public void setGzfRepairManagerProject(GzfRepairManagerProject gzfRepairManagerProject) {
        this.gzfRepairManagerProject = gzfRepairManagerProject;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    private GzfHouseInfo     gzfHouseInfo;

    private GzfHouseholdInfo gzfHouseholdInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
    }

    public String getRepairProcessType() {
        return repairProcessType;
    }

    public void setRepairProcessType(String repairProcessType) {
        this.repairProcessType = repairProcessType;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public String getGzfVillage() {
        return gzfVillage;
    }

    public void setGzfVillage(String gzfVillage) {
        this.gzfVillage = gzfVillage;
    }

    public String getGzfPalacesId() {
        return gzfPalacesId;
    }

    public void setGzfPalacesId(String gzfPalacesId) {
        this.gzfPalacesId = gzfPalacesId;
    }

    public GzfHouseInfo getGzfHouseInfo() {
        return gzfHouseInfo;
    }

    public void setGzfHouseInfo(GzfHouseInfo gzfHouseInfo) {
        this.gzfHouseInfo = gzfHouseInfo;
    }

    public GzfHouseholdInfo getGzfHouseholdInfo() {
        return gzfHouseholdInfo;
    }

    public void setGzfHouseholdInfo(GzfHouseholdInfo gzfHouseholdInfo) {
        this.gzfHouseholdInfo = gzfHouseholdInfo;
    }

    public String getAllSelect() {
        return allSelect;
    }

    public void setAllSelect(String allSelect) {
        this.allSelect = allSelect;
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

    @Length(min = 0, max = 64, message = "报修单号长度必须介于 0 和 64 之间")
    public String getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(String repairNum) {
        this.repairNum = repairNum;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Length(min = 0, max = 64, message = "房屋长度必须介于 0 和 64 之间")
    public String getGzfHouseInfoId() {
        return gzfHouseInfoId;
    }

    public void setGzfHouseInfoId(String gzfHouseInfoId) {
        this.gzfHouseInfoId = gzfHouseInfoId;
    }

    @Length(min = 0, max = 64, message = "租户长度必须介于 0 和 64 之间")
    public String getGzfHouseholdInfoId() {
        return gzfHouseholdInfoId;
    }

    public void setGzfHouseholdInfoId(String gzfHouseholdInfoId) {
        this.gzfHouseholdInfoId = gzfHouseholdInfoId;
    }

    @Length(min = 0, max = 64, message = "报修人长度必须介于 0 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 11, message = "报修人电话长度必须介于 0 和 11 之间")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(min = 0, max = 64, message = "故障现象长度必须介于 0 和 64 之间")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Length(min = 0, max = 64, message = "维修状态(维修表id)长度必须介于 0 和 64 之间")
    public String getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(String maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

}