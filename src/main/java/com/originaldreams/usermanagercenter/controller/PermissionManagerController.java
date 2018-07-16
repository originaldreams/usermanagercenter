package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.common.MyResponse;
import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.entity.RoleRouters;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import com.originaldreams.usermanagercenter.service.RoleService;
import com.originaldreams.usermanagercenter.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户权限管理控制
 * 提供用户权限相关操作功能（包括角色划分、角色分配、授权等）
 */
@RestController
@RequestMapping("/permissionManager")
public class PermissionManagerController {
    private Logger logger = LoggerFactory.getLogger(PermissionManagerController.class);

    @Resource
    private RoleService roleService;
    @Resource
    private RouterService routerService;

    /*
     * 1.添加角色
     * 2.添加用户角色
     * 3.添加角色权限
     */

    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    @RequestMapping(value = "addRole" , method = RequestMethod.POST)
    public ResponseEntity addRole(String name,String description){
        if(name == null || description == null){
            return MyResponse.badRequest("角色名或描述不能为空");
        }
        Role role = new Role(name,description);
        return MyResponse.ok(roleService.insert(role));
    }

    /**
     * 为用户添加角色
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "addRoleForUser" , method = RequestMethod.POST)
    public ResponseEntity addRoleForUser(Integer userId,Integer roleId){
        if(userId == null || roleId == null){
            return MyResponse.badRequest("参数异常");
        }
        UserRoles userRoles = new UserRoles(userId, roleId);
        return MyResponse.ok(roleService.addRoleForUser(userRoles));
    }

    /**
     * 为角色添加权限
     * @param roleId
     * @param routerId
     * @return
     */
    @RequestMapping(value = "addRouterForRole" , method = RequestMethod.POST)
    public ResponseEntity addRouterForRole(Integer roleId,Integer routerId){
        if(roleId == null || routerId == null){
            return MyResponse.badRequest("参数异常");
        }
        RoleRouters roleRouters = new RoleRouters(roleId,routerId);
        return MyResponse.ok(routerService.addRouterForRole(roleRouters));
    }


}
