package com.originaldreams.usermanagercenter.entity;

import java.util.Date;
import java.util.List;

public class UserRoles {
    /**
    * ID
    */
     private Integer id;
    /**
    * 用户ID
    */
     private Integer userId;
    /**
    * 角色ID
    */
     private Integer roleId;
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
     public Integer getUserId(){
           return this.userId;
     }
     public void setUserId(Integer userId){
           this.userId = userId;
     }
     public Integer getRoleId(){
           return this.roleId;
     }
     public void setRoleId(Integer roleId){
           this.roleId = roleId;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }


@Override
    public String toString() {
        return "UserRoles{" +
            "  id:" + id + "  userId:" + userId + "  roleId:" + roleId + "  createTime:" + createTime + 
        "}";
    }
  }
