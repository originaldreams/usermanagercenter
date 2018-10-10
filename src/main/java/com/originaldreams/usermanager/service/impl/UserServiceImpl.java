package com.originaldreams.usermanager.service.impl;

import com.originaldreams.usermanager.model.mapper.UserMapper;
import com.originaldreams.usermanager.model.vo.UserVO;
import com.originaldreams.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO listUsers() {
        return null;
    }
}
