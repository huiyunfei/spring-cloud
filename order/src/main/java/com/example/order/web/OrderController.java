package com.example.order.web;

import com.example.order.service.OrderService;
import com.example.pointshare.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value="/findById/{id}",method= RequestMethod.GET)
    public Point findById(@PathVariable(value="id") String id){
        log.info("order findById in：{} ",id);
        return orderService.findById(id);
    }

    @RequestMapping(value="/findByIdAndName",method= RequestMethod.POST)
    public Point findByIdAndName(@RequestParam(value="id") String id,@RequestParam(value="name") String name){
        log.info("order findByIdAndName in：{},{} ",id,name);
        return orderService.findByIdAndName(id,name);
    }

    @RequestMapping(value="/update",method= RequestMethod.POST)
    public void update(@RequestBody Point point){
        log.info("order update in：{}",point);
        orderService.update(point);
    }
}

