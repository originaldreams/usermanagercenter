package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.service.RoleService;
import com.originaldreams.usermanagercenter.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
