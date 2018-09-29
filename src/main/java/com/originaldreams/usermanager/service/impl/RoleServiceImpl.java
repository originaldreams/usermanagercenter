package com.originaldreams.usermanager.service.impl;

import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.usermanager.mapper.RoleMapper;
import com.originaldreams.usermanager.mapper.RoleRoutersMapper;
import com.originaldreams.usermanager.service.RoleService;
import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import com.originaldreams.usermanager.mapper.UserRolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangkaile
 * @date 2018-09-05 08:54:10
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private RoleRoutersMapper roleRoutersMapper;

    @Override
    public MyServiceResponse getAll(){
        return new MyServiceResponse(roleMapper.getAll());
    }

    @Override
    public MyServiceResponse getRoleByUserId(int userId){
        return new MyServiceResponse(roleMapper.getRoleByUserId(userId));
    }

    @Override
    public MyServiceResponse getRolesByRouterId(int userId){
        return new MyServiceResponse(roleMapper.getRolesByRouterId(userId));
    }

    @Override
    public MyServiceResponse addRoleForUser(UserRoles userRoles){
        UserRoles result = userRolesMapper.getByUserId(userRoles.getUserId());
        if(result == null){
            return new MyServiceResponse(userRolesMapper.insert(userRoles));
        }
        return new MyServiceResponse(userRolesMapper.update(userRoles));
    }

    @Override
    public MyServiceResponse deleteById(Integer id){
        userRolesMapper.deleteByRoleId(id);
        roleRoutersMapper.deleteByroleId(id);
        roleMapper.deleteById(id);
        return new MyServiceResponse("已删除");
    }


    public Role getById(Integer id){

        return roleMapper.getById(id);
    }

    @Override
    public MyServiceResponse insert(Role role){
        Role checker = roleMapper.getByName(role);
        if(checker != null){
            return new MyServiceResponse(MyServiceResponse.SUCCESS_CODE_FAILED,"角色名已存在");
        }
        roleMapper.insert(role);
        return new MyServiceResponse(role.getId());
    }


    @Override
    public MyServiceResponse update(Role role){
        return new MyServiceResponse(roleMapper.update(role));
    }


}
