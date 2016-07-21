/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 巡查设备详情Entity
 * @author Hongjun
 * @version 2016-03-15
 */
public class GzfInspectionDetail extends DataEntity<GzfInspectionDetail> {

    private static final long     serialVersionUID = 1L;
    private String                gzfInspectionCategoryId; // 巡查类别
    private String                name;                   // 巡查类目

    private GzfInspectionCategory gzfInspectionCategory;

    public GzfInspectionCategory getGzfInspectionCategory() {
        return gzfInspectionCategory;
    }

    public void setGzfInspectionCategory(GzfInspectionCategory gzfInspectionCategory) {
        this.gzfInspectionCategory = gzfInspectionCategory;
    }

    public GzfInspectionDetail() {
        super();
    }

    public GzfInspectionDetail(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "巡查类别长度必须介于 0 和 64 之间")
    public String getGzfInspectionCategoryId() {
        return gzfInspectionCategoryId;
    }

    public void setGzfInspectionCategoryId(String gzfInspectionCategoryId) {
        this.gzfInspectionCategoryId = gzfInspectionCategoryId;
    }

    @Length(min = 0, max = 64, message = "巡查类目长度必须介于 0 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}