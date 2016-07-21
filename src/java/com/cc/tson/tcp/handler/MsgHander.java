package com.cc.tson.tcp.handler;

import com.cc.tson.bean.Response;
import com.cc.tson.tcp.TcpHolder;
import com.cc.tson.util.JsonUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgHander<T> extends IoHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MsgHander.class);

    private TcpHolder tcpHolder;

    public MsgHander(TcpHolder tcpHolder) {
        this.tcpHolder = tcpHolder;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        logger.warn(session.getId() + "", cause);
        session.close(true);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("reveive string :"  + message);
        Response response = JsonUtil.toObject((String) message, Response.class);
        tcpHolder.getResponseCallBack().executorResult(response);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info("session idle:id=" + session.getId() + ":" + status);
        if(tcpHolder.getTcpConfig().getIsHeartBeat()) {
            if(status == IdleStatus.READER_IDLE){
                session.write("heartbeat");
            }
        }
    }
}
