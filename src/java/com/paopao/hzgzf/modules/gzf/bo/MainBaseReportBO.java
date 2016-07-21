package com.paopao.hzgzf.modules.gzf.bo;


/**
 * Created by FastLane on 2016-01-10.
 */
public class MainBaseReportBO extends ReportBO {

    public MainBaseReportBO() {
        super();
    }

    public MainBaseReportBO(String key, String title, String value, String className) {
        this.key = key;
        this.title = title;
        this.value = value;
        this.className = className;
    }

    public MainBaseReportBO(String key, String title, String value, String className, String url) {
        this.key = key;
        this.title = title;
        this.value = value;
        this.className = className;
        this.url = url;
    }

    private String className;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }



}
