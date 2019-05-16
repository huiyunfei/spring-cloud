package com.example.pointshare.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by hui.yunfei@qq.com on 2019/5/13
 */
@Data
@ToString
public class Point {

    private String id;

    private String userId;

    private int points;

    private Date updateTime;
}




