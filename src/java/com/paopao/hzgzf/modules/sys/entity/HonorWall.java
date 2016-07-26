/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.sys.entity;

import com.paopao.hzgzf.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 荣誉墙Entity
 * @author luoo
 * @version 2016-07-26
 */
public class HonorWall extends DataEntity<HonorWall> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer sort;		// 排序
	private String photo;		// 图片
	private String introduction;		// 简介
	
	public HonorWall() {
		super();
	}

	public HonorWall(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=255, message="简介长度必须介于 0 和 255 之间")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}