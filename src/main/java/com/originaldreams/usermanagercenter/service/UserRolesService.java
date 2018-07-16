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

    public MyServiceResponse getRolesByUserId(int userId){
        return new MyServiceResponse(userRolesMapper.getRolesByUserId(userId));
    }
    public MyServiceResponse getUsersByRoleId(int roleId){
        return new MyServiceResponse(userRolesMapper.getUsersByRoleId(roleId));
    }

    public UserRoles getById(Integer id){

        return userRolesMapper.getById(id);
    }

    public List<UserRoles> getAll(){
        return userRolesMapper.getAll();
    }

    public Integer insert(UserRoles userRoles){
        return userRolesMapper.insert(userRoles);
    }

    public Integer deleteById(Integer id){
        return userRolesMapper.deleteById(id);
    }

    public Integer update(UserRoles userRoles){
        return userRolesMapper.update(userRoles);
    }


}
