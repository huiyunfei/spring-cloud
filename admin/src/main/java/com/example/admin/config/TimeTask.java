package com.example.admin.config;

import com.example.admin.web.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by huiyunfei on 2019/5/31.
 */
@Component
@EnableScheduling
public class TimeTask {
    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);
    @Scheduled(cron = "0/1 * * * * ?")   //每分钟执行一次
    public void test(){
        System.err.println("*********   定时任务执行   **************");
        CopyOnWriteArraySet<WebSocketServer> webSocketSet =
                WebSocketServer.getWebSocketSet();
        int i = 0 ;
        webSocketSet.forEach(c->{
            try {
                c.sendMessage("  定时发送  " + new Date().toLocaleString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.err.println("/n 定时任务完成.......");
    }
}


