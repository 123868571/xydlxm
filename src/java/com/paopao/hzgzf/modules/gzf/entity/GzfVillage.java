/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.modules.sys.entity.Area;

/**
 * 小区Entity
 * @author Hongjun
 * @version 2015-12-20
 */
public class GzfVillage extends DataEntity<GzfVillage> {

    private static final long serialVersionUID = 1L;
    private String            name;                 // 名称
    private String            nextLevel;            // 下级层级
    private Date              buildYear;            // 建筑年代
    private String            belongCompany;        // 归属单位
    private String            propertyCompany;      // 物业单位
    private String            constructCompany;     // 施工单位
    private String            location;             // 坐落位置
    private String            landGrade;            // 土地等级
    private String            landNo;               // 地号
    private String            rightNumber;          // 产权证号
    private String            rightCompany;         // 产权单位
    private Integer           totalTenant;          // 可配总租户数

    private String            checkPalaces;         //选择苑

    private String            palaces;              //选择苑名称

    private String            building;             //楼栋名称

    private Area              area;                 // 归属区域

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getCheckPalaces() {
        return checkPalaces;
    }

    public void setCheckPalaces(String checkPalaces) {
        this.checkPalaces = checkPalaces;
    }

    public String getPalaces() {
        return palaces;
    }

    public void setPalaces(String palaces) {
        this.palaces = palaces;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public GzfVillage() {
        super();
    }

    public GzfVillage(String id) {
        super(id);
    }

    @Length(min = 1, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 64, message = "下级层级长度必须介于 1 和 64 之间")
    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "建筑年代不能为空")
    public Date getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Date buildYear) {
        this.buildYear = buildYear;
    }

    @Length(min = 1, max = 255, message = "归属单位长度必须介于 1 和 255 之间")
    public String getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(String belongCompany) {
        this.belongCompany = belongCompany;
    }

    @Length(min = 1, max = 255, message = "物业单位长度必须介于 1 和 255 之间")
    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    @Length(min = 1, max = 255, message = "施工单位长度必须介于 1 和 255 之间")
    public String getConstructCompany() {
        return constructCompany;
    }

    public void setConstructCompany(String constructCompany) {
        this.constructCompany = constructCompany;
    }

    @Length(min = 1, max = 255, message = "坐落位置长度必须介于 1 和 255 之间")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Length(min = 1, max = 255, message = "土地等级长度必须介于 1 和 255 之间")
    public String getLandGrade() {
        return landGrade;
    }

    public void setLandGrade(String landGrade) {
        this.landGrade = landGrade;
    }

    @Length(min = 1, max = 255, message = "地号长度必须介于 1 和 255 之间")
    public String getLandNo() {
        return landNo;
    }

    public void setLandNo(String landNo) {
        this.landNo = landNo;
    }

    @Length(min = 1, max = 255, message = "产权证号长度必须介于 1 和 255 之间")
    public String getRightNumber() {
        return rightNumber;
    }

    public void setRightNumber(String rightNumber) {
        this.rightNumber = rightNumber;
    }

    @Length(min = 1, max = 255, message = "产权单位长度必须介于 1 和 255 之间")
    public String getRightCompany() {
        return rightCompany;
    }

    public void setRightCompany(String rightCompany) {
        this.rightCompany = rightCompany;
    }

    @NotNull(message = "可配总租户数不能为空")
    public Integer getTotalTenant() {
        return totalTenant;
    }

    public void setTotalTenant(Integer totalTenant) {
        this.totalTenant = totalTenant;
    }

}