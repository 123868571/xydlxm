package com.cc.tson.tcp.test;

import com.cc.tson.bean.Request;
import com.cc.tson.bean.Response;
import com.cc.tson.tcp.ResultCallBack;
import com.cc.tson.tcp.TcpHolder;
import com.cc.tson.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by caohx on 2016/3/13.
 */
public class TestTcp {
    public static void main(String[] args) throws Exception{
        TcpHolder tcpHolder = new TcpHolder();
        tcpHolder.setResponseCallBack(new ResultCallBack() {
            public void executorResult(Response response) {
                try {
                    System.out.println("result json :" + JsonUtil.toJson(response));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        tcpHolder.start();
        for (;;) {
            try {
                Thread.sleep(1000);

                Request t  = new Request();
                t.setEt_type(1);
                t.setEt_num("2342");
                t.setEt_cmd(1);
                t.setEt_ip("127.0.0.1");
                System.out.println("send msg:" + JsonUtil.toJson(t));
                    tcpHolder.write(JsonUtil.toJson(t));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }


}
