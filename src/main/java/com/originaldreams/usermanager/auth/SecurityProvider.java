package com.originaldreams.usermanager.auth;

import com.originaldreams.usermanager.exception.BadRequestException;
import com.originaldreams.usermanager.service.impl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(SecurityProvider.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;

        if (null == token || null == token.getPrincipal() ) {
            logger.warn("token = null, {}", token);
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername((String) token.getPrincipal());
        if (userDetails == null) {
            throw new BadRequestException("用户名或密码错误");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String inputPassword = token.getCredentials().toString();
        if (!encoder.matches(inputPassword, userDetails.getPassword())) {
            throw new BadRequestException("用户名或密码错误");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
