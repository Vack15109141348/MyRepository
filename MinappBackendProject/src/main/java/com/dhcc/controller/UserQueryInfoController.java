package com.dhcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.entity.User;
import com.dhcc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-04-26 22:04:54
 */
@RestController
@CrossOrigin
@RequestMapping("userselect")
public class UserQueryInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    //给类初始化日志对象
    private final static Logger log = Logger.getLogger(UserQueryInfoController.class);

    /**
     * 用户登录
     * 请求报文格式：json
     * 响应报文格式：json
     *
     * @param data
     * @return
     */
    @PostMapping("selectOne")
    public JSONObject selectOne(@RequestBody JSONObject data) {
        log.info("-----------------------------获取到前台请求------------------------------------");
        String msg = data.toJSONString();
        JSONObject jsonObject = new JSONObject();
        log.info("获取到的请求报文内容" + msg);
        //获取学号
        Integer userid = data.getInteger("userid");
        //获取密码
        Integer password = data.getInteger("password");
        log.info("需要校验的请求内容：学号：" + userid + ",密码：" + password);
        try {
            User user = userService.queryById(userid);
            if (!user.getPassword().equals(password)) {
                log.info("密码错误");
                jsonObject.put("code", "202");
                jsonObject.put("message", "密码错误");
            } else {
                log.info("登陆成功");
                jsonObject.put("code", "200");
                jsonObject.put("message", "登陆成功");
            }
        }catch (Exception e){
            log.info("该账户不存在");
            jsonObject.put("code", "201");
            jsonObject.put("message", "账户不存在，请先注册");
        }finally {

        }
        log.info("本次请求响应报文"+jsonObject.toJSONString());
        log.info("-----------------------------本次请求结束------------------------------------");
        return jsonObject;
    }
}