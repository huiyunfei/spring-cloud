package com.example.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by huiyunfei on 2019/5/31.
 */
@Controller
@RequestMapping("/testWebSocket")
public class TestWebSocketController {
    //页面请求
    @GetMapping("/socket/{userId}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{userId}")
    public ResultObj pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
            ResultObj obj=new ResultObj();
            obj.setInfo(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
