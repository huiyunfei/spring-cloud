package com.example.point.service.impl;

import com.example.point.dao.PointDao;
import com.example.point.model.Point;
import com.example.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by hui.yunfei@qq.com on 2019/5/14
 */
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointDao pointDao;

    @Override
    public Point findById(String id) {
        return pointDao.findById(id);
    }

    @Override
    public Point findByIdAndName(String id, String name) {
        return pointDao.findByIdAndName(id,name);
    }

    @Override
    public void update(Point point) {
        point.setUpdateTime(new Date());
        pointDao.update(point);
    }
}




