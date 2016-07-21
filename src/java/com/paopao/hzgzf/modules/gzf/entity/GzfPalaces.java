/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 苑Entity
 * @author Hongjun
 * @version 2015-12-20
 */
public class GzfPalaces extends DataEntity<GzfPalaces> {

    private static final long serialVersionUID = 1L;
    private String            gzfVillageId;         // 小区id
    private String            name;                 // 名称
    private Integer           maxBuildNum;          // 最大楼栋数
    private Integer           maxUnitNum;           // 最大单元数
    private Integer           maxFloorNum;          // 最大楼层数
    private Integer           maxRoomNum;           // 最大房间数

    private GzfVillage        gzfVillage;

    public GzfVillage getGzfVillage() {
        return gzfVillage;
    }

    public void setGzfVillage(GzfVillage gzfVillage) {
        this.gzfVillage = gzfVillage;
    }

    public GzfPalaces() {
        super();
    }

    public GzfPalaces(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "小区id长度必须介于 1 和 64 之间")
    public String getGzfVillageId() {
        return gzfVillageId;
    }

    public void setGzfVillageId(String gzfVillageId) {
        this.gzfVillageId = gzfVillageId;
    }

    @Length(min = 1, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "最大楼栋数不能为空")
    public Integer getMaxBuildNum() {
        return maxBuildNum;
    }

    public void setMaxBuildNum(Integer maxBuildNum) {
        this.maxBuildNum = maxBuildNum;
    }

    @NotNull(message = "最大单元数不能为空")
    public Integer getMaxUnitNum() {
        return maxUnitNum;
    }

    public void setMaxUnitNum(Integer maxUnitNum) {
        this.maxUnitNum = maxUnitNum;
    }

    @NotNull(message = "最大楼层数不能为空")
    public Integer getMaxFloorNum() {
        return maxFloorNum;
    }

    public void setMaxFloorNum(Integer maxFloorNum) {
        this.maxFloorNum = maxFloorNum;
    }

    @NotNull(message = "最大房间数不能为空")
    public Integer getMaxRoomNum() {
        return maxRoomNum;
    }

    public void setMaxRoomNum(Integer maxRoomNum) {
        this.maxRoomNum = maxRoomNum;
    }

    @Override
    public String toString() {
        return name;
    }

}