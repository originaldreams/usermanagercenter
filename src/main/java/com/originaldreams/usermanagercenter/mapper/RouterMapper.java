package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.Router;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface RouterMapper {
    String tableName = "Router";


     @Select("SELECT id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask FROM " + tableName + " WHERE id = #{id}")
     Router getById(Integer Id);

     @Select("SELECT id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask FROM " + tableName + " WHERE methodName = #{methodName}")
     Router getByMethodName(String methodName);

     @Select("SELECT id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask FROM " + tableName)
     List<Router> getAll();

     @Insert("INSERT INTO " + tableName + "(id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask) VALUES (#{id}, #{serviceName}, #{controllerName}, #{methodName}, #{routerUrl}, #{firstMask}, #{secondMask})")
     Integer insert(Router router);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);

     @Update("UPDATE " + tableName + " SET serviceName=#{serviceName}, controllerName=#{controllerName}, methodName=#{methodName}, routerUrl=#{routerUrl}, firstMask=#{firstMask}, secondMask=#{secondMask} WHERE id = #{id}")
     Integer update(Router router);

     @Delete("DELETE FROM " + tableName )
     Integer deleteAll();

}
