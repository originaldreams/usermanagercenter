package com.originaldreams.usermanagercenter.service;

import com.originaldreams.usermanagercenter.common.MyMD5Utils;
import com.originaldreams.usermanagercenter.common.MyResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 判断是否使用密码登录
     *  如果使用密码登录，看是用户名密码、手机号密码、邮箱密码的哪种组合
     *      如果密码校验无误，返回用户ID
     *      如果密码校验失败或不支持指定的密码登录组合，则返回相应的错误信息
     *  如果不是密码登录，看是否是微信登录
     * @param userName
     * @param phone
     * @param wxId
     * @param email
     * @param password
     * @return
     */
    public MyResponseObject logon(String userName, String phone, String wxId, String email, String password)  {
        User user = null;
        boolean checkPassword = false;
        MyResponseObject responseObject = new MyResponseObject();
        if(password != null){   //使用密码登录
            if(userName != null){   //用户名密码组合
                user = userMapper.getByUserName(userName);
                //判断是否允许用户使用用户名登录
                if(user != null && user.permitUserNameLogon()){
                    checkPassword = true;
                }else{
                    responseObject.setSuccess(MyResponseObject.success_code_failed);
                    responseObject.setMessage("不支持用户名登录");
                }
            }else if(phone != null){    //手机号密码组合
                user = userMapper.getByPhone(phone);
                //判断是否允许用户使用手机号登录
                if(user != null && user.permitPhoneLogon()){
                    checkPassword = true;

                }else{
                    responseObject.setSuccess(MyResponseObject.success_code_failed);
                    responseObject.setMessage("不支持手机号登录");
                }
            }else if(email != null){    //邮箱密码组合
                user = userMapper.getByEmail(email);
                //判断是否允许用户使用邮箱登录
                if(user != null && user.permitEmailLogon()){
                    checkPassword = true;
                }else{
                    responseObject.setSuccess(MyResponseObject.success_code_failed);
                    responseObject.setMessage("不支持邮箱登录");
                }
            }
            try{
                //校验密码
                if(checkPassword && MyMD5Utils.checkqual(password,user.getPassword())){
                    responseObject.setData(user.getId());
                }else {
                    responseObject.setSuccess(MyResponseObject.success_code_failed);
                    responseObject.setMessage("用户名密码错误");
                }
            }catch (Exception e){
                logger.error("校验密码异常 ",e);
                responseObject.setSuccess(MyResponseObject.success_code_failed);
                responseObject.setMessage("用户名密码错误");
            }


        }else{
            //TODO 微信登录
        }
        return responseObject;
    }

    public User getById(Integer id){

        return userMapper.getById(id);
    }

    public List<User> getAll(){
        return userMapper.getAll();
    }

    public Integer insert(User user){
        return userMapper.insert(user);
    }

    public Integer deleteById(Integer id){
        return userMapper.deleteById(id);
    }

    public Integer update(User user){
        return userMapper.update(user);
    }


}
