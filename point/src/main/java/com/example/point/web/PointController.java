package com.example.point.web;

import com.example.pointshare.feign.PointService;
import com.example.pointshare.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hui.yunfei@qq.com on 2019/5/14
 */
@RestController
@RequestMapping("/point")
@Slf4j
@RefreshScope//不加该注解刷新无效
public class PointController {

    @Value("${server.port}")
    String point;

    @Value("${local.profile}")
    String localProfile;
    @Value("${config.profile}")
    String configProfile;

    @Autowired
    private PointService pointService;

    @RequestMapping(value="/sayHi",method= RequestMethod.GET)
    public void sayHi(@RequestParam(value="name") String name){
        log.info("point sayHi in name: {},point: {},localProfile: {},configProfile: {}",name,point,localProfile,configProfile);
        pointService.sayHi(name);
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
