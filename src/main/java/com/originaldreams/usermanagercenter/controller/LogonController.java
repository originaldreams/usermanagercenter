package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.common.MyResponse;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录控制
 * 负责用户的登录和注册
 */
@RestController
@RequestMapping("/logon")
public class LogonController {
    private Logger logger = LoggerFactory.getLogger(LogonController.class);

    @Resource
    private UserService userService;

    /**
     *
     * @param userName
     * @param phone
     * @param wxId
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    ResponseEntity logon(String userName,String phone,String wxId,String email,String password){
        if(userName == null && phone == null && email == null && password == null){
            return MyResponse.badRequest("请求参数异常");
        }else{
            User user = new User();
            user.setUserName(userName);
            user.setPhone(phone);
            user.setWxId(wxId);
            user.setEmail(email);
            user.setPassword(password);
            return MyResponse.ok(userService.logon(user));

        }
    }

    @RequestMapping(value = "register" , method = RequestMethod.POST)
    public ResponseEntity register(String userName,String phone,String email,String password){
        if(userName == null  && phone == null && email == null){
            return MyResponse.badRequest("请求参数异常");
        }
        if(password == null){
            return MyResponse.badRequest("密码为空");
        }
        User user = new User();
        user.setUserName(userName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        return MyResponse.ok(userService.register(user));
    }
}
