/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 维修日志Entity
 * @author Hongjun
 * @version 2016-01-17
 */
public class GzfMaintenanceLog extends DataEntity<GzfMaintenanceLog> {

    private static final long serialVersionUID = 1L;
    private Date              time;                 // 操作时间
    private String            type;                 // 故障类别
    private String            content;              // 故障原因及解决方法
    private String            name;                 // 操作人

    private GzfFaultCategory  gzfFaultCategory;      //类别

    public GzfFaultCategory getGzfFaultCategory() {
        return gzfFaultCategory;
    }

    public void setGzfFaultCategory(GzfFaultCategory gzfFaultCategory) {
        this.gzfFaultCategory = gzfFaultCategory;
    }

    public GzfMaintenanceLog() {
        super();
    }

    public GzfMaintenanceLog(String id) {
        super(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Length(min = 0, max = 64, message = "故障类别长度必须介于 0 和 64 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 64, message = "故障原因及解决方法长度必须介于 0 和 64 之间")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Length(min = 0, max = 64, message = "操作人长度必须介于 0 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}