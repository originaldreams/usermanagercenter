package com.originaldreams.usermanager.service.impl;

import com.originaldreams.usermanager.model.entity.User;
import com.originaldreams.usermanager.model.mapper.UserMapper;
import com.originaldreams.usermanager.service.UserService;
import com.originaldreams.usermanager.util.SecurityModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取 userDetail
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userMapper.getUserByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return SecurityModelFactory.create(user);
        }
    }

}
