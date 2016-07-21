package com.cc.tson.bean;

/**
 * Created by caohx on 2016/3/8.
 */
public class Response {
    private Long et_num;
    private String et_context;
    private String msg;
    private Integer et_cmd;

    public Integer getEt_cmd() {
        return et_cmd;
    }

    public void setEt_cmd(Integer et_cmd) {
        this.et_cmd = et_cmd;
    }

    public Long getEt_num() {
        return et_num;
    }

    public void setEt_num(Long et_num) {
        this.et_num = et_num;
    }

    public String getEt_context() {
        return et_context;
    }

    public void setEt_context(String et_context) {
        this.et_context = et_context;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
