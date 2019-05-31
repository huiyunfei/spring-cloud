package com.example.admin.web;

import com.example.admin.service.RestFulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hui.yunfei@qq.com on 2019/5/31
 */
@RestController
@RequestMapping("/restful")
public class RestFulController {

    @Autowired
    RestFulService restFulService;

//    @RequestMapping(value="/sayHi",method= RequestMethod.GET)
//    public void sayHi(@RequestParam(value="sid") String sid){
//        restFulService.insert(sid);
//        try {
//            WebSocketServer.sendInfo("hello",sid);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("sayhi in sid:"+sid);
//    }
}




