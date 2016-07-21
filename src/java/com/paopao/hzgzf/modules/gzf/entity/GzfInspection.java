/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 巡查设备Entity
 * @author Hongjun
 * @version 2016-03-15
 */
public class GzfInspection extends DataEntity<GzfInspection> {

    private static final long serialVersionUID = 1L;
    private String            gzfVillageId;           // 小区名

    private String            gzfInspectionCategoryId;

    private GzfVillage        gzfVillage;

    private List<String>      gzfInspectionDetailIds;

    public List<String> getGzfInspectionDetailIds() {
        return gzfInspectionDetailIds;
    }

    public void setGzfInspectionDetailIds(List<String> gzfInspectionDetailIds) {
        this.gzfInspectionDetailIds = gzfInspectionDetailIds;
    }

    public GzfVillage getGzfVillage() {
        return gzfVillage;
    }

    public void setGzfVillage(GzfVillage gzfVillage) {
        this.gzfVillage = gzfVillage;
    }

    public String getGzfInspectionCategoryId() {
        return gzfInspectionCategoryId;
    }

    public void setGzfInspectionCategoryId(String gzfInspectionCategoryId) {
        this.gzfInspectionCategoryId = gzfInspectionCategoryId;
    }

    public GzfInspection() {
        super();
    }

    public GzfInspection(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "小区名长度必须介于 0 和 64 之间")
    public String getGzfVillageId() {
        return gzfVillageId;
    }

    public void setGzfVillageId(String gzfVillageId) {
        this.gzfVillageId = gzfVillageId;
    }

}