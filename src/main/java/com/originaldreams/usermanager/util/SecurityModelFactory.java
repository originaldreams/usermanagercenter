package com.originaldreams.usermanager.util;

import com.originaldreams.usermanager.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.Date;

public class SecurityModelFactory {

    public static UserDetailImpl create(User user) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }

        Date lastPasswordReset = new Date();
        lastPasswordReset.setTime(user.getPasswordGmtModified().getTime());
        return UserDetailImpl.newInstance(
                user.getUserName(),
                user.getUserName(),
                user.getPassword(),
                lastPasswordReset,
                authorities,
                user.isEnable()
        );
    }

}