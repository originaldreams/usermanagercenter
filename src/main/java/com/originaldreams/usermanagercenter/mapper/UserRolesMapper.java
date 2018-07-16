package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface UserRolesMapper {
    String tableName = "user_roles";

     @Select("SELECT userId, roleId, createTime FROM " + tableName + " WHERE userId = #{userId}")
     UserRoles getByUserId(Integer userId);

     @Select("SELECT userId, roleId, createTime FROM " + tableName + " WHERE roleId = #{roleId}")
     UserRoles getByRoleId(Integer roleId);

     @Select("SELECT id, userId, roleId, createTime FROM " + tableName)
     List<UserRoles> getAll();

     @Insert("INSERT INTO " + tableName + "(userId, roleId, createTime) VALUES (#{userId}, #{roleId}, #{createTime})")
     Integer insert(UserRoles userRoles);







}
