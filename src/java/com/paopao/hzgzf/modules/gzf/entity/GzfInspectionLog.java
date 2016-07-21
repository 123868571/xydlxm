/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 巡查日志Entity
 * @author Hongjun
 * @version 2016-02-28
 */
public class GzfInspectionLog extends DataEntity<GzfInspectionLog> {

    private static final long serialVersionUID = 1L;
    private Date              time;                 // 巡查日期
    private String            gzfVillageId;         // 小区名
    private String            unit;                 // 巡查单位
    private String            inspectionName;       // 巡查人
    private String            name;                 // 项目维护负责人
    private String            inspectionId;         // 巡查编号
    private String            inspectionPhone;      // 巡查人电话
    private String            phone;                // 负责人电话
    private Integer           computerStatus;       // 服务器运行状态
    private Integer           computerC;            // 服务器C盘空间
    private Integer           computerLogbak;       // 服务器日志备份拷贝
    private String            computerFeedback;     // 问题反馈
    private Integer           roomMachine;          // 单元门口机
    private Integer           roomControl;          // 各单元梯控使用情况
    private Integer           roomIndoor;           // 室内机
    private String            roomFeedback;         // 问题反馈
    private String            computerStatusRemarks; // 服务器运行状态备注
    private String            computerCRemarks;     // 服务器C盘空间备注
    private String            computerLogbakRemarks; // 服务器日志备份拷贝备注
    private String            roomMachineRemarks;   // 单元门口机备注
    private String            roomControlRemarks;   // 各单元梯控使用情况备注
    private String            roomIndoorRemarks;    // 室内机备注

    private GzfVillage        gzfVillage;

    private String            propertyCompany;

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public GzfVillage getGzfVillage() {
        return gzfVillage;
    }

    public void setGzfVillage(GzfVillage gzfVillage) {
        this.gzfVillage = gzfVillage;
    }

    public GzfInspectionLog() {
        super();
    }

    public GzfInspectionLog(String id) {
        super(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Length(min = 0, max = 64, message = "小区名长度必须介于 0 和 64 之间")
    public String getGzfVillageId() {
        return gzfVillageId;
    }

    public void setGzfVillageId(String gzfVillageId) {
        this.gzfVillageId = gzfVillageId;
    }

    @Length(min = 0, max = 64, message = "巡查单位长度必须介于 0 和 64 之间")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Length(min = 0, max = 64, message = "巡查人长度必须介于 0 和 64 之间")
    public String getInspectionName() {
        return inspectionName;
    }

    public void setInspectionName(String inspectionName) {
        this.inspectionName = inspectionName;
    }

    @Length(min = 0, max = 255, message = "项目维护负责人长度必须介于 0 和 255 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 64, message = "巡查编号长度必须介于 0 和 64 之间")
    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    @Length(min = 0, max = 64, message = "巡查人电话长度必须介于 0 和 64 之间")
    public String getInspectionPhone() {
        return inspectionPhone;
    }

    public void setInspectionPhone(String inspectionPhone) {
        this.inspectionPhone = inspectionPhone;
    }

    @Length(min = 0, max = 64, message = "负责人电话长度必须介于 0 和 64 之间")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getComputerStatus() {
        return computerStatus;
    }

    public void setComputerStatus(Integer computerStatus) {
        this.computerStatus = computerStatus;
    }

    public Integer getComputerC() {
        return computerC;
    }

    public void setComputerC(Integer computerC) {
        this.computerC = computerC;
    }

    public Integer getComputerLogbak() {
        return computerLogbak;
    }

    public void setComputerLogbak(Integer computerLogbak) {
        this.computerLogbak = computerLogbak;
    }

    public String getComputerFeedback() {
        return computerFeedback;
    }

    public void setComputerFeedback(String computerFeedback) {
        this.computerFeedback = computerFeedback;
    }

    public Integer getRoomMachine() {
        return roomMachine;
    }

    public void setRoomMachine(Integer roomMachine) {
        this.roomMachine = roomMachine;
    }

    public Integer getRoomControl() {
        return roomControl;
    }

    public void setRoomControl(Integer roomControl) {
        this.roomControl = roomControl;
    }

    public Integer getRoomIndoor() {
        return roomIndoor;
    }

    public void setRoomIndoor(Integer roomIndoor) {
        this.roomIndoor = roomIndoor;
    }

    public String getRoomFeedback() {
        return roomFeedback;
    }

    public void setRoomFeedback(String roomFeedback) {
        this.roomFeedback = roomFeedback;
    }

    @Length(min = 0, max = 255, message = "服务器运行状态备注长度必须介于 0 和 255 之间")
    public String getComputerStatusRemarks() {
        return computerStatusRemarks;
    }

    public void setComputerStatusRemarks(String computerStatusRemarks) {
        this.computerStatusRemarks = computerStatusRemarks;
    }

    @Length(min = 0, max = 255, message = "服务器C盘空间备注长度必须介于 0 和 255 之间")
    public String getComputerCRemarks() {
        return computerCRemarks;
    }

    public void setComputerCRemarks(String computerCRemarks) {
        this.computerCRemarks = computerCRemarks;
    }

    @Length(min = 0, max = 255, message = "服务器日志备份拷贝备注长度必须介于 0 和 255 之间")
    public String getComputerLogbakRemarks() {
        return computerLogbakRemarks;
    }

    public void setComputerLogbakRemarks(String computerLogbakRemarks) {
        this.computerLogbakRemarks = computerLogbakRemarks;
    }

    @Length(min = 0, max = 255, message = "单元门口机备注长度必须介于 0 和 255 之间")
    public String getRoomMachineRemarks() {
        return roomMachineRemarks;
    }

    public void setRoomMachineRemarks(String roomMachineRemarks) {
        this.roomMachineRemarks = roomMachineRemarks;
    }

    @Length(min = 0, max = 255, message = "各单元梯控使用情况备注长度必须介于 0 和 255 之间")
    public String getRoomControlRemarks() {
        return roomControlRemarks;
    }

    public void setRoomControlRemarks(String roomControlRemarks) {
        this.roomControlRemarks = roomControlRemarks;
    }

    @Length(min = 0, max = 255, message = "室内机备注长度必须介于 0 和 255 之间")
    public String getRoomIndoorRemarks() {
        return roomIndoorRemarks;
    }

    public void setRoomIndoorRemarks(String roomIndoorRemarks) {
        this.roomIndoorRemarks = roomIndoorRemarks;
    }

}