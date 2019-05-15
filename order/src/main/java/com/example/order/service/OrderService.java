package com.example.order.service;

import com.example.pointshare.model.Point;

/**
 * Created by hui.yunfei@qq.com on 2019/5/15
 */
public interface OrderService {
    void sayHi(String name);

    Point findById(String id);

    Point findByIdAndName(String id, String name);

    void update(Point point);
}
