package com.originaldreams.usermanagercenter.entity;

import java.util.Date;
import java.util.List;

public class RoleRouters {
    /**
    * ID
    */
     private Integer id;
    /**
    * 角色ID
    */
     private Integer roleId;
    /**
    * 路由ID
    */
     private Integer routerId;
    /**
    * 创建时间
    */
     private Date createTime = new Date();
     public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public Integer getRoleId(){
           return this.roleId;
     }
     public void setRoleId(Integer roleId){
           this.roleId = roleId;
     }
     public Integer getRouterId(){
           return this.routerId;
     }
     public void setRouterId(Integer routerId){
           this.routerId = routerId;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }


@Override
    public String toString() {
        return "RoleRouters{" +
            "  id:" + id + "  roleId:" + roleId + "  routerId:" + routerId + "  createTime:" + createTime + 
        "}";
    }
  }
