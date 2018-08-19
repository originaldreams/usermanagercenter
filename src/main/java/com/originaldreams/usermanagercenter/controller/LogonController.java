package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.common.router.MyRouter;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制
 * 负责用户的登录和注册
 * @author 杨凯乐
 * @date 2018-07-30 09:17:03
 */
@RestController
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
            return MyResponse.badRequest();
        }else{
            User user = new User();
            user.setUserName(userName);
            user.setPhone(phone);
            user.setWxId(wxId);
            user.setEmail(email);
            logger.info("user logon:" + user);
            user.setPassword(password);
            MyServiceResponse response = userService.logon(user);

            return MyResponse.ok(response);
        }
    }

    /**
     * 注册接口
     * @param userName  用户名（可用于登录） 选填
     * @param phone     手机号
     * @param email     邮箱  手机号和邮箱至少要填其中一项
     * @param password  密码  必填
     * @return
     */
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ResponseEntity register(String userName,String phone,String email,String password){
        if(userName == null  && phone == null && email == null){
            return MyResponse.badRequest();
        }
        if(password == null){
            return MyResponse.badRequest();
        }
        User user = new User();
        if(userName != null){
            user.setUserName(userName);
            user.setUserNameLogon();
        }
        if(phone != null){
            user.setPhone(phone);
            user.setPhoneLogon();
        }
        if(email != null){
            user.setEmail(email);
            user.setEmailLogon();
        }
        logger.info("user register:" + user);
        user.setPassword(password);
        return MyResponse.ok(userService.register(user));
    }
}
