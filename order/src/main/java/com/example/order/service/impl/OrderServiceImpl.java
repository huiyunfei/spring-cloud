package com.example.order.service.impl;

import com.example.order.feign.PointFeignClient;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hui.yunfei@qq.com on 2019/5/15
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private PointFeignClient pointFeignClient;

    @Override
    public void sayHi(String name) {
        pointFeignClient.sayHi(name);
    }

}






