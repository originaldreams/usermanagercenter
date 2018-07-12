package com.originaldreams.usermanagercenter.service;

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

    public Map<String,Object> logon(String userName, String phone, String wxId, String email, String password){
        Map<String,Object> result = new HashMap<>();
        User user = null;
        result.put(MyResponseHeader.success,MyResponseHeader.success_code_success);
        if(password != null){
            if(userName != null){
                user = userMapper.getByUserName(userName);
                if(user != null && user.permitUserNameLogon()){

                }else{
                    result.put(MyResponseHeader.success,MyResponseHeader.success_code_failed);
                    result.put(MyResponseHeader.message,"不支持用户名登录");
                }
            }else if(phone != null){
                user = userMapper.getByPhone(phone);
                if(user != null && user.permitPhoneLogon()){

                }else{
                    result.put(MyResponseHeader.success,MyResponseHeader.success_code_failed);
                    result.put(MyResponseHeader.message,"不支持手机号登录");
                }
            }else if(email != null){
                user = userMapper.getByEmail(email);
                if(user != null && user.permitEmailLogon()){

                }else{
                    result.put(MyResponseHeader.success,MyResponseHeader.success_code_failed);
                    result.put(MyResponseHeader.message,"不支持邮箱登录");
                }
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
