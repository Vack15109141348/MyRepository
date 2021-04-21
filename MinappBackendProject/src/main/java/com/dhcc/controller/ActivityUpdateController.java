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
@RequestMapping("updateActivity")
public class ActivityUpdateController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;
    //给类初始化日志对象
    private final static Logger log = Logger.getLogger(ActivityInsertController.class);

    /**
     * 根据actcode修改活动
     * @param data
     * @return
     */
    @PostMapping("update")
    public JSONObject updateActivity(@RequestBody JSONObject data){
        log.info("-----------------------------获取到前台请求------------------------------------");
        String messsge = data.toJSONString();
        log.info("接收到到的请求报文内容"+messsge);
        JSONObject jsonObject = new JSONObject();
        //获取活动流水号
        Integer actcode = data.getInteger("actcode");
        //获取活动ID，在本方法中并未用到
        Integer actid = data.getInteger("actid");
        //活动名称
        String actname = data.getString("actname");
        // 活动内容
        String actcontent = data.getString("actcontent");
        //活动地点
        String actsite= data.getString("actsite");
        // 活动开始时间
        String actbegintime= data.getString("actbegintime");
        // 活动结束时间
        String actendtime= data.getString("actendtime");
        log.info("准备修改的报文内容");
        log.info("活动名称:"+actname+",活动内容:"+actcontent+",活动地点:"+actsite);
        log.info("活动开始时间:"+actbegintime+",活动结束时间:"+"活动流水号"+actcode);
       Activity activity = new Activity();
        activity.setActbegintime(actbegintime);
        activity.setActcontent(actcontent);
        activity.setActendtime(actendtime);
        activity.setActcode(actcode);
        activity.setActsite(actsite);
        activity.setActname(actname);
        int i = 0 ;
        try {
            i = this.activityService.update(activity);
        }catch (Exception e){

        }finally {
            if( i > 0){
                log.info("修改成功");
                jsonObject.put("code","200");
                jsonObject.put("massage","成功");
            }else {
                log.info("修改失败");
                jsonObject.put("code","201");
                jsonObject.put("massage","失败");
            }
        }
        log.info("本次请求的响应报文："+jsonObject.toJSONString());
        log.info("-----------------------------本次请求结束------------------------------------");
        return jsonObject;
    }



}
