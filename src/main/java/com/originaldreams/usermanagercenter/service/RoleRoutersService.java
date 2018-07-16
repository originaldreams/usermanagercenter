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

    public Integer insert(RoleRouters roleRouters){
        return roleRoutersMapper.insert(roleRouters);
    }

}
