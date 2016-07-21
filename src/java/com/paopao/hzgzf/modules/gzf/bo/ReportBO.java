package com.paopao.hzgzf.modules.gzf.bo;

import java.io.Serializable;

/**
 * Created by FastLane on 2016-01-14.
 */
public class ReportBO implements Serializable {
    protected String key;

    protected String title;

    protected String value;

    protected float rate;

    public ReportBO(String title, String key, String value) {
        this.title = title;
        this.key = key;
        this.value = value;
    }

    public ReportBO(String title, String key, String value, float rate) {
        this.title = title;
        this.key = key;
        this.value = value;
        this.rate = rate;
    }

    public ReportBO() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
