/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 维修关系管理Entity
 * @author Hongjun
 * @version 2016-02-21
 */
public class GzfRepairManagerProject extends DataEntity<GzfRepairManagerProject> {

    private static final long   serialVersionUID = 1L;
    private Integer             num;                  // 报修数量
    private String              gzfRepairManagerId;   // 报修id
    private String              gzfRepairProjectId;   // 项目id

    private GzfRepairProject    gzfRepairProject;

    private GzfRepairManagement gzfRepairManagement;

    public GzfRepairProject getGzfRepairProject() {
        return gzfRepairProject;
    }

    public void setGzfRepairProject(GzfRepairProject gzfRepairProject) {
        this.gzfRepairProject = gzfRepairProject;
    }

    public GzfRepairManagement getGzfRepairManagement() {
        return gzfRepairManagement;
    }

    public void setGzfRepairManagement(GzfRepairManagement gzfRepairManagement) {
        this.gzfRepairManagement = gzfRepairManagement;
    }

    public GzfRepairManagerProject() {
        super();
    }

    public GzfRepairManagerProject(String id) {
        super(id);
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Length(min = 0, max = 64, message = "报修id长度必须介于 0 和 64 之间")
    public String getGzfRepairManagerId() {
        return gzfRepairManagerId;
    }

    public void setGzfRepairManagerId(String gzfRepairManagerId) {
        this.gzfRepairManagerId = gzfRepairManagerId;
    }

    @Length(min = 0, max = 64, message = "项目id长度必须介于 0 和 64 之间")
    public String getGzfRepairProjectId() {
        return gzfRepairProjectId;
    }

    public void setGzfRepairProjectId(String gzfRepairProjectId) {
        this.gzfRepairProjectId = gzfRepairProjectId;
    }

}