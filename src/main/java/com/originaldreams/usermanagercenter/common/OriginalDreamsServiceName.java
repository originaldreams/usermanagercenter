package com.originaldreams.usermanagercenter.common;

/**
 * 维护组件名及组件对外公开的接口
 * 当新增了组件或接口，需要在该类中注册，详细说明新增的组件或接口
 * 当一个组件需要调用其他组件时，将该类复制到组件下的commom包中即可
 */
public class OriginalDreamsServiceName {
    /**
     * 服务注册中心
     * 维护所有已启动的组件
     */
    public final static String ServiceRegistryCenter = "ServiceRegistryCenter";

    /**
     * 用户管理中心
     * 维护用户信息，负责用户登录、注册
     */
    public final static String UserManagerCenter = "UserManagerCenter";

    /**
     * 日志中心
     * 负责所有组件的日志记录、查询等功能
     */
    public final static String LogCenter = "LogCenter";

    /**
     * 外部服务中心
     * 负责提供邮件发送、短信发送等对外服务
     */
    public final static String PublicServiceCenter = "PublicServiceCenter";

    /**
     * LogCenter提供的测试http get请求的接口
     * 接口参数：
     * id: Integer，null
     * name: String，null
     */
    public final static String LogCenter_Http_Get = "http://LogCenter/http/get";

    /**
     * LogCenter提供的测试http post请求的接口
     * 接口参数：
     * id: Integer，notNull
     * name: String，notNull
     */
    public final static String LogCenter_Http_Post = "http://LogCenter/http/post";


}
