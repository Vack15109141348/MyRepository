package com.dhcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.entity.Activity;
import com.dhcc.service.ActivityService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;

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
@RequestMapping("deleteActivity")
public class ActivityDeleteController {

    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;
    //给类初始化日志对象
    private final static Logger log = Logger.getLogger(ActivityDeleteController.class);

    /**
     * @Description 删除活动
     * @Date 2020/5/27 10:10
      * @param actcode 活动流水号，根据活动流水号删除
     *@return com.alibaba.fastjson.JSONObject
     **/

    @PostMapping("delete")
    public JSONObject delete(Integer actcode) {
        log.info("-----------------------------获取到前台请求------------------------------------");
        log.info("进入删除活动接口");
        JSONObject jsonObject = new JSONObject();
        //此变量用于判断是否删除成功
        boolean istrue = true;
        //接收前端传递过来的活动流水号
        Integer actcode1 = actcode;
        Activity activity = new Activity();
        activity.setActcode(actcode1);
        try {
            ///调用deleteById方法进行删除操作
            istrue = this.activityService.deleteById(activity);
        } catch (Exception e) {
        } finally {
            if (istrue) {
                jsonObject.put("code", "200");
                jsonObject.put("massage", "成功");
                log.info("删除成功");
            } else {
                jsonObject.put("code", "201");
                jsonObject.put("massage", "失败");
                log.info("删除失败");
            }
        }
        log.info("本次请求响应报文" + jsonObject.toJSONString());
        log.info("-----------------------------本次请求结束------------------------------------");
        return jsonObject;
    }


}
