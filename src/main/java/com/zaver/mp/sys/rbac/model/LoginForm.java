package com.zaver.mp.sys.rbac.model;

import java.io.Serializable;

/**
 * @ClassName : LoginForm
 * @Description TODO
 * @Date : 2019/5/6 16:57
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
public class LoginForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
