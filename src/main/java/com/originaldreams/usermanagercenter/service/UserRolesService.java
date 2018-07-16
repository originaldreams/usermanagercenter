package com.originaldreams.usermanagercenter.service;

import com.originaldreams.usermanagercenter.common.MyServiceResponse;
import com.originaldreams.usermanagercenter.mapper.RoleMapper;
import com.originaldreams.usermanagercenter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import com.originaldreams.usermanagercenter.mapper.UserRolesMapper;
import java.util.List;

@Service
public class UserRolesService {
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public Integer insert(UserRoles userRoles){
        return userRolesMapper.insert(userRoles);
    }

}
