package com.originaldreams.passport.model.mapper;

import com.originaldreams.passport.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    String TABLE_NAME = "user";

//    @Select("SELECT username, password FROM TABLE_NAME")
    User findByUsername(String username);

//    @Insert("INSERT INTO" + TABLE_NAME + "(username, password) VALUES(#{user.username}, #{user.password})")
    void saveUser(User user);

    List<User> listAllUsers();
}
