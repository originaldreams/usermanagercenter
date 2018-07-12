package com.originaldreams.usermanagercenter.service;

import com.originaldreams.usermanagercenter.common.MD5Utils;
import com.originaldreams.usermanagercenter.common.MyResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

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
     * @throws Exception
     */
    public Map<String,Object> logon(String userName, String phone, String wxId, String email, String password) throws Exception{
        Map<String,Object> result = new HashMap<>();
        User user = null;
        boolean checkPassword = false;
        result.put(MyResponseHeader.success,MyResponseHeader.success_code_success);
        if(password != null){   //使用密码登录
            if(userName != null){   //用户名密码组合
                user = userMapper.getByUserName(userName);
                //判断是否允许用户使用用户名登录
                if(user != null && user.permitUserNameLogon()){
                    checkPassword = true;
                }else{
                    result.put(MyResponseHeader.success,MyResponseHeader.success_code_failed);
                    result.put(MyResponseHeader.message,"不支持用户名登录");
                }
            }else if(phone != null){    //手机号密码组合
                user = userMapper.getByPhone(phone);
                //判断是否允许用户使用手机号登录
                if(user != null && user.permitPhoneLogon()){
                    checkPassword = true;

                }else{
                    result.put(MyResponseHeader.success,MyResponseHeader.success_code_failed);
                    result.put(MyResponseHeader.message,"不支持手机号登录");
                }
            }else if(email != null){    //邮箱密码组合
                user = userMapper.getByEmail(email);
                //判断是否允许用户使用邮箱登录
                if(user != null && user.permitEmailLogon()){
                    checkPassword = true;
                }else{
                    result.put(MyResponseHeader.success,MyResponseHeader.success_code_failed);
                    result.put(MyResponseHeader.message,"不支持邮箱登录");
                }
            }

            //校验密码
            if(checkPassword && MD5Utils.checkqual(password,user.getPassword())){
                result.put(MyResponseHeader.date,user.getId());
                result.put(MyResponseHeader.message,"登录成功");
            }else {
                result.put(MyResponseHeader.success,MyResponseHeader.success_code_failed);
                result.put(MyResponseHeader.message,"用户名密码错误");
            }

        }else{
            //TODO 微信登录
        }
        return result;
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
