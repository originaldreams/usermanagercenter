package com.originaldreams.usermanager.service;

import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.entity.UserRoles;

public interface RoleService {

    MyServiceResponse getAll();

    MyServiceResponse getRoleByUserId(int userId);

    MyServiceResponse getRolesByRouterId(int userId);

    MyServiceResponse addRoleForUser(UserRoles userRoles);

    MyServiceResponse deleteById(Integer id);

    MyServiceResponse insert(Role role);

    MyServiceResponse update(Role role);
}
