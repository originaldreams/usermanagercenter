package com.originaldreams.usermanager.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.common.util.StringUtils;
import com.originaldreams.common.util.ValidUserName;
import com.originaldreams.usermanager.service.UserService;
import com.originaldreams.usermanagercenter.entity.User;
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
 * @author 杨凯乐 MAMIAN
 * @date 2018-07-30 09:17:03
 */
@RestController
@RequestMapping(value = "/api/v1")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> login(String userName,String phone,String wxId,String email,String password){

        logger.info("userName = {}, phone = {}, email = {}", userName, phone, email);

        if(userName == null && phone == null && email == null && password == null){
            return MyResponse.badRequest();
        }else{
            User user = new User();
            user.setUserName(userName);
            user.setPhone(phone);
            user.setWxId(wxId);
            user.setEmail(email);
            logger.info("user login:" + user);
            user.setPassword(password);
            MyServiceResponse response = userService.login(user);

            return MyResponse.ok(response);
        }
    }

    /**
     * 注册接口
     * @param userName  手机号或邮箱
     * @param password  密码  必填
     * @param verificationCode 验证码
     * @return
     */
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ResponseEntity register(String userName,String password,String verificationCode) {
        try {
            if (StringUtils.isEmpty(userName,password,verificationCode)) {
                return MyResponse.badRequest();
            }
            User user = new User();
            logger.info("user register:" + userName);
            user.setPassword(password);
            if (ValidUserName.isValidPhoneNumber(userName)) {
                user.setPhone(userName);
                user.setPhoneLogon();
                return MyResponse.ok(userService.registerByPhone(user, verificationCode));
            } else if (ValidUserName.isValidEmailAddress(userName)) {
                user.setEmail(userName);
                user.setEmailLogon();
                return MyResponse.ok(userService.registerByEmail(user, verificationCode));
            } else {
                return MyResponse.badRequest();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return MyResponse.serverError();
        }
    }
}
