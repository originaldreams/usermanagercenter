package com.originaldreams.usermanager.service;

import com.originaldreams.common.entity.Router;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.usermanagercenter.entity.RoleRouters;

public interface RouterService {

    void initRouters();

    MyServiceResponse getAll();

    MyServiceResponse getRoutersByRoleId(int roleId);

    MyServiceResponse addRouterForRole(RoleRouters roleRouters);

    MyServiceResponse getRouterIdsByUserId(Integer userId);

    Integer insert(Router router);

    Integer update(Router router);


}
