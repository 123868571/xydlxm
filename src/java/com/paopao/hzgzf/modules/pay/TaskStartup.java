package com.paopao.hzgzf.modules.pay;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//import com.sun.net.httpserver.HttpHandler;
//import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.paopao.hzgzf.modules.pay.service.WriteOffHouseRentTask;

@Service
public class TaskStartup {
    Logger logger = LoggerFactory.getLogger(TaskStartup.class);
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
//    @Autowired
//    WriteOffHouseRentTask dealBillService;
    
    public TaskStartup() throws Exception {
        //加载配置信息

    }
    public void start(){
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
            	try{
            		//调用任务代码
//            		dealBillService.generateHouseRentAcctItemEveryDay();
            	}catch(Exception e){
            		if (logger.isErrorEnabled()) {
            			logger.error(e.getMessage());
					}
            	}
            }
        }, 60L/**初始延迟**/, 60L/**多久调用一次*/, TimeUnit.SECONDS);
    }

    public void stop(){
        scheduledThreadPoolExecutor.shutdownNow();
    }

//    void createContext(String path,HttpHandler handler){
//        server.createContext(path,handler);
//    }

}
