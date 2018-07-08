package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface UserMapper {
    String tableName = "user";


     @Select("SELECT id, phone, password, createTime, type FROM " + tableName + " WHERE id = #{id}")
     User getById(Integer Id);

     @Select("SELECT id, phone, password, createTime, type FROM " + tableName)
     List<User> getAll();

     @Insert("INSERT INTO " + tableName + "(id, phone, password, createTime, type) VALUES (#{id}, #{phone}, #{password}, #{createTime}, #{type})")
     Integer insert(User user);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET phone=#{phone}, password=#{password}, createTime=#{createTime}, type=#{type} WHERE id = #{id}")
     Integer update(User user);


}
