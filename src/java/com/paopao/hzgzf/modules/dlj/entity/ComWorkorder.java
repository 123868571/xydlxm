/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 工单分配表Entity
 * @author pjs
 * @version 2016-06-02
 */
public class ComWorkorder extends DataEntity<ComWorkorder> {
	
	private static final long serialVersionUID = 1L;
	private String staffids;		// 员工
	private String gpsids;		// GPS
	private String code;		// code
	private String wotype;		// 工单状态
	private String ordercontent;		// 工作任务
	private String orderwarning;		// 注意事项
	private Date startdate;		// startdate
	private String starttime;		// starttime
	private Date enddate;		// enddate
	private String endtime;		// endtime
	
	public ComWorkorder() {
		super();
	}

	public ComWorkorder(String id){
		super(id);
	}

	@Length(min=1, max=500, message="员工长度必须介于 1 和 500 之间")
	public String getStaffids() {
		return staffids;
	}

	public void setStaffids(String staffids) {
		this.staffids = staffids;
	}
	
	@Length(min=0, max=500, message="GPS长度必须介于 0 和 500 之间")
	public String getGpsids() {
		return gpsids;
	}

	public void setGpsids(String gpsids) {
		this.gpsids = gpsids;
	}
	
	@Length(min=0, max=50, message="code长度必须介于 0 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=10, message="工单状态长度必须介于 1 和 10 之间")
	public String getWotype() {
		return wotype;
	}

	public void setWotype(String wotype) {
		this.wotype = wotype;
	}
	
	@Length(min=0, max=255, message="工作任务长度必须介于 0 和 255 之间")
	public String getOrdercontent() {
		return ordercontent;
	}

	public void setOrdercontent(String ordercontent) {
		this.ordercontent = ordercontent;
	}
	
	@Length(min=0, max=255, message="注意事项长度必须介于 0 和 255 之间")
	public String getOrderwarning() {
		return orderwarning;
	}

	public void setOrderwarning(String orderwarning) {
		this.orderwarning = orderwarning;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	@Length(min=0, max=50, message="starttime长度必须介于 0 和 50 之间")
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	@Length(min=0, max=50, message="endtime长度必须介于 0 和 50 之间")
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}