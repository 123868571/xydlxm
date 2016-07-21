/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 维修历史Entity
 * @author Hongjun
 * @version 2016-01-14
 */
public class GzfRepair extends DataEntity<GzfRepair> {
	
	private static final long serialVersionUID = 1L;
	private String repairType;		// 维修状态
	private String repairBy;		// 维修人
	private String phone;		// 维修人电话
	private String repairProject;		// 维修项目
	private String repairNum;		// 维修数量
	private String content;		// 维修内容
	private Date repairDate;		// 维修时间
	private String gzfHouseInfoId;		// 房屋id
	
	public GzfRepair() {
		super();
	}

	public GzfRepair(String id){
		super(id);
	}

	@Length(min=0, max=64, message="维修状态长度必须介于 0 和 64 之间")
	public String getRepairType() {
		return repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	
	@Length(min=0, max=64, message="维修人长度必须介于 0 和 64 之间")
	public String getRepairBy() {
		return repairBy;
	}

	public void setRepairBy(String repairBy) {
		this.repairBy = repairBy;
	}
	
	@Length(min=0, max=255, message="维修人电话长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="维修项目长度必须介于 0 和 64 之间")
	public String getRepairProject() {
		return repairProject;
	}

	public void setRepairProject(String repairProject) {
		this.repairProject = repairProject;
	}
	
	@Length(min=0, max=255, message="维修数量长度必须介于 0 和 255 之间")
	public String getRepairNum() {
		return repairNum;
	}

	public void setRepairNum(String repairNum) {
		this.repairNum = repairNum;
	}
	
	@Length(min=0, max=64, message="维修内容长度必须介于 0 和 64 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	
	@Length(min=0, max=64, message="房屋id长度必须介于 0 和 64 之间")
	public String getGzfHouseInfoId() {
		return gzfHouseInfoId;
	}

	public void setGzfHouseInfoId(String gzfHouseInfoId) {
		this.gzfHouseInfoId = gzfHouseInfoId;
	}
	
}