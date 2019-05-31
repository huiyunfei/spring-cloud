package com.example.admin.web;

import com.example.admin.model.CustomMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by hui.yunfei@qq.com on 2019/5/31
 */

@Controller
@RequestMapping("/system")
public class SystemController {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;
    //页面请求
//    @GetMapping("/index/{userId}")
//    public ModelAndView socket(@PathVariable String userId) {
//        ModelAndView mav=new ModelAndView("/socket1");
//        mav.addObject("userId", userId);
//        return mav;
//    }
//    //推送数据接口
//    @ResponseBody
//    @RequestMapping("/socket/push/{cid}")
//    public Map pushToWeb(@PathVariable String cid, String message) {
//        Map result = new HashMap();
//        try {
//            WebSocketServer.sendInfo(message,cid);
//            result.put("code", 200);
//            result.put("msg", "success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    //页面请求
    @GetMapping("/socket2")
    public ModelAndView socket2() {//@PathVariable String userId
        ModelAndView mav=new ModelAndView("html/socket2");
        //mav.addObject("userId", userId);
        return mav;
    }

    /**
     * @Description:这个方法是接收客户端发送功公告的WebSocket请求，使用的是@MessageMapping
     * @Author:hui.yunfei@qq.com
     * @Date: 2019/5/31
     */
    @MessageMapping("/change-notice")//客户端访问服务端的时候config中配置的服务端接收前缀也要加上 例：/app/change-notice
    @SendTo("/topic/notice")//config中配置的订阅前缀记得要加上
    public CustomMessage greeting(CustomMessage message){
        System.out.println("服务端接收到消息："+message.toString());
        //我们使用这个方法进行消息的转发发送！
        //this.simpMessagingTemplate.convertAndSend("/topic/notice", value);(可以使用定时器定时发送消息到客户端)
        //        @Scheduled(fixedDelay = 1000L)
        //        public void time() {
        //            messagingTemplate.convertAndSend("/system/time", new Date().toString());
        //        }
        //也可以使用sendTo发送
        return message;
    }


}
