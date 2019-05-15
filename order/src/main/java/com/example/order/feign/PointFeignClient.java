package com.example.order.feign;

import com.example.order.config.FeignConfiguration;
import com.example.order.model.Point;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hui.yunfei@qq.com on 2019/5/13
 */
@FeignClient(value = "point-server",fallback = PointFeignHystric.class,configuration = FeignConfiguration.class)//
public interface PointFeignClient {

    @RequestMapping(value="/point/sayHi",method= RequestMethod.GET)
    void sayHi(@RequestParam(value="name") String name);

    @RequestMapping(value="/point/findById/{id}",method= RequestMethod.GET)
    Point findById(@PathVariable("id") String id);

    @RequestMapping(value="/point/findByIdAndName",method= RequestMethod.POST)//, consumes = MediaType.APPLICATION_JSON_VALUE
    Point findByIdAndName(@RequestParam("id") String id,@RequestParam("name") String name);

   @RequestMapping(value="/point/update",method= RequestMethod.POST)
   //@RequestLine("POST /point/update")
    void update(@RequestBody Point point);

}
