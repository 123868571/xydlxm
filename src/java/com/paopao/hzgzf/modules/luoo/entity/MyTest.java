/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.luoo.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * helloEntity
 * @author luoo
 * @version 2016-07-21
 */
public class MyTest extends DataEntity<MyTest> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String age;		// age
	
	public MyTest() {
		super();
	}

	public MyTest(String id){
		super(id);
	}

	@Length(min=1, max=200, message="name闀垮害蹇呴』浠嬩簬 1 鍜� 200 涔嬮棿")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=11, message="age闀垮害蹇呴』浠嬩簬 1 鍜� 11 涔嬮棿")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
}