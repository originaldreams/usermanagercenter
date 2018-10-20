package com.originaldreams.passport.service.impl;

import com.originaldreams.passport.model.mapper.UserMapper;
import com.originaldreams.passport.model.vo.UserVO;
import com.originaldreams.passport.service.UserService;
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
