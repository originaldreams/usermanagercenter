package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.common.MyResponse;
import com.originaldreams.usermanagercenter.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  用户权限查询控制
 *  提供用户权限查询功能
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private RouterService routerService;

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping(value = "/getAllRoles" , method = RequestMethod.GET)
    public ResponseEntity getAllRoles(){
        return MyResponse.ok(roleService.getAll());
    }

    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getRolesByUserId" , method = RequestMethod.GET)
    public ResponseEntity getRolesByUserId(Integer userId){
        if(userId == null || userId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(roleService.getRolesByUserId(userId));
    }

    /**
     * 查询包含某个权限的角色
     * @param routerId
     * @return
     */
    @RequestMapping(value = "/getRolesByRouterId" , method = RequestMethod.GET)
    public ResponseEntity getRolesByRouterId(Integer routerId){
        if(routerId == null || routerId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(roleService.getRolesByRouterId(routerId));
    }

    /**
     * 查询拥有某个角色的用户
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getUsersByRoleId" , method = RequestMethod.GET)
    public ResponseEntity getUsersByRoleId(Integer roleId){
        if(roleId == null || roleId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(userService.getUsersByRoleId(roleId));
    }

    /**
     * 查询所有接口（权限）
     * @return
     */
    @RequestMapping(value = "/getAllRouters" , method = RequestMethod.GET)
    public ResponseEntity getAllRouters(){
        return MyResponse.ok(routerService.getAll());
    }

    /**
     * 查询某个角色的所有权限
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getRoutersByRoleId" , method = RequestMethod.GET)
    public ResponseEntity getRoutersByRoleId(Integer roleId){
        if(roleId == null || roleId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(routerService.getRoutersByRoleId(roleId));
    }
}