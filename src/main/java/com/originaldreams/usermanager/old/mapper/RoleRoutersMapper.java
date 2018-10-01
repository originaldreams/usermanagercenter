package com.originaldreams.usermanager.old.mapper;

import com.originaldreams.usermanagercenter.entity.RoleRouters;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRoutersMapper {
    String TABLE_NAME = "role_routers";
    String USER_ROLES = "user_roles";

    @Select("SELECT  roleId, routerId, createTime FROM " + TABLE_NAME + " WHERE roleId = #{roleId}")
    List<RoleRouters> getByRoleId(Integer roleId);

    @Select("SELECT  roleId, routerId, createTime FROM " + TABLE_NAME + " WHERE routerId = #{routerId}")
    List<RoleRouters> getByRouterId(Integer routerId);

    @Select("SELECT  roleId, routerId, createTime FROM " + TABLE_NAME)
    List<RoleRouters> getAll();

    @Insert("INSERT INTO " + TABLE_NAME + "(roleId, routerId, createTime) VALUES (#{roleId}, #{routerId}, #{createTime})")
    Integer insert(RoleRouters roleRouters);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE roleId = #{roleId}")
    Integer deleteByroleId(Integer roleId);

    @Select({"SELECT a.roleId,a.routerId FROM " + TABLE_NAME + " a," + USER_ROLES + " b WHERE a.roleId = b.roleId AND b.userId = #{userId}"})
    List<RoleRouters> getByUserId(Integer userId);
}