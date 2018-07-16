package com.originaldreams.usermanagercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import com.originaldreams.usermanagercenter.mapper.UserRolesMapper;
import java.util.List;

@Service
public class UserRolesService {
    @Autowired
    private UserRolesMapper userRolesMapper;

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
