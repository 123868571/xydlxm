package com.cc.tson.tcp;

import com.cc.tson.tcp.config.TcpConfig;
import com.cc.tson.tcp.config.TcpConfiguration;
import com.cc.tson.tcp.handler.MsgHander;
import com.cc.tson.tcp.listener.IoServiceListenerAdpter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by caohx on 2016/3/8.
 */
public class TcpHolder {

    private static final Logger logger = LoggerFactory.getLogger(TcpHolder.class);

    /** The connector */
    private IoConnector connector;

    /** The session */
    private volatile IoSession session;

    private TcpConfig tcpConfig = TcpConfiguration.getTcpConfig();

    private com.cc.tson.tcp.ResultCallBack resultCallBack;

    private AtomicBoolean started = new AtomicBoolean(false);

    public TcpHolder(){
    }

    public void setResponseCallBack(com.cc.tson.tcp.ResultCallBack callBack){
        this.resultCallBack = callBack;
    }

    public TcpConfig getTcpConfig() {
        return tcpConfig;
    }

    public com.cc.tson.tcp.ResultCallBack getResponseCallBack(){
        return this.resultCallBack;
    }

    public void start(){
        if(!started.compareAndSet(false,true)){
            return;
        }
        connector = new NioSocketConnector();
        connector.getSessionConfig().setReaderIdleTime(tcpConfig.getIdleSecond());
        connector.getSessionConfig().setMinReadBufferSize(tcpConfig.getByteBufferSize());
        connector.getSessionConfig().setMaxReadBufferSize(tcpConfig.getByteBufferSize());
        connector.getSessionConfig().setReadBufferSize(tcpConfig.getByteBufferSize());
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(
                        Charset.forName("UTF-8"),
                        "\n",
                        "\n"
                )
                )
        );

        connector.setHandler(new MsgHander(this));
        connector.addListener(new IoServiceListenerAdpter(){
            @Override
            public void sessionDestroyed(IoSession session) throws Exception{
                for(;;){
                    try{
                        if(!started.get()){
                            break;
                        }
                        Thread.sleep(3000);
                        SocketAddress socketAddress = new InetSocketAddress(tcpConfig.getServerIp(),tcpConfig.getServerPort());
                        ConnectFuture future = connector.connect(socketAddress);
                        future.awaitUninterruptibly();// 等待连接创建成功
                        TcpHolder.this.session = session = future.getSession();// 获取会话
                        if(session.isConnected()){
                            logger.info("reconnection [" + tcpConfig.getServerIp() +":"+ tcpConfig.getServerPort() +"]成功");
                            System.out.println("reconnection [" + tcpConfig.getServerIp() +":"+ tcpConfig.getServerPort() +"]成功");
                            break;
                        }
                    }catch(Exception ex){
                        logger.info("reconnection server,after three second :" + ex.getMessage());
                        System.out.println("reconnection server,after three second :" + ex.getMessage());
                    }
                }
            }

        });
        SocketAddress socketAddress = new InetSocketAddress(tcpConfig.getServerIp(),tcpConfig.getServerPort());

        ConnectFuture connFuture = connector.connect(socketAddress);

        connFuture.awaitUninterruptibly();

        session = connFuture.getSession();
    }

    public void write(String message){
        if(session.getCloseFuture().isClosed()){
            throw new RuntimeException("server not active!");
        }
        logger.debug("send:" + message);
        System.out.println("send: " + message);
        session.write(message);
    }

    public void stop(){
        if(!started.compareAndSet(true,false)){
            return;
        }
        connector.dispose();
    }

}
