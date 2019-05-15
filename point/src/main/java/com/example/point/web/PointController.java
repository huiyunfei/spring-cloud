package com.example.point.web;

import com.example.point.model.Point;
import com.example.point.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hui.yunfei@qq.com on 2019/5/14
 */
@RestController
@RequestMapping("/point")
@Slf4j
public class PointController {

    @Value("${server.port}")
    String point;

    @Autowired
    private PointService pointService;

    @RequestMapping(value="/sayHi",method= RequestMethod.GET)
    public void sayHi(@RequestParam(value="name") String name){
        log.info("point sayHi in name: {},point: {}",name,point);
    }

    @RequestMapping(value="/findById/{id}",method= RequestMethod.GET)
    public Point findById(@PathVariable(value="id") String id){
        log.info("point findById in：{} ",id);
        Point point=pointService.findById(id);
        return point;
    }
    @RequestMapping(value="/findByIdAndName",method= RequestMethod.POST)
    public Point findByIdAndName(@RequestParam(value="id") String id,@RequestParam(value="name") String name){
        log.info("point findByIdAndName in：{},{} ",id,name);
        Point point=pointService.findByIdAndName(id,name);
        return point;
    }


    @RequestMapping(value="/update",method= RequestMethod.POST)
    public void update(@RequestBody Point point){
        log.info("point update in：{}",point);
        pointService.update(point);
    }
}
