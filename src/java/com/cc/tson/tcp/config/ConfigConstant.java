package com.cc.tson.tcp.config;

/**
 * 查询zk配置常量信息
 */
public enum ConfigConstant {
    serverIp("server.ip"),
    serverPort("port"),
    byteBufferSize("max.message.size"),
    idleSecond("heartbeat.second"),
    isHeartBeat("is.heartbeat");

    private String key;

    public String getKey(){
        return this.key;
    }

    private ConfigConstant(String key){
        this.key = key;
    }


}
