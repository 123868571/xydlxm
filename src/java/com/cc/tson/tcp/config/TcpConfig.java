package com.cc.tson.tcp.config;

public class TcpConfig {
    private Integer serverPort;
    private String serverIp;
    private Integer idleSecond;
    private Integer byteBufferSize;
    private Boolean isHeartBeat;

    public Boolean getIsHeartBeat() {
        return isHeartBeat;
    }

    public void setIsHeartBeat(Boolean isHeartBeat) {
        this.isHeartBeat = isHeartBeat;
    }

    public Integer getByteBufferSize() {
        return byteBufferSize;
    }

    public void setByteBufferSize(Integer byteBufferSize) {
        this.byteBufferSize = byteBufferSize;
    }

    public Integer getIdleSecond() {
        return idleSecond;
    }

    public void setIdleSecond(Integer idleSecond) {
        this.idleSecond = idleSecond;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
