package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.common.MyResponse;
import com.originaldreams.usermanagercenter.service.RoleRoutersService;
import com.originaldreams.usermanagercenter.service.RoleService;
import com.originaldreams.usermanagercenter.service.RouterService;
import com.originaldreams.usermanagercenter.service.UserRolesService;
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
    private RoleService roleService;
    @Resource
    private RouterService routerService;
    @Resource
    private RoleRoutersService roleRoutersService;
    @Resource
    private UserRolesService userRolesService;

    @RequestMapping(value = "/getAllRoles" , method = RequestMethod.GET)
    public ResponseEntity getAllRoles(){
        return MyResponse.ok(roleService.getAll());
    }

    @RequestMapping(value = "/getRolesByUserId" , method = RequestMethod.GET)
    public ResponseEntity getRolesByUserId(Integer userId){
        if(userId == null || userId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(userRolesService.getRolesByUserId(userId));
    }

    @RequestMapping(value = "/getUsersByRoleId" , method = RequestMethod.GET)
    public ResponseEntity getUsersByRoleId(Integer roleId){
        if(roleId == null || roleId < 0){
            return MyResponse.badRequest("请求参数不合法");
        }
        return MyResponse.ok(userRolesService.getUsersByRoleId(roleId));
    }
//
//    @RequestMapping(value = "/getAllRouters" , method = RequestMethod.GET)
//    public ResponseEntity getAllRouters(){
//
//    }
//
//    @RequestMapping(value = "/getRoutersByRoleName" , method = RequestMethod.GET)
//    public ResponseEntity getRoutersByRoleName(){
//
//    }
//
//    @RequestMapping(value = "/getRolesByRouterId" , method = RequestMethod.GET)
//    public ResponseEntity getRolesByRouterId(){
//
//    }
}
