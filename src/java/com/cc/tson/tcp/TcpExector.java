package com.cc.tson.tcp;

import com.cc.tson.bean.Request;
import com.cc.tson.util.JsonUtil;

/**
 * Created by caohx on 2016/3/8.
 */
public class TcpExector {
    TcpHolder tcpHolder = new TcpHolder();
    public void send(Request request)throws Exception{
        tcpHolder.write(JsonUtil.toJson(request));
    }
}
