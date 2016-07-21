package com.cc.tson.bean;

import com.cc.tson.util.JsonUtil;

/**
 * Created by caohx on 2016/3/8.
 */
public class Request {
    private int et_type;
    private String et_num;
    private String et_ip;
    private Integer et_cmd;

    public Integer getEt_cmd(){
        return et_cmd;
    }

    public void setEt_cmd(Integer et_cmd){
        this.et_cmd = et_cmd;
    }

    public int getEt_type() {
        return et_type;
    }

    public void setEt_type(int et_type) {
        this.et_type = et_type;
    }

    public String getEt_num() {
        return et_num;
    }

    public void setEt_num(String et_num) {
        this.et_num = et_num;
    }

    public String getEt_ip() {
        return et_ip;
    }

    public void setEt_ip(String et_ip) {
        this.et_ip = et_ip;
    }

    public static void main(String[] args)throws Exception {
        Request request = new Request();
        request.setEt_ip("123.3.0.12");
        request.setEt_num("123123");
        request.setEt_type(1);
        String jsonStr = JsonUtil.toJson(request);
        System.out.println(JsonUtil.toJson(request));
        System.out.println(JsonUtil.toObject(jsonStr,Request.class));
    }

    @Override
    public String toString() {
        return "Request{" +
                "et_type=" + et_type +
                ", et_num=" + et_num +
                ", et_ip='" + et_ip + '\'' +
                ", et_cmd=" + et_cmd +
                '}';
    }
}
