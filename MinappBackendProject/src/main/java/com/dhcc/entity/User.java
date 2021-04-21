package com.dhcc.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-26 22:04:54
 */
public class User implements Serializable {
    private static final long serialVersionUID = 614143641996594730L;
    /**
     * 学号
     */
    private Integer userid;
    /**
     * 班级
     */
    private Integer userclass;
    /**
     * 姓名
     */
    private String username;
    /**
     * 性别
     */
    private String gender;
    /**
     * 密码
     */
    private Integer password;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserclass() {
        return userclass;
    }

    public void setUserclass(Integer userclass) {
        this.userclass = userclass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

}