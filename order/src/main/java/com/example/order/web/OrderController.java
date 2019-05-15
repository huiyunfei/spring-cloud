package com.example.order.web;

import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hui.yunfei@qq.com on 2019/5/14
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/sayHi",method= RequestMethod.GET)
    public void sayHi(@RequestParam(value="name") String name){
        log.info("order sayHi in：{} ",name);
        orderService.sayHi(name);
    }
//    @RequestMapping(value="/findById/{id}",method= RequestMethod.GET)
//    public Point findById(@PathVariable(value="id") String id){
//        log.info("order findById in：{} ",id);
//        return pointFeignClient.findById(id);
//    }
//
//    @RequestMapping(value="/findByIdAndName",method= RequestMethod.POST)
//    public Point findByIdAndName(@RequestParam(value="id") String id,@RequestParam(value="name") String name){
//        log.info("order findByIdAndName in：{},{} ",id,name);
//        return pointFeignClient.findByIdAndName(id,name);
//    }
//
//    @RequestMapping(value="/update",method= RequestMethod.POST)
//    public void update(@RequestBody Point point){
//        log.info("order update in：{}",point);
//        pointFeignClient.update(point);
//    }
}

