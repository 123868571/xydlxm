/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import org.hibernate.validator.constraints.Length;
import com.paopao.hzgzf.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.common.utils.excel.annotation.ExcelField;

/**
 * 员工基础信息Entity
 * @author panjs
 * @version 2016-05-27
 */
public class ComStaff extends DataEntity<ComStaff> {
	
	private static final long serialVersionUID = 1L;
	private Office company;	// 归属公司
	private Office office;	// 归属部门
	private String name;		// 员工姓名
	private String loginIp;		// login_ip
	private Date loginDate;		// login_date
	private String loginFlag;		// login_flag
	private String no;
	private String phone;		// 手机
	private String sex;		// 性别
	private Date birthday;		// 出生年月
	private String grade;		// 职称
	private String edu;		// 学历
	private String polity;		// 政治面貌
	private String worktype;		// 用工信息
	private String station;		// 岗位
	private String photo;		// 用户头像
	
	public ComStaff() {
		super();
	}

	public ComStaff(String id){
		super(id);
	}

	@Length(min=1, max=50, message="员工姓名长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="login_ip长度必须介于 0 和 100 之间")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	

	@JsonIgnore
	@NotNull(message="归属公司不能为空")
	@ExcelField(title="归属公司", align=2, sort=20)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Length(min=0, max=64, message="login_flag长度必须介于 0 和 64 之间")
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	
	@Length(min=0, max=10, message="性别长度必须介于 0 和 10 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=10, message="职称长度必须介于 0 和 10 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=10, message="学历长度必须介于 0 和 10 之间")
	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}
	
	@Length(min=0, max=10, message="政治面貌长度必须介于 0 和 10 之间")
	public String getPolity() {
		return polity;
	}

	public void setPolity(String polity) {
		this.polity = polity;
	}
	
	@Length(min=0, max=10, message="用工信息长度必须介于 0 和 10 之间")
	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	
	@Length(min=0, max=10, message="岗位长度必须介于 0 和 10 之间")
	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
	
	@Length(min=0, max=1000, message="用户头像长度必须介于 0 和 1000 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=1, max=100, message="工号长度必须介于 1 和 100 之间")
	@ExcelField(title="工号", align=2, sort=45)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	@Length(min=0, max=200, message="电话长度必须介于 1 和 200 之间")
	@ExcelField(title="电话", align=2, sort=60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}