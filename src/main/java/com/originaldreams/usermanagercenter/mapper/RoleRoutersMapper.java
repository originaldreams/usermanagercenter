package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.RoleRouters;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface RoleRoutersMapper {
    String tableName = "role_routers";

    @Select("SELECT  roleId, routerId, createTime FROM " + tableName + " WHERE roleId = #{roleId}")
    List<RoleRouters> getByRoleId(Integer roleId);

    @Select("SELECT  roleId, routerId, createTime FROM " + tableName + " WHERE routerId = #{routerId}")
    List<RoleRouters> getByRouterId(Integer routerId);

    @Select("SELECT  roleId, routerId, createTime FROM " + tableName)
    List<RoleRouters> getAll();

    @Insert("INSERT INTO " + tableName + "(roleId, routerId, createTime) VALUES (#{roleId}, #{routerId}, #{createTime})")
    Integer insert(RoleRouters roleRouters);

    @Delete("DELETE FROM " + tableName + " WHERE roleId = #{roleId}")
    Integer deleteByroleId(Integer roleId);
}
