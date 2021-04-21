package com.dhcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.entity.Activity;
import com.dhcc.service.ActivityService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Activity)表控制层
 *
 * @author makejava
 * @since 2020-04-22 14:37:59
 */
@RestController
@RequestMapping("selectActivity")
public class ActivityQueryInfoController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;
    //给类初始化日志对象
    private final static Logger log = Logger.getLogger(ActivityQueryInfoController.class);

    /**
     * 通过flag查询所有发布的活动，此方法的flag默认为1
     *flag=1代表此活动为发布状态
     * @return 所有发布状态的活动实体集
     */
    @PostMapping("Allactivity")
    public List<Activity> selectAllAct() {
        log.info("进入查询所有发布的活动的接口");
        return this.activityService.queryAllAct();
    }
    /**
     * 通过actcode查询单条数据,用于显示某条活动的信息
     * 主要用于用户修改活动
     * @return 单条数据
     */
    @PostMapping("selectOne")
    public Activity selectOne(Integer actcode) {
        log.info("进入通过actcode查询单条数据,用于显示某条活动的信息的接口");
        return this.activityService.queryById(actcode);
    }

    /**
     * 查询某位用户参与的活动
     * flag默认为1
     */
    @PostMapping("selectByUserid")
    public List<Activity> selectByUserid(Integer userid) {
        log.info("进入查询用户已发布活动接口");
        log.info(userid);
        return this.activityService.queryByUserid(userid);
    }

    /**
     * 查询某位用户参与的活动
     * flag默认为0
     */
    @PostMapping("selectByUseridflag")
    public List<Activity> selectByUseridflag(Integer userid) {
        log.info("进入查询用户已参加活动的接口");
        log.info(userid);
        return this.activityService.queryByUseridflag(userid);
    }

    /**
     * 根据时间段统计某位学生在某个时间段参与/发布的活动
     * @param data
     * @return
     */
    @PostMapping("countByUserid")
    public JSONObject countByUserid(@RequestBody JSONObject data){
        String msg = data.toJSONString();
        log.info(msg);
        //获取到用户ID
        Integer userid = data.getInteger("userid");
        //查询状态（发布/参与）
        Integer flag = data.getInteger("flag");
        //开始时间
        String begintime = data.getString("begintime");
        //结束时间
        String endtime = data.getString("endtime");
        //开始时间和结束时间类型转换为integer类型
        Integer begin =  Integer.parseInt(begintime);
        Integer end = Integer.parseInt(endtime);
        JSONObject jsonObject = new JSONObject();
        int i = 0 ;
        List<Activity> activityList = null;
     try {
         if (flag == 1){
              activityList = this.activityService.queryByUserid(userid);
         }else {
              activityList = this.activityService.queryByUseridflag(userid);
         }
     }catch (Exception e){
         jsonObject.put("massage","该用户不存在");
     }finally {
         if (activityList!=null){
             for (Activity activity : activityList){
                 //查询寻用户的参与或发布时间
                 int b = Integer.parseInt(activity.getActbegintime());
                 //判断是否在规定的时间段内
                 if (b>=begin && b<=end){
                     i++;
                 }
             }
         }else {
            jsonObject.put("massage","该学生在此期间并未参与/发布活动");
         }
     }
     jsonObject.put("userid",userid);
     jsonObject.put("begintime",begintime);
     jsonObject.put("endtime",endtime);
     jsonObject.put("flag",flag);
     jsonObject.put("timecount",i);
     log.info(jsonObject.toJSONString());
           return jsonObject;
    }
/**
 * 查询某活动的参与人数和男生女生的参与人数
 */
@PostMapping("byactid")
public JSONObject selectCountByactid(@RequestBody JSONObject data){
    log.info("进入到查询某活动的参与人数和男生女生的参与人数接口");
    Integer actid = data.getInteger("actid");
    JSONObject jsonObject = new JSONObject();
    List<Activity> activityList = this.activityService.queryByactid(actid);
    //此处用map是用于去重，map的 key不可重复
    Map<Integer, Integer> map = new HashMap<>();
    Integer Ai = 0;
    Integer AM= 0;
    Integer AW=0;
    for (Activity activity : activityList) {
//            log.info(activity.getUserid());
        if (activity.getGender().equals("男")){
            AM++;
        }else {
            AW++;
        }
        map.put(activity.getUserid(), Ai);
    }
    Ai = map.size();

    jsonObject.put("Acount",Ai);
    jsonObject.put("Amcount",AM);
    jsonObject.put("Awcount",AW);
    log.info("本次响应报文"+jsonObject.toJSONString());
    return jsonObject;
}
    /**
     * 查询某个班级参加/发布活动的实体集
     * @param userclass
     * @return
     */
    @RequestMapping("userclass")
    public List<Activity> selectclass(Integer userclass, Integer flag) {
        return this.activityService.queryByUserClass(userclass, flag);
    }

    /**
     * 查询某个班级参与/发布活动的男生女生人数和参与活动的总人数
     * @param *userclass
     * @return
     */
    @RequestMapping("userclasscount")
    public JSONObject selectclasscount(@RequestBody JSONObject data) {
        log.info("进入到查询某个班级参与/发布活动的男生女生人数和参与活动的总人数接口");
        Integer userclass = data.getInteger("userclass");
        Integer flag = data.getInteger("flag");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);
        List<Activity> activityList = this.activityService.queryByUserClass(userclass, flag);
        Map<Integer, Integer> map = new HashMap<>();
        Integer i = 0;
        Integer M= 0;
        Integer W=0;
        for (Activity activity : activityList) {
//            log.info(activity.getUserid());
            if (activity.getGender().equals("男")){
                M++;
            }else {
                W++;
            }
            map.put(activity.getUserid(), i);
        }
        i = map.size();

        jsonObject.put("count",i);
        jsonObject.put("mcount",M);
        jsonObject.put("wcount",W);
        log.info("本次响应报文"+jsonObject.toJSONString());
        return jsonObject;
    }
}