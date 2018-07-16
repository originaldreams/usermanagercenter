package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.common.MyResponse;
import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.service.RoleRoutersService;
import com.originaldreams.usermanagercenter.service.RoleService;
import com.originaldreams.usermanagercenter.service.RouterService;
import com.originaldreams.usermanagercenter.service.UserRolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户权限管理控制
 * 负责用户权限相关操作（包括角色划分、角色分配、授权等）
 */
@RestController
@RequestMapping("/permissionManager")
public class PermissionManagerController {
    private Logger logger = LoggerFactory.getLogger(PermissionManagerController.class);

    @Resource
    private RoleService roleService;
    @Resource
    private RouterService routerService;
    @Resource
    private RoleRoutersService roleRoutersService;
    @Resource
    private UserRolesService userRolesService;


}
