package com.originaldreams.usermanager.model.mapper;

import com.originaldreams.usermanager.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    String TABLE_NAME = "user";

//    @Select("SELECT user.id, user.username, user.password, user.enable FROM TABLE_NAME WHERE username = #{username}")
    User getUserByUserName(@Param("username") String userName);

}
