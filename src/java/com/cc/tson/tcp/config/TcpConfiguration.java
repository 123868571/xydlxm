package com.cc.tson.tcp.config;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 查询tcp配置信息
 */
public class TcpConfiguration {
    public static final Logger logger = LoggerFactory.getLogger(TcpConfiguration.class);
    private static TcpConfiguration config = new TcpConfiguration();
    private volatile Properties prop;

    private TcpConfiguration(){
        prop = load();
        startReload();

    }

    public Properties load(){
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = TcpConfiguration.class.getResourceAsStream("/tcp.properties");
            if(is == null){
                is = TcpConfiguration.class.getResourceAsStream("/conf/tcp.properties");
            }
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException igore) {
                }
            }
        }
        return prop;
    }

    private void startReload() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Properties properties = load();
                    prop = properties;
                } catch (Exception ignore) {

                }
            }
        }, 5L, TimeUnit.SECONDS);
    }


    public static TcpConfig getTcpConfig(){
        TcpConfig tcpConfig = new TcpConfig();
        String ip = getConfig(ConfigConstant.serverIp.getKey());
        if(StringUtils.isEmpty(ip)){
            throw new InvalidParameterException("tcp ip is empty");
        }
        tcpConfig.setServerIp(ip);
            String port = String.valueOf(config.prop.get(ConfigConstant.serverPort.getKey()));
        if(StringUtils.isEmpty(port)){
            throw new InvalidParameterException("tcp port is empty");
        }
        tcpConfig.setServerPort(Integer.valueOf(port));

        String byteBufferSize = getConfig(ConfigConstant.byteBufferSize.getKey(),"1024");
        tcpConfig.setByteBufferSize(Integer.valueOf(byteBufferSize));

        String idleSecond = getConfig(ConfigConstant.idleSecond.getKey(), "10");
        tcpConfig.setIdleSecond(Integer.valueOf(idleSecond));

        String isHeartBeatString = getConfig(ConfigConstant.isHeartBeat.getKey(),"false");
        tcpConfig.setIsHeartBeat(Boolean.getBoolean(isHeartBeatString));

        return tcpConfig;
    }

    public static String getConfig(String key){
        if(key == null){
            return null;
        }
        Object propValue = config.prop.get(key);
        if(propValue == null){
            return null;
        }
        return String.valueOf(propValue);
    }
    public static String getConfig(String key,String defaultString){
        if(key == null){
            return null;
        }
        Object propValue = config.prop.get(key);
        if(propValue == null){
            return defaultString;
        }
        return String.valueOf(propValue);
    }

}
