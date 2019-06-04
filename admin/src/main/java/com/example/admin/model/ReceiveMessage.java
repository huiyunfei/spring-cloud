package com.example.admin.model;

import lombok.ToString;

/**
 * Created by hui.yunfei@qq.com on 2019/6/4
 */
@ToString
public class ReceiveMessage {
    private String msg;
    private String user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
