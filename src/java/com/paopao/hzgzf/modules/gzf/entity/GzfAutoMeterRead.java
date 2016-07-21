/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 自动抄表Entity
 * @author songyahe
 * @version 2016-03-09
 */
public class GzfAutoMeterRead extends DataEntity<GzfAutoMeterRead> {
	
	private static final long serialVersionUID = 1L;
	private String meterIp;		// 表的ip
	private String meterNo;		// 表号
	private Double num;		// 表数
	private Date time;		// 抄表时间
	private String meterType;		// 1-水表 2-电表 3-天燃气
	private String flag;		// 状态 U-正常 E-处理异常
	
	public GzfAutoMeterRead() {
		super();
	}

	public GzfAutoMeterRead(String id){
		super(id);
	}

	@Length(min=1, max=64, message="表的ip长度必须介于 1 和 64 之间")
	public String getMeterIp() {
		return meterIp;
	}

	public void setMeterIp(String meterIp) {
		this.meterIp = meterIp;
	}
	
	@Length(min=1, max=64, message="表号长度必须介于 1 和 64 之间")
	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	
	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@Length(min=1, max=8, message="1-水表 2-电表 3-天燃气长度必须介于 1 和 8 之间")
	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	
	@Length(min=0, max=1, message="状态 U-正常 E-处理异常长度必须介于 0 和 1 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}