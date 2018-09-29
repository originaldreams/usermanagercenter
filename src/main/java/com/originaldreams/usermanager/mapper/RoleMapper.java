package com.originaldreams.usermanager.mapper;

import com.originaldreams.usermanagercenter.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {
    String TABLE_NAME = "role";
    String USER_ROLES = "user_roles";
    String ROLE_ROUTERS = "role_routers";
     @Select("SELECT id, name, description, createTime FROM " + TABLE_NAME + " WHERE id = #{id}")
     Role getById(Integer Id);

     @Select("SELECT id, name, description, createTime FROM " + TABLE_NAME)
     List<Role> getAll();

     @Select("SELECT id, name, description, createTime FROM " + TABLE_NAME + " WHERE name = #{name}")
     Role getByName(Role role);

     @Select("SELECT b.id b, b.name , b.description , b.createTime  "
             +" FROM " + USER_ROLES + " a, "+ TABLE_NAME + " b "
             + " WHERE a.roleId = b.id AND a.userId = #{userId}" )
     Role getRoleByUserId(Integer userId);

     @Select("SELECT b.id, b.name, b.description, b.createTime "
         +" FROM " + ROLE_ROUTERS + " a, "+ TABLE_NAME + " b "
         + " WHERE a.roleId = b.id AND a.routerId = #{routerId}" )
     List<Role> getRolesByRouterId(Integer routerId);

     @Insert("INSERT INTO " + TABLE_NAME + "(name, description, createTime) VALUES (#{name}, #{description}, #{createTime})")
     @Options(useGeneratedKeys = true)
     Integer insert(Role role);

     @Delete("DELETE FROM " + TABLE_NAME + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + TABLE_NAME + " SET name=#{name}, description=#{description}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(Role role);


}
