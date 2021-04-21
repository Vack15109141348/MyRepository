package com.dhcc.controller;


import com.alibaba.fastjson.JSONObject;
import com.dhcc.entity.User;
import com.dhcc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("userupdate")
public class UserUpdateController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    //给类初始化日志对象
    private final static Logger log = Logger.getLogger(UserUpdateController.class);
    /**
     * 修改密码
     * *@param userid
     * *@param password
     * @return
     */
    @PostMapping("update")
    public JSONObject updateUser(@RequestBody JSONObject data){
        log.info("-----------------------------获取到前台请求------------------------------------");
        String msg = data.toJSONString();
        JSONObject jsonObject = new JSONObject();
        log.info("获取到的请求报文内容"+msg);
        Integer userid = data.getInteger("userid");
        Integer password = data.getInteger("password");
        User user = new User();
        user.setUserid(userid);
        user.setPassword(password);
        int i = 0 ;

        try {
            i = this.userService.update(user);
        }catch (Exception e){
            log.info("修改密码失败");
        }finally {
           if(i>0){
                jsonObject.put("code","200");
                jsonObject.put("massage","修改成功");
               log.info("修改密码成功");
           }else {
               jsonObject.put("code","201");
               jsonObject.put("massage","修改失败");
               log.info("修改密码失败");
           }
        }
        log.info("本次请求响应报文"+jsonObject.toJSONString());
        log.info("-----------------------------本次请求结束------------------------------------");
        return jsonObject;

}






}
