package com.originaldreams.usermanager.model.mapper;

import com.originaldreams.usermanager.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    String TABLE_NAME = "user";

    @Select("SELECT username, password FROM TABLE_NAME")
    User findByUsername(@Param("username") String username);

    @Insert("INSERT INTO TABLE_NAME (username, password) VALUES(#{user.username}, #{user.password})")
    User saveUser(User user);
}
