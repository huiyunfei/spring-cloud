package com.example.order.feign;

import com.example.order.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by hui.yunfei@qq.com on 2019/5/15
 */
@Slf4j
@Component
public class PointFeignHystric implements PointFeignClient{
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
