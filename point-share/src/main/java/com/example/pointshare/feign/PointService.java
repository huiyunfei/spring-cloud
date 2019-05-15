package com.example.pointshare.feign;

import com.example.pointshare.model.Point;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hui.yunfei@qq.com on 2019/5/13
 */
@FeignClient(value = "point-server",fallback = PointService.PointFeignHystric.class,configuration = PointService.FeignConfiguration.class)
public interface PointService {

    @RequestMapping(value="/point/sayHi",method= RequestMethod.GET)
    void sayHi(@RequestParam(value = "name") String name);

    @RequestMapping(value="/point/findById/{id}",method= RequestMethod.GET)
    Point findById(@PathVariable("id") String id);

    @RequestMapping(value="/point/findByIdAndName",method= RequestMethod.POST)//, consumes = MediaType.APPLICATION_JSON_VALUE
    Point findByIdAndName(@RequestParam("id") String id, @RequestParam("name") String name);

   @RequestMapping(value="/point/update",method= RequestMethod.POST)
   //@RequestLine("POST /point/update")
    void update(@RequestBody Point point);

    @Component
    @Slf4j
    public class PointFeignHystric implements PointService {
        @Override
        public void sayHi(String name) {
            log.info("sayHi feign error");
        }

        @Override
        public Point findById(String id) {
            return null;
        }

        @Override
        public Point findByIdAndName(String id, String name) {
            return null;
        }

        @Override
        public void update(Point point) {

        }
    }

    /**
     * Created by hui.yunfei@qq.com on 2019/5/15
     * Feign禁用Hystrix功能
     */
    @Configuration
    public class FeignConfiguration {
        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }
    }

}
