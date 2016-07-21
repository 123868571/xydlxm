/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * GPSEntity
 * @author pjs
 * @version 2016-05-28
 */
public class ComGps extends DataEntity<ComGps> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String gpskey;		// gpskey
	private String gpstype;		// gpstype
	private String carnum;		// carnum
	private Date opendate;		// opendate
	
	public ComGps() {
		super();
	}

	public ComGps(String id){
		super(id);
	}

	@Length(min=1, max=100, message="name长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="gpskey长度必须介于 0 和 64 之间")
	public String getGpskey() {
		return gpskey;
	}

	public void setGpskey(String gpskey) {
		this.gpskey = gpskey;
	}
	
	@Length(min=1, max=10, message="gpstype长度必须介于 1 和 10 之间")
	public String getGpstype() {
		return gpstype;
	}

	public void setGpstype(String gpstype) {
		this.gpstype = gpstype;
	}
	
	@Length(min=0, max=64, message="carnum长度必须介于 0 和 64 之间")
	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="opendate不能为空")
	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	
}