/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 房屋属性Entity
 * @author Hongjun
 * @version 2016-01-02
 */
public class GzfHouseProperty extends DataEntity<GzfHouseProperty> {

    private static final long serialVersionUID = 1L;
    private String            name;                 // 名称

    public GzfHouseProperty() {
        super();
    }

    public GzfHouseProperty(String id) {
        super(id);
    }

    @Length(min = 1, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}