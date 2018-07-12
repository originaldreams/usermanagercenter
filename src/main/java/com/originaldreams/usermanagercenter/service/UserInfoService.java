package com.originaldreams.usermanagercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.mapper.UserInfoMapper;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;



    public UserInfo getById(Integer id){

        return userInfoMapper.getById(id);
    }


    public List<UserInfo> getAll(){
        return userInfoMapper.getAll();
    }

    public Integer insert(UserInfo userInfo){
        return userInfoMapper.insert(userInfo);
    }

    public Integer deleteById(Integer id){
        return userInfoMapper.deleteById(id);
    }

    public Integer update(UserInfo userInfo){
        return userInfoMapper.update(userInfo);
    }


}
