package com.originaldreams.usermanagercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.RoleRouters;
import com.originaldreams.usermanagercenter.mapper.RoleRoutersMapper;
import java.util.List;

@Service
public class RoleRoutersService {
    @Autowired
    private RoleRoutersMapper roleRoutersMapper;

    public RoleRouters getById(Integer id){

        return roleRoutersMapper.getById(id);
    }

    public List<RoleRouters> getAll(){
        return roleRoutersMapper.getAll();
    }

    public Integer insert(RoleRouters roleRouters){
        return roleRoutersMapper.insert(roleRouters);
    }

    public Integer deleteById(Integer id){
        return roleRoutersMapper.deleteById(id);
    }

    public Integer update(RoleRouters roleRouters){
        return roleRoutersMapper.update(roleRouters);
    }


}
