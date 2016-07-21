/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 通知发布Entity
 * @author Hongjun
 * @version 2016-05-05
 */
public class GzfNotice extends DataEntity<GzfNotice> {

    private static final long serialVersionUID = 1L;
    private String            gzfVillageId;         // 小区id
    private String            gzfPalacesId;         // 公租房苑id
    private String            title;                // 通知标题
    private String            content;              // 通知内容
    private String            person;               // 联系人
    private String            phone;                // 联系电话
    private String            company;              // 通知单位
    private String            noticeDate;           // 通知时间
    private GzfVillage        gzfVillage;
    private GzfPalaces        gzfPalaces;

    public GzfVillage getGzfVillage() {
        return gzfVillage;
    }

    public void setGzfVillage(GzfVillage gzfVillage) {
        this.gzfVillage = gzfVillage;
    }

    public GzfPalaces getGzfPalaces() {
        return gzfPalaces;
    }

    public void setGzfPalaces(GzfPalaces gzfPalaces) {
        this.gzfPalaces = gzfPalaces;
    }

    public GzfNotice() {
        super();
    }

    public GzfNotice(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "小区id长度必须介于 1 和 64 之间")
    public String getGzfVillageId() {
        return gzfVillageId;
    }

    public void setGzfVillageId(String gzfVillageId) {
        this.gzfVillageId = gzfVillageId;
    }

    @Length(min = 1, max = 64, message = "公租房苑id长度必须介于 1 和 64 之间")
    public String getGzfPalacesId() {
        return gzfPalacesId;
    }

    public void setGzfPalacesId(String gzfPalacesId) {
        this.gzfPalacesId = gzfPalacesId;
    }

    @Length(min = 1, max = 64, message = "通知标题长度必须介于 1 和 64 之间")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Length(min = 1, max = 64, message = "联系人长度必须介于 1 和 64 之间")
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Length(min = 1, max = 64, message = "联系电话长度必须介于 1 和 64 之间")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(min = 1, max = 64, message = "通知单位长度必须介于 1 和 64 之间")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Length(min = 1, max = 64, message = "通知时间长度必须介于 1 和 64 之间")
    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

}