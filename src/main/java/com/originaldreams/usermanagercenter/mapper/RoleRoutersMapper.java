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


     @Select("SELECT id, roleId, routerId, createTime FROM " + tableName + " WHERE id = #{id}")
     RoleRouters getById(Integer Id);

     @Select("SELECT id, roleId, routerId, createTime FROM " + tableName)
     List<RoleRouters> getAll();

     @Insert("INSERT INTO " + tableName + "(id, roleId, routerId, createTime) VALUES (#{id}, #{roleId}, #{routerId}, #{createTime})")
     Integer insert(RoleRouters roleRouters);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET roleId=#{roleId}, routerId=#{routerId}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(RoleRouters roleRouters);


}
