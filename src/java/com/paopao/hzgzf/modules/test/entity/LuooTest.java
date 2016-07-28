/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.test.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * testEntity
 * @author luoo
 * @version 2016-07-22
 */
public class LuooTest extends DataEntity<LuooTest> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String age;		// age
	private String remark;		// remark
	
	public LuooTest() {
		super();
	}

	public LuooTest(String id){
		super(id);
	}

	@Length(min=1, max=200, message="name闀垮害蹇呴』浠嬩簬 1 鍜� 200 涔嬮棿")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=2, message="age闀垮害蹇呴』浠嬩簬 1 鍜� 2 涔嬮棿")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=400, message="remark闀垮害蹇呴』浠嬩簬 0 鍜� 400 涔嬮棿")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}