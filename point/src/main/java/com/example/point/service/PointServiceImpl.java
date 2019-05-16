package com.example.point.service;

import com.example.point.dao.PointDao;
import com.example.pointshare.feign.PointService;
import com.example.pointshare.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by hui.yunfei@qq.com on 2019/5/14
 */
@Slf4j
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointDao pointDao;

    @Override
    public void sayHi(String name) {
        log.info("point feignImpl sayHi in：{} ",name);
    }

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




