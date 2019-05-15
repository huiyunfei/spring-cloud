package com.example.point.dao;

import com.example.pointshare.model.Point;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by hui.yunfei@qq.com on 2019/5/15
 */
@Mapper
public interface PointDao {


    @Select("select * from t_point where id=#{id}")
    //公用同一个resultMap可以添加mapper文件写映射关系
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "point", column = "point"),
            @Result(property = "updateTime", column = "update_time")
    })
    Point findById(String id);


    @Select("select * from t_point where id=#{id} and user_id=#{name}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "point", column = "point"),
            @Result(property = "updateTime", column = "update_time")
    })
    Point findByIdAndName(@Param("id") String id, @Param("name") String name);

    @UpdateProvider(type = PointSql.class, method = "update")
    void update(Point point);

    class PointSql {
        public String update(Point point) {
            return new SQL() {{
                UPDATE("t_point");
                //条件写法.
                if (point.getPoints() != 0) {
                    SET("points=points+#{points}");
                }
                if (point.getUpdateTime() != null) {
                    SET("update_time=#{updateTime}");
                }
                WHERE("id=#{id}");
            }}.toString();
        }
    }
}
