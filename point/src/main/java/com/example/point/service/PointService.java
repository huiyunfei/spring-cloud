package com.example.point.service;

import com.example.point.model.Point;

/**
 * Created by hui.yunfei@qq.com on 2019/5/14
 */
public interface PointService {
    Point findById(String id);

    Point findByIdAndName(String id, String name);

    void update(Point point);
}
