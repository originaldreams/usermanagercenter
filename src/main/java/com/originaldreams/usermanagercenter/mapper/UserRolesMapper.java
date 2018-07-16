package com.originaldreams.usermanagercenter.mapper;

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


     @Select("SELECT id, userId, roleId, createTime FROM " + tableName + " WHERE id = #{id}")
     UserRoles getById(Integer Id);

     @Select("SELECT id, userId, roleId, createTime FROM " + tableName)
     List<UserRoles> getAll();

     @Insert("INSERT INTO " + tableName + "(id, userId, roleId, createTime) VALUES (#{id}, #{userId}, #{roleId}, #{createTime})")
     Integer insert(UserRoles userRoles);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET userId=#{userId}, roleId=#{roleId}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(UserRoles userRoles);


}
