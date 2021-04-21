package com.dhcc.entity;

import java.io.Serializable;

/**
 * (Activity)实体类
 *
 * @author makejava
 * @since 2020-04-22 14:37:57
 */
public class Activity implements Serializable {
    private static final long serialVersionUID = -69855301369459166L;
    /**
     * 活动流水号
     */
    private Integer actcode;
    /**
     * 活动ID
     */
    private Integer actid;
    /**
     * 活动名称
     */
    private String actname;
    /**
     * 活动内容
     */
    private String actcontent;
    /**
     * 活动地点
     */
    private String actsite;
    /**
     * 活动开始时间
     */
    private String actbegintime;
    /**
     * 活动结束时间
     */
    private String actendtime;
    /**
     * 用户学号
     */
    private Integer userid;
    /**
     * 用户状态(01代表发布，02代表参与)
     */
    private Integer flag;
    /**
     * 参与/发布人班级
     */
    private Integer userclass;
    /**
     * 参与/发布人姓名
     */
    private String username;
    /**
     * 参与/发布人性别
     */
    private String gender;


    public Integer getActcode() {
        return actcode;
    }

    public void setActcode(Integer actcode) {
        this.actcode = actcode;
    }

    public Integer getActid() {
        return actid;
    }

    public void setActid(Integer actid) {
        this.actid = actid;
    }

    public String getActname() {
        return actname;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getActcontent() {
        return actcontent;
    }

    public void setActcontent(String actcontent) {
        this.actcontent = actcontent;
    }

    public String getActsite() {
        return actsite;
    }

    public void setActsite(String actsite) {
        this.actsite = actsite;
    }

    public String getActbegintime() {
        return actbegintime;
    }

    public void setActbegintime(String actbegintime) {
        this.actbegintime = actbegintime;
    }

    public String getActendtime() {
        return actendtime;
    }

    public void setActendtime(String actendtime) {
        this.actendtime = actendtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

}