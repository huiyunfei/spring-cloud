package com.example.admin.config;

import com.example.admin.service.RestFulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hui.yunfei@qq.com on 2019/6/4
 */
@Component
@EnableScheduling
public class TimeTask {
    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);

    @Autowired
    public SimpMessagingTemplate template;
    @Autowired
    RestFulService restFulService;
    @Scheduled(cron = "0/20 * * * * ?")
    public void test(){
        System.err.println("*********   定时任务执行   **************");
//        CopyOnWriteArraySet<WebSocketServer> webSocketSet =
//                WebSocketServer.getWebSocketSet();
//        int i = 0 ;
//        webSocketSet.forEach(c->{
//            try {
//                c.sendMessage("  定时发送  " + new Date().toLocaleString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        String message=restFulService.getMessage();
        template.convertAndSend("/topic/getResponse", "我是服务器主动推送的定时消息"+message+"|||"+new Date().toLocaleString());
        System.err.println("/n 定时任务完成.......");
    }
}
