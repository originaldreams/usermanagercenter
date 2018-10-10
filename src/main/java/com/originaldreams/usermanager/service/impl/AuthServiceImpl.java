package com.originaldreams.usermanager.service.impl;

import com.originaldreams.usermanager.exception.BadRequestException;
import com.originaldreams.usermanager.exception.ErrorDetails;
import com.originaldreams.usermanager.model.dto.LoginDTO;
import com.originaldreams.usermanager.model.dto.RegisterUserDTO;
import com.originaldreams.usermanager.model.entity.User;
import com.originaldreams.usermanager.model.mapper.UserMapper;
import com.originaldreams.usermanager.model.vo.LoginVO;
import com.originaldreams.usermanager.model.vo.RegisterUserVO;
import com.originaldreams.usermanager.service.AuthService;
import com.originaldreams.usermanager.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public RegisterUserVO register(RegisterUserDTO registerUserDTO) {
        final String username = registerUserDTO.getUsername();
        if(userMapper.findByUsername(username) != null) {
            // 重名
            ErrorDetails errorDetails = new ErrorDetails<>("请勿重复注册", null);
            throw new BadRequestException(errorDetails);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = registerUserDTO.getPassword();
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(rawPassword));
        user.setLastPasswordResetDate(new Date());
        user.setRoles(Arrays.asList("ROLE_USER"));
        userMapper.saveUser(user);

        RegisterUserVO registerUserVO = RegisterUserVO.newInstanceWithUser(user, "注册成功");
        return registerUserVO;


    }

    @Override
    public LoginVO login(LoginDTO loginDTO) {

        final String username = loginDTO.getUsername();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                username, loginDTO.getPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = tokenUtil.generateToken(userDetails);
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setMessage("登录成功");
        return loginVO;
//        return token;

//        LoginVO loginVO = new LoginVO();
//        userMapper.getUserByUserName(loginDTO.getUsername());
//
//        TokenDetail tokenDetail = new TokenDetail(loginDTO.getUsername());
//
//        loginVO.setMessage("成功");
//
//        String token = tokenUtil.generateToken(tokenDetail);
//
//        loginVO.setToken(token);

//        return loginVO;
    }
}
