/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 额度限制Entity
 * @author Hongjun
 * @version 2016-05-05
 */
public class GzfQuota extends DataEntity<GzfQuota> {

    private static final long serialVersionUID = 1L;
    private String            gzfVillageId;         // 小区id
    private String            gzfPalacesId;         // 公租房苑id
    private Integer           maxRent;              // 房租最大几个月
    private Double            maxWater;             // 最大水费数
    private Double            maxElec;              // 最大电费数

    private GzfVillage        gzfVillage;
    private GzfPalaces        gzfPalaces;

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

    public GzfQuota() {
        super();
    }

    public GzfQuota(String id) {
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

    @NotNull(message = "房租最大几个月不能为空")
    public Integer getMaxRent() {
        return maxRent;
    }

    public void setMaxRent(Integer maxRent) {
        this.maxRent = maxRent;
    }

    @NotNull(message = "最大水费数不能为空")
    public Double getMaxWater() {
        return maxWater;
    }

    public void setMaxWater(Double maxWater) {
        this.maxWater = maxWater;
    }

    @NotNull(message = "最大电费数不能为空")
    public Double getMaxElec() {
        return maxElec;
    }

    public void setMaxElec(Double maxElec) {
        this.maxElec = maxElec;
    }

}