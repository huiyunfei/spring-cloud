package com.example.order.service.impl;

import com.example.order.service.OrderService;
import com.example.pointshare.feign.PointService;
import com.example.pointshare.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hui.yunfei@qq.com on 2019/5/15
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private PointService pointService;

    @Override
    public void sayHi(String name) {
        pointService.sayHi(name);
    }

    @Override
    public Point findById(String id) {
        return pointService.findById(id);
    }

    @Override
    public Point findByIdAndName(String id, String name) {
        return pointService.findByIdAndName(id,name);
    }

    @Override
    public void update(Point point) {
        pointService.update(point);
    }

}






