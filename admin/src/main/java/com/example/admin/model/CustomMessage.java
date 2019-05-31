package com.example.admin.model;

import lombok.*;

/**
 * Created by hui.yunfei@qq.com on 2019/5/31
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomMessage {
    private int id;
    private String name;
}
