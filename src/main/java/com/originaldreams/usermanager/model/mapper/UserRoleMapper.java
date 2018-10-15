package com.originaldreams.usermanager.model.mapper;

import com.originaldreams.usermanager.model.entity.Role;
import com.originaldreams.usermanager.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
@Mapper
public interface UserRoleMapper {

    String TABLE_NAME = "user_role";

    void removeUserRole(Long userId, Long roleId);
    void saveUserRole(Long userId, Long roleId);
    List<Role> listAllRolesByUserId(Long userId);
    List<User> listAllUsers(Long roleId);
}
