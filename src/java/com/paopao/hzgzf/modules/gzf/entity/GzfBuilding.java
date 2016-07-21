/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

import java.util.List;

/**
 * 楼栋Entity
 * @author Hongjun
 * @version 2015-12-20
 */
public class GzfBuilding extends DataEntity<GzfBuilding> {

    private static final long serialVersionUID = 1L;
    private String            gzfVillageId;         // 小区id
    private String            gzfPalacesId;         // 公租房苑id
    private String            maxBuildNum;          // 最大楼栋数
    private String            maxUnitNum;           // 最大单元数
    private String            maxFloorNum;          // 最大楼层数
    private String            maxRoomNum;           // 最大房间数

    private String            minBuildNum;          // 最小楼栋数
    private String            minUnitNum;           // 最小单元数
    private String            minFloorNum;          // 最小楼层数
    private String            minRoomNum;           // 最小房间数

    private GzfVillage        gzfVillage;
    private GzfPalaces        gzfPalaces;

    private List<String>      gzfVillages;
    private List<String>      gzfPalaceList;

    public String getMinBuildNum() {
        return minBuildNum;
    }

    public void setMinBuildNum(String minBuildNum) {
        this.minBuildNum = minBuildNum;
    }

    public String getMinUnitNum() {
        return minUnitNum;
    }

    public void setMinUnitNum(String minUnitNum) {
        this.minUnitNum = minUnitNum;
    }

    public String getMinFloorNum() {
        return minFloorNum;
    }

    public void setMinFloorNum(String minFloorNum) {
        this.minFloorNum = minFloorNum;
    }

    public String getMinRoomNum() {
        return minRoomNum;
    }

    public void setMinRoomNum(String minRoomNum) {
        this.minRoomNum = minRoomNum;
    }

    public GzfVillage getGzfVillage() {
        return gzfVillage;
    }

    public void setGzfVillage(GzfVillage gzfVillage) {
        this.gzfVillage = gzfVillage;
    }

    public GzfPalaces getGzfPalaces() {
        return gzfPalaces;
    }

    public void setGzfPalaces(GzfPalaces gzfPalaces) {
        this.gzfPalaces = gzfPalaces;
    }

    public GzfBuilding() {
        super();
    }

    public GzfBuilding(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "小区id长度必须介于 1 和 64 之间")
    public String getGzfVillageId() {
        return gzfVillageId;
    }

    public void setGzfVillageId(String gzfVillageId) {
        this.gzfVillageId = gzfVillageId;
    }

    @Length(min = 1, max = 64, message = "公租房苑id长度必须介于 1 和 64 之间")
    public String getGzfPalacesId() {
        return gzfPalacesId;
    }

    public void setGzfPalacesId(String gzfPalacesId) {
        this.gzfPalacesId = gzfPalacesId;
    }

    @Length(min = 1, max = 11, message = "最大楼栋数长度必须介于 1 和 11 之间")
    public String getMaxBuildNum() {
        return maxBuildNum;
    }

    public void setMaxBuildNum(String maxBuildNum) {
        this.maxBuildNum = maxBuildNum;
    }

    @Length(min = 1, max = 11, message = "最大单元数长度必须介于 1 和 11 之间")
    public String getMaxUnitNum() {
        return maxUnitNum;
    }

    public void setMaxUnitNum(String maxUnitNum) {
        this.maxUnitNum = maxUnitNum;
    }

    @Length(min = 1, max = 11, message = "最大楼层数长度必须介于 1 和 11 之间")
    public String getMaxFloorNum() {
        return maxFloorNum;
    }

    public void setMaxFloorNum(String maxFloorNum) {
        this.maxFloorNum = maxFloorNum;
    }

    @Length(min = 1, max = 11, message = "最大房间数长度必须介于 1 和 11 之间")
    public String getMaxRoomNum() {
        return maxRoomNum;
    }

    public void setMaxRoomNum(String maxRoomNum) {
        this.maxRoomNum = maxRoomNum;
    }

    public List<String> getGzfVillages() {
        return gzfVillages;
    }

    public void setGzfVillages(List<String> gzfVillages) {
        this.gzfVillages = gzfVillages;
    }

    public List<String> getGzfPalaceList() {
        return gzfPalaceList;
    }

    public void setGzfPalaceList(List<String> gzfPalaceList) {
        this.gzfPalaceList = gzfPalaceList;
    }
}
