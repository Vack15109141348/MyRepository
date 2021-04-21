package com.dhcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.entity.Activity;
import com.dhcc.service.ActivityService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Activity)表控制层
 *
 * @author makejava
 * @since 2020-04-22 14:37:59
 */
@RestController
@RequestMapping("insertActivity")
public class ActivityInsertController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;
    //给类初始化日志对象
    private final static Logger log = Logger.getLogger(ActivityInsertController.class);

    /**
     * 发布/参与活动
     * 请求报文格式：json
     * 返回报文格式：json
     * flag = 0代表参与
     * flag = 1代表发布
     *
     * @param data
     * @return
     */
    @PostMapping("insert")
    public JSONObject insertActivity(@RequestBody JSONObject data) {
        log.info("-----------------------------获取到前台请求------------------------------------");
        String messsge = data.toJSONString();
        log.info("接收到到的请求报文内容" + messsge);
        JSONObject jsonObject = new JSONObject();
        //获取学号
        Integer userid = data.getInteger("userid");
        //获取班级
        Integer userclass = data.getInteger("userclass");
        //获取姓名
        String username = data.getString("username");
        //获取性别
        String gender = data.getString("gender");
        //获取活动ID
        Integer actid = data.getInteger("actid");
        //活动名称
        String actname = data.getString("actname");
        // 活动内容
        String actcontent = data.getString("actcontent");
        //活动地点
        String actsite = data.getString("actsite");
        // 活动开始时间
        String actbegintime = data.getString("actbegintime");
        // 活动结束时间
        String actendtime = data.getString("actendtime");
        //活动状态
        Integer flag = data.getInteger("flag");
        log.info("准备入库的报文内容");
        log.info("学号:" + userid + ",班级:" + userclass + ",姓名:" + username + ",性别:" + gender);
        log.info("活动ID:" + actid + ",活动名称:" + actname + ",活动内容:" + actcontent + ",活动地点:" + actsite);
        log.info("活动开始时间:" + actbegintime + ",活动结束时间:" + actendtime + ",活动状态:" + flag);
        Activity activity = new Activity();
        activity.setFlag(flag);
        activity.setUserid(userid);
        activity.setActbegintime(actbegintime);
        activity.setActcontent(actcontent);
        activity.setActendtime(actendtime);
        activity.setActid(actid);
        activity.setActsite(actsite);
        activity.setUsername(username);
        activity.setUserclass(userclass);
        activity.setGender(gender);
        activity.setActname(actname);
        int i = 0;
        try {
            log.info("开始将报文插入数据库....");
            i = this.activityService.insert(activity);
        } catch (Exception e) {
            log.info("报文插入失败......");

        } finally {
            if (i > 0) {
                log.info("成功插入数据库.....");
                if (flag == 1) {
                    jsonObject.put("code", "200");
                    jsonObject.put("massage", "发布成功");

                } else {
                    jsonObject.put("code", "200");
                    jsonObject.put("massage", "参加成功");
                }
            } else {
                log.info("入库失败.....");
                if (flag == 1) {
                    jsonObject.put("code", "201");
                    jsonObject.put("massage", "发布失败");

                } else {
                    jsonObject.put("code", "201");
                    jsonObject.put("massage", "参与失败");
                }
            }
        }
        log.info("本次请求响应报文" + jsonObject.toJSONString());
        log.info("-----------------------------本次请求结束------------------------------------");
        return jsonObject;
    }


}
