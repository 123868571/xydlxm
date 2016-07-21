package com.paopao.hzgzf.modules.gzf.bo;

/**
 * Created by FastLane on 2016-02-28.
 */
public class ReportVO {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getxAxis() {
        return xAxis;
    }

    public void setxAxis(String[] xAxis) {
        this.xAxis = xAxis;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    String title = "";



    String name = "";
    String[] xAxis = null;
    String[] data = null;
}
