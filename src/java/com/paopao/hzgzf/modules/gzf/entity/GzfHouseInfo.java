/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.common.utils.excel.annotation.ExcelField;

/**
 * 房屋信息Entity
 * @author Hongjun
 * @version 2016-01-02
 */
public class GzfHouseInfo extends DataEntity<GzfHouseInfo> {

    private static final long serialVersionUID = 1L;
    private String            gzfPalacesId;         // 公租房苑id
    private Integer           buildNum;             // 楼栋
    private Integer           unit;                 // 单元
    private String            room;                 // 房间号
    private Double            innerArea;            // 套内面积
    private Double            useArea;              // 使用面积
    private Integer           houseType;            // 房屋类型
    private Integer           houseStat;            // 房屋状态
    private String            photo;                // 房屋缩略图
    private String            gzfHousePropertyId;   // 房屋属性

    private String            houseTypeStr;
    private String            houseProperty;

    private GzfPalaces        gzfPalaces;

    private GzfHousePerson    gzfHousePerson;

    private String            houseAll;

    private String            allSelect;

    private Date              startTime;

    private Date              endTime;

    private String            water;
    private String            waterIp;
    private String            elec;
    private String            elecIp;

    private String            villageName;
    private String            palacesName;

    private int               code;                 //cs端对应
    private int               sync;

    private Integer waterStatus;
    private Integer elecStatus;
    private Integer nextStep;//下次分房
    private Integer personal;//人员属性
    

    public Integer getNextStep() {
        return nextStep;
    }

    public void setNextStep(Integer nextStep) {
        this.nextStep = nextStep;
    }

    public Integer getPersonal() {
        return personal;
    }

    public void setPersonal(Integer personal) {
        this.personal = personal;
    }

    public Integer getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(Integer waterStatus) {
        this.waterStatus = waterStatus;
    }

    public Integer getElecStatus() {
        return elecStatus;
    }

    public void setElecStatus(Integer elecStatus) {
        this.elecStatus = elecStatus;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getPalacesName() {
        return palacesName;
    }

    public void setPalacesName(String palacesName) {
        this.palacesName = palacesName;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getWaterIp() {
        return waterIp;
    }

    public void setWaterIp(String waterIp) {
        this.waterIp = waterIp;
    }

    public String getElec() {
        return elec;
    }

    public void setElec(String elec) {
        this.elec = elec;
    }

    public String getElecIp() {
        return elecIp;
    }

    public void setElecIp(String elecIp) {
        this.elecIp = elecIp;
    }

    public String getHouseTypeStr() {
        return houseTypeStr;
    }

    public void setHouseTypeStr(String houseTypeStr) {
        this.houseTypeStr = houseTypeStr;
    }

    public String getHouseProperty() {
        return houseProperty;
    }

    public void setHouseProperty(String houseProperty) {
        this.houseProperty = houseProperty;
    }

    public String getAllSelect() {
        return allSelect;
    }

    public void setAllSelect(String allSelect) {
        this.allSelect = allSelect;
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

    private GzfPaymentStandard gzfPaymentStandard;

    public GzfPaymentStandard getGzfPaymentStandard() {
        return gzfPaymentStandard;
    }

    public void setGzfPaymentStandard(GzfPaymentStandard gzfPaymentStandard) {
        this.gzfPaymentStandard = gzfPaymentStandard;
    }

    public String getHouseAll() {
        return houseAll;
    }

    public void setHouseAll(String houseAll) {
        this.houseAll = houseAll;
    }

    public GzfHousePerson getGzfHousePerson() {
        return gzfHousePerson;
    }

    public void setGzfHousePerson(GzfHousePerson gzfHousePerson) {
        this.gzfHousePerson = gzfHousePerson;
    }

    private GzfHouseProperty gzfHouseProperty;

    @JsonIgnore
    @ExcelField(title = "苑名称", align = 2, sort = 1)
    public GzfPalaces getGzfPalaces() {
        return gzfPalaces;
    }

    public void setGzfPalaces(GzfPalaces gzfPalaces) {
        this.gzfPalaces = gzfPalaces;
    }

    @ExcelField(title = "房屋属性", align = 2, sort = 20)
    public GzfHouseProperty getGzfHouseProperty() {
        return gzfHouseProperty;
    }

    public void setGzfHouseProperty(GzfHouseProperty gzfHouseProperty) {
        this.gzfHouseProperty = gzfHouseProperty;
    }

    public GzfHouseInfo() {
        super();
    }

    public GzfHouseInfo(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "公租房苑id长度必须介于 1 和 64 之间")
    public String getGzfPalacesId() {
        return gzfPalacesId;
    }

    public void setGzfPalacesId(String gzfPalacesId) {
        this.gzfPalacesId = gzfPalacesId;
    }

    @NotNull(message = "楼栋不能为空")
    @ExcelField(title = "楼栋", align = 2, sort = 2)
    public Integer getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(Integer buildNum) {
        this.buildNum = buildNum;
    }

    @NotNull(message = "单元不能为空")
    @ExcelField(title = "单元", align = 2, sort = 3)
    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Length(min = 1, max = 32, message = "房间号长度必须介于 1 和 32 之间")
    @ExcelField(title = "房间号", align = 2, sort = 4)
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @NotNull(message = "套内面积不能为空")
    @ExcelField(title = "套内面积", align = 2, sort = 5)
    public Double getInnerArea() {
        return innerArea;
    }

    public void setInnerArea(Double innerArea) {
        this.innerArea = innerArea;
    }

    @NotNull(message = "使用面积不能为空")
    @ExcelField(title = "使用面积", align = 2, sort = 6)
    public Double getUseArea() {
        return useArea;
    }

    public void setUseArea(Double useArea) {
        this.useArea = useArea;
    }

    @NotNull(message = "房屋类型不能为空")
    @ExcelField(title = "房屋类型", align = 2, sort = 7, dictType = "house_type")
    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    @NotNull(message = "房屋状态不能为空")
    @ExcelField(title = "房屋状态", align = 2, sort = 8, dictType = "house_status")
    public Integer getHouseStat() {
        return houseStat;
    }

    public void setHouseStat(Integer houseStat) {
        this.houseStat = houseStat;
    }

    @Length(min = 0, max = 255, message = "房屋缩略图长度必须介于 0 和 255 之间")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Length(min = 1, max = 64, message = "房屋属性长度必须介于 1 和 64 之间")
    public String getGzfHousePropertyId() {
        return gzfHousePropertyId;
    }

    public void setGzfHousePropertyId(String gzfHousePropertyId) {
        this.gzfHousePropertyId = gzfHousePropertyId;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    private int cardNum;

}
