package com.originaldreams.usermanagercenter.entity;

import java.util.Date;
import java.util.List;

public class Role {
    /**
    * 角色ID
    */
     private Integer id;
    /**
    * 角色名称
    */
     private String name;
    /**
    * 角色描述
    */
     private String description;
    /**
    * 角色描述
    */
     private Date createTime = new Date();
     public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public String getName(){
           return this.name;
     }
     public void setName(String name){
           this.name = name;
     }
     public String getDescription(){
           return this.description;
     }
     public void setDescription(String description){
           this.description = description;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }


@Override
    public String toString() {
        return "Role{" +
            "  id:" + id + "  name:" + name + "  description:" + description + "  createTime:" + createTime + 
        "}";
    }
  }
