package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {
    String tableName = "role";
    String userRoles = "user_roles";
    String roleRouters = "role_routers";
     @Select("SELECT id, name, description, createTime FROM " + tableName + " WHERE id = #{id}")
     Role getById(Integer Id);

     @Select("SELECT id, name, description, createTime FROM " + tableName)
     List<Role> getAll();

     @Select("SELECT "+ tableName +".id, "
             + tableName +".name, "
             + tableName +".description, "
             + tableName +".createTime "
             +" FROM " + userRoles + " , "+ tableName
             + " WHERE " + userRoles +".roleId =" + tableName + ".id " + userRoles + ".userId = #{userId}" )
     List<Role> getRolesByUserId(Integer userId);

     @Select("SELECT "+ tableName +".id, "
         + tableName +".name, "
         + tableName +".description, "
         + tableName +".createTime "
         +" FROM " + roleRouters + " , "+ tableName
         + " WHERE " + roleRouters +".roleId =" + tableName + ".id " + roleRouters + ".routerId = #{routerId}" )
     List<Role> getRolesByRouterId(Integer routerId);

     @Insert("INSERT INTO " + tableName + "(name, description, createTime) VALUES (#{name}, #{description}, #{createTime})")
     @Options(useGeneratedKeys = true)
     Integer insert(Role role);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET name=#{name}, description=#{description}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(Role role);


}
