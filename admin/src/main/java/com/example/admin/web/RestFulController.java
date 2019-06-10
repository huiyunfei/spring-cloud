package com.example.admin.web;

import com.example.admin.model.ReceiveMessage;
import com.example.admin.service.RestFulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hui.yunfei@qq.com on 2019/5/31
 */
@Controller
@RequestMapping("/restful")
public class RestFulController {

    @Autowired
    RestFulService restFulService;

    @Autowired
    public SimpMessagingTemplate template;

    /**
     * @Description:广播
     * @Author:hui.yunfei@qq.com
     * @Date: 2019/6/4
     */
    @MessageMapping("/subscribe")
    //@SendTo
    public void subscribe(ReceiveMessage rm) {
        System.out.println("服务端接收到广播消息："+rm);
        for(int i =1;i<=5;i++) {
            //广播使用convertAndSend方法，第一个参数为目的地，和js中订阅的目的地要一致
            template.convertAndSend("/topic/getResponse", rm.getMsg());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @Description:点对点
     * @Author:hui.yunfei@qq.com
     * @Date: 2019/6/4
     */
    @MessageMapping("/queue")
    //@SendToUser
    public void queue(ReceiveMessage rm) {
        System.out.println("服务端接收到点对点消息："+rm);
        for(int i =1;i<=5;i++) {
            /*广播使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
            "/user/" + 用户Id + "/message",其中"/user"是固定的*/
            template.convertAndSendToUser(rm.getUser(),"/message",rm.getMsg());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @Description:主动推消息到客户端(可配做定时器)
     * @Author:hui.yunfei@qq.com
     * @Date: 2019/6/4
     */

    public void send(){
        template.convertAndSend("/topic/getResponse", "我是服务器主动推送的消息");
    }
}




