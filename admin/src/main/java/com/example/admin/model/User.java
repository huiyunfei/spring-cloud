package com.example.admin.model;

import java.security.Principal;

/**
 * Created by hui.yunfei@qq.com on 2019/6/11
 */
public class User implements Principal {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
