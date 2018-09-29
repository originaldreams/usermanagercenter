package com.originaldreams.usermanager.mapper;

import com.originaldreams.usermanagercenter.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    String TABLE_NAME = "user_info";

    @Select("SELECT id, nickName, birthday, gender, address, signature, userPortrait, email, phone, createTime, mask FROM " + TABLE_NAME + " WHERE id = #{id}")
    UserInfo getById(Integer Id);

    @Select("SELECT id, nickName, birthday, gender, address, signature, userPortrait, email, phone, createTime, mask FROM " + TABLE_NAME)
    List<UserInfo> getAll();

    @Insert("INSERT INTO " + TABLE_NAME + "(id, nickName, birthday, gender, address, signature, userPortrait, email, phone, createTime, mask) VALUES (#{id}, #{nickName}, #{birthday}, #{gender}, #{address}, #{signature}, #{userPortrait}, #{email}, #{phone}, #{createTime}, #{mask})")
    Integer insert(UserInfo userInfo);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE id = #{id}")
    Integer deleteById(Integer id);

    @Update("UPDATE " + TABLE_NAME + " SET nickName=#{nickName}, birthday=#{birthday}, gender=#{gender}, address=#{address}, signature=#{signature}, userPortrait=#{userPortrait}, email=#{email}, phone=#{phone}, createTime=#{createTime}, mask=#{mask} WHERE id = #{id}")
    Integer update(UserInfo userInfo);
}
