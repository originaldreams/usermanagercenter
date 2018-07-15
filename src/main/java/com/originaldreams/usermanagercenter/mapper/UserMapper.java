package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    String tableName = "user";


     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask,isDelete FROM " + tableName + " WHERE id = #{id} AND isDelete = 0")
     User getById(Integer Id);

     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask,isDelete FROM " + tableName + " isDelete = 0 ")
     List<User> getAll();

     @Insert("INSERT INTO " + tableName + "(userName, phone, wxId, email, password, createTime, mask) VALUES (#{userName}, #{phone}, #{wxId}, #{email}, #{password}, #{createTime}, #{mask})")
     @Options(useGeneratedKeys = true)
     Integer insert(User user);

     @Delete("UPDATE " + tableName + " SET isDelete = 1 , WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET userName=#{userName}, phone=#{phone}, wxId=#{wxId}, email=#{email}, password=#{password}, createTime=#{createTime}, mask=#{mask} WHERE id = #{id}")
     Integer update(User user);

     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask,isDelete FROM " + tableName + " WHERE userName = #{userName} AND isDelete = #{isDelete}")
     User getByUserName(String userName,int isDelete);

     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask,isDelete FROM " + tableName + " WHERE phone = #{phone} AND isDelete = #{isDelete}")
     User getByPhone(String phone,int isDelete);

     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask,isDelete FROM " + tableName + " WHERE wxId = #{wxId} AND isDelete = #{isDelete}")
     User getByWXId(String wxId,int isDelete);

     @Select("SELECT id, userName, phone, wxId, email, password, createTime, mask,isDelete FROM " + tableName + " WHERE email = #{email} AND isDelete = #{isDelete}")
     User getByEmail(String email,int isDelete);
}
