package com.originaldreams.usermanager.mapper;

import com.originaldreams.common.entity.Router;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RouterMapper {
    String TABLE_NAME = "router";
    String ROLE_ROUTERS = "role_routers";

     @Select("SELECT id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask,requestMethod FROM " + TABLE_NAME + " WHERE id = #{id}")
     Router getById(Integer Id);

     @Select("SELECT id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask,requestMethod FROM " + TABLE_NAME)
     List<Router> getAll();

     @Select({"SELECT a.id, a.serviceName, a.controllerName, a.methodName, a.routerUrl, a.firstMask, a.secondMask,a.requestMethod "
             + " FROM " + TABLE_NAME + " a ," + ROLE_ROUTERS + " b "
             + " WHERE a.id = b.routerId AND b.roleId = #{roleId}"
     })
     List<Router> getRoutersByRoleId(Integer roleId);

     @Insert("INSERT INTO " + TABLE_NAME + "(id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask,requestMethod) VALUES (#{id}, #{serviceName}, #{controllerName}, #{methodName}, #{routerUrl}, #{firstMask}, #{secondMask},#{requestMethod})")
     Integer insert(Router router);

     @Delete("DELETE FROM " + TABLE_NAME + " WHERE id = #{id}")
     Integer deleteById(Integer id);

     @Update("UPDATE " + TABLE_NAME + " SET serviceName=#{serviceName}, controllerName=#{controllerName}, methodName=#{methodName}, routerUrl=#{routerUrl}, firstMask=#{firstMask}, secondMask=#{secondMask} WHERE id = #{id}")
     Integer update(Router router);

     @Delete("DELETE FROM " + TABLE_NAME)
     Integer deleteAll();

}
