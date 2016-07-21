/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 水表Entity
 * @author Hongjun
 * @version 2016-01-17
 */
public class GzfWaterMeter extends DataEntity<GzfWaterMeter> {

    private static final long serialVersionUID = 1L;
    private String            gzfHouseholdInfoId;   // 人员
    private String            gzfHouseInfoId;       // 房间
    private Double            num; 					// 度数
    private Date              time;                 // 抄表时间

    private String            searchName;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    private GzfHousePerson gzfHousePerson;

    public GzfHousePerson getGzfHousePerson() {
        return gzfHousePerson;
    }

    public void setGzfHousePerson(GzfHousePerson gzfHousePerson) {
        this.gzfHousePerson = gzfHousePerson;
    }

    public GzfWaterMeter() {
        super();
    }

    public GzfWaterMeter(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "房间长度必须介于 0 和 64 之间")
    public String getGzfHouseholdInfoId() {
        return gzfHouseholdInfoId;
    }

    public void setGzfHouseholdInfoId(String gzfHouseholdInfoId) {
        this.gzfHouseholdInfoId = gzfHouseholdInfoId;
    }

    @Length(min = 0, max = 64, message = "人员长度必须介于 0 和 64 之间")
    public String getGzfHouseInfoId() {
        return gzfHouseInfoId;
    }

    public void setGzfHouseInfoId(String gzfHouseInfoId) {
        this.gzfHouseInfoId = gzfHouseInfoId;
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

}