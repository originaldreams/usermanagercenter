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
    String roleTable = "role";
    String userTable = "user_info";

     @Select("SELECT id, userId, roleId, createTime FROM " + tableName + " WHERE id = #{id}")
     UserRoles getById(Integer Id);

     @Select("SELECT id, userId, roleId, createTime FROM " + tableName)
     List<UserRoles> getAll();
     @Select("SELECT "+ userTable +".id, "
             + userTable +".name, "
             + userTable +".description, "
             + userTable +".createTime, "
             + userTable +".address, "
             + userTable +".signature, "
             + userTable +".userPortrait, "
             + userTable +".email, "
             + userTable +".phone, "
             + userTable +".createTime, "
             + userTable +".mask FROM " + tableName + " , "+ userTable
             + " WHERE " + tableName +".userId =" + userTable + ".id " + tableName + ".roleId = #{roleId}" )
     List<User> getUsersByRoleId(Integer roleId);

     @Select("SELECT "+ roleTable +".id, "
             + roleTable +".name, "
             + roleTable +".description, "
             + roleTable +".createTime "
             +" FROM " + tableName + " , "+ roleTable
             + " WHERE " + tableName +".roleId =" + roleTable + ".id " + tableName + ".userId = #{userId}" )
     List<Role> getRolesByUserId(Integer userId);
     @Insert("INSERT INTO " + tableName + "(id, userId, roleId, createTime) VALUES (#{id}, #{userId}, #{roleId}, #{createTime})")
     Integer insert(UserRoles userRoles);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET userId=#{userId}, roleId=#{roleId}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(UserRoles userRoles);





}
