package com.original.dreams.passport.service.impl;

import com.original.dreams.passport.model.entity.JwtUserFactory;
import com.original.dreams.passport.model.entity.Role;
import com.original.dreams.passport.model.entity.User;
import com.original.dreams.passport.model.mapper.RoleMapper;
import com.original.dreams.passport.model.mapper.UserMapper;
import com.original.dreams.passport.model.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 获取 userDetail
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userMapper.findByUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<Role> roleList = this.userRoleMapper.listAllRolesByUserId(user.getId());
            List<String> roleNames = new ArrayList<>();
            for (Role role : roleList) {
                roleNames.add(role.getName());
            }
//            user.setRoles(roleNames);
            return JwtUserFactory.create(user, roleNames);
        }
    }

    private Collection<GrantedAuthority> getAuthorities(String role) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

}
