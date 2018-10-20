package com.original.dreams.passport.service.impl;

import com.original.dreams.passport.model.mapper.UserMapper;
import com.original.dreams.passport.model.vo.UserVO;
import com.original.dreams.passport.service.UserService;
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
