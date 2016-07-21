/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 提醒Entity
 * @author Hongjun
 * @version 2016-01-17
 */
public class GzfRemind extends DataEntity<GzfRemind> {
	
	private static final long serialVersionUID = 1L;
	private String gzfHouseholdInfoId;		// 人员
	private String gzfHousInfoId;		// 房屋
	private String remindtype;		// 没交费项目
	private Date time;		// 提醒时间
	
	private GzfHouseholdInfo gzfHouseholdInfo;
	private GzfHouseInfo gzfHouseInfo;
	
	private String allSelect;
	
	public String getAllSelect() {
		return allSelect;
	}

	public void setAllSelect(String allSelect) {
		this.allSelect = allSelect;
	}

	public GzfHouseholdInfo getGzfHouseholdInfo() {
		return gzfHouseholdInfo;
	}

	public void setGzfHouseholdInfo(GzfHouseholdInfo gzfHouseholdInfo) {
		this.gzfHouseholdInfo = gzfHouseholdInfo;
	}

	public GzfHouseInfo getGzfHouseInfo() {
		return gzfHouseInfo;
	}

	public void setGzfHouseInfo(GzfHouseInfo gzfHouseInfo) {
		this.gzfHouseInfo = gzfHouseInfo;
	}

	public GzfRemind() {
		super();
	}

	public GzfRemind(String id){
		super(id);
	}

	@Length(min=0, max=64, message="人员长度必须介于 0 和 64 之间")
	public String getGzfHouseholdInfoId() {
		return gzfHouseholdInfoId;
	}

	public void setGzfHouseholdInfoId(String gzfHouseholdInfoId) {
		this.gzfHouseholdInfoId = gzfHouseholdInfoId;
	}
	
	@Length(min=0, max=64, message="房屋长度必须介于 0 和 64 之间")
	public String getGzfHousInfoId() {
		return gzfHousInfoId;
	}

	public void setGzfHousInfoId(String gzfHousInfoId) {
		this.gzfHousInfoId = gzfHousInfoId;
	}
	
	@Length(min=0, max=11, message="没交费项目长度必须介于 0 和 11 之间")
	public String getRemindtype() {
		return remindtype;
	}

	public void setRemindtype(String remindtype) {
		this.remindtype = remindtype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}