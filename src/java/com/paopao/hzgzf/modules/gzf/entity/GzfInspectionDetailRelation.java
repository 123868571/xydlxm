/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 巡查设备详情Entity
 * @author Hongjun
 * @version 2016-03-16
 */
public class GzfInspectionDetailRelation extends DataEntity<GzfInspectionDetailRelation> {
	
	private static final long serialVersionUID = 1L;
	private String gzfInspectionId;		// 巡查编号
	private String gzfInspectionDetailId;		// 巡查详细编号
	
	public GzfInspectionDetailRelation() {
		super();
	}

	public GzfInspectionDetailRelation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="巡查编号长度必须介于 0 和 64 之间")
	public String getGzfInspectionId() {
		return gzfInspectionId;
	}

	public void setGzfInspectionId(String gzfInspectionId) {
		this.gzfInspectionId = gzfInspectionId;
	}
	
	@Length(min=0, max=64, message="巡查详细编号长度必须介于 0 和 64 之间")
	public String getGzfInspectionDetailId() {
		return gzfInspectionDetailId;
	}

	public void setGzfInspectionDetailId(String gzfInspectionDetailId) {
		this.gzfInspectionDetailId = gzfInspectionDetailId;
	}
	
}