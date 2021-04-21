package com.dhcc.controller;


import com.alibaba.fastjson.JSONObject;
import com.dhcc.entity.User;
import com.dhcc.service.UserService;
import com.dhcc.util.R;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-04-26 22:04:54
 */
@RestController
@RequestMapping("userinsert")
    public class UserInsertController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    //给类初始化日志对象
    private final static Logger log = Logger.getLogger(UserInsertController.class);
    /**
     * 用户注册
     * 请求报文格式：json
     * 响应报文格式：json
     * @param data
     * @return
     */
    @PostMapping("insert")
    public  JSONObject insertUser(@RequestBody JSONObject data) {
        log.info("-----------------------------获取到前台请求------------------------------------");
        String msg = data.toJSONString();
        JSONObject jsonObject = new JSONObject();
        log.info("获取到的请求报文内容"+msg);
        //获取学号
        Integer userid = data.getInteger("userid");
        //获取班级
        Integer userclass = data.getInteger("userclass");
        //获取姓名
        String username = data.getString("username");
        //获取性别
        String gender = data.getString("gender");
        //获取密码
        Integer password = data.getInteger("password");
        log.info("需要入库的请求内容：学号："+userid+",班级："+userclass+",姓名："+username+",性别："+gender+",密码："+password);
        User user = new User();
        user.setUserid(userid);
        user.setUserclass(userclass);
        user.setUsername(username);
        user.setGender(gender);
        user.setPassword(password);
        int i = 0;
        try {
            log.info("开始将报文插入数据库....");
            i  =  this.userService.insert(user);

        }catch (Exception e){
            jsonObject.put("massage","该账户已注册");
            log.info("该账户已注册,报文插入失败......");

        }finally {
            if(i>0){
                log.info("报文成功插入数据库.....");
                jsonObject.put("code","200");
                jsonObject.put("massage","注册成功");
            }else{
                jsonObject.put("code","201");
                jsonObject.put("massage","注册失败");
                log.info("本次请求响应报文"+jsonObject.toJSONString());
                log.info("-----------------------------本次请求结束------------------------------------");
                return jsonObject;
            }
        }
        log.info("报文入库结束......");
        log.info("本次请求响应报文"+jsonObject.toJSONString());
        log.info("-----------------------------本次请求结束------------------------------------");
        return jsonObject;
    }
}
