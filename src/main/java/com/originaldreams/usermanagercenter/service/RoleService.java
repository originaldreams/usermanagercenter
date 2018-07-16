package com.originaldreams.usermanagercenter.service;

import com.originaldreams.usermanagercenter.common.MyServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.mapper.RoleMapper;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public MyServiceResponse getAll(){
        return new MyServiceResponse(roleMapper.getAll());
    }

    public MyServiceResponse getRolesByUserId(int userId){
        return new MyServiceResponse(roleMapper.getRolesByUserId(userId));
    }
    public MyServiceResponse getRolesByRouterId(int userId){
        return new MyServiceResponse(roleMapper.getRolesByRouterId(userId));
    }
    public Role getById(Integer id){

        return roleMapper.getById(id);
    }

    public Integer insert(Role role){
        return roleMapper.insert(role);
    }

    public Integer deleteById(Integer id){
        return roleMapper.deleteById(id);
    }

    public Integer update(Role role){
        return roleMapper.update(role);
    }


}
