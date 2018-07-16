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

@RestController
@RequestMapping("/permissionManager")
public class PermissionController {
    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private RouterService routerService;

    @RequestMapping(value = "/getAllRoles" , method = RequestMethod.GET)
    public ResponseEntity getAllRoles(){
        return MyResponse.ok(roleService.getAll());
    }

    @RequestMapping(value = "/getRolesByUserId" , method = RequestMethod.GET)
    public ResponseEntity getRolesByUserId(Integer userId){
        if(userId == null || userId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(roleService.getRolesByUserId(userId));
    }

    @RequestMapping(value = "/getUsersByRoleId" , method = RequestMethod.GET)
    public ResponseEntity getUsersByRoleId(Integer roleId){
        if(roleId == null || roleId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(userService.getUsersByRoleId(roleId));
    }

    @RequestMapping(value = "/getAllRouters" , method = RequestMethod.GET)
    public ResponseEntity getAllRouters(){
        return MyResponse.ok(routerService.getAll());
    }

//    @RequestMapping(value = "/getRoutersByRoleId" , method = RequestMethod.GET)
//    public ResponseEntity getRoutersByRoleId(){
//
//    }
//
//    @RequestMapping(value = "/getRolesByRouterId" , method = RequestMethod.GET)
//    public ResponseEntity getRolesByRouterId(){
//
//    }
}
