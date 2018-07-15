package com.originaldreams.usermanagercenter.common;

public class MyRouterObject {
    private Integer id;
    private String serviceName;
    private String controllerName;
    private String methodName;
    private String routerUrl;
    private Long firstMask;
    private Long secondMask;

    public MyRouterObject(Integer id, String methodName, String routerUrl, Long firstMask, Long secondMask) {
        this.id = id;
        this.methodName = methodName;
        this.routerUrl = routerUrl;
        this.firstMask = firstMask;
        this.secondMask = secondMask;
        /*
        这里可能产生数组越界抛异常，当抛异常时，说明methodName不合法，需要修改
         */
        String[] array = methodName.split("_");
        this.serviceName = array[0];
        this.controllerName = array[1];
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }

    public Long getFirstMask() {
        return firstMask;
    }

    public void setFirstMask(Long firstMask) {
        this.firstMask = firstMask;
    }

    public Long getSecondMask() {
        return secondMask;
    }

    public void setSecondMask(Long secondMask) {
        this.secondMask = secondMask;
    }
}
