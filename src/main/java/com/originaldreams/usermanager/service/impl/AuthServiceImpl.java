package com.originaldreams.usermanager.service.impl;

import com.originaldreams.usermanager.auth.SecurityProvider;
import com.originaldreams.usermanager.config.SecurityConstant;
import com.originaldreams.usermanager.exception.BadRequestException;
import com.originaldreams.usermanager.exception.ErrorDetails;
import com.originaldreams.usermanager.model.dto.LoginDTO;
import com.originaldreams.usermanager.model.dto.RegisterUserDTO;
import com.originaldreams.usermanager.model.entity.User;
import com.originaldreams.usermanager.model.mapper.RoleMapper;
import com.originaldreams.usermanager.model.mapper.UserMapper;
import com.originaldreams.usermanager.model.mapper.UserRoleMapper;
import com.originaldreams.usermanager.model.vo.LoginVO;
import com.originaldreams.usermanager.model.vo.RegisterUserVO;
import com.originaldreams.usermanager.service.AuthService;
import com.originaldreams.usermanager.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private SecurityProvider securityProvider;

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
        // TODO save role

        this.userMapper.saveUser(user);
        Long roleId = this.roleMapper.getIdByName(SecurityConstant.USER);
        if (null == roleId) {
            logger.warn("role == null , role_id = {}", roleId);
        }

        this.userRoleMapper.saveUserRole(user.getId(), roleId);

//        user.setRoles(Arrays.asList("ROLE_USER"));


        RegisterUserVO registerUserVO = RegisterUserVO.newInstanceWithUser(user, "注册成功");
        return registerUserVO;


    }

    @Override
    public LoginVO login(LoginDTO loginDTO) {

        final String username = loginDTO.getUsername();

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            ErrorDetails errorDetails = new ErrorDetails("用户名或密码错误");
            throw new BadRequestException(errorDetails);
        }
        // 查询Role
//        this.roleMapper.getIdByName(SecurityConstant.USER);



        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                username, loginDTO.getPassword());
//        SecurityProvider securityProvider = new SecurityProvider();
        final Authentication authentication = securityProvider.authenticate(upToken);
//        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        final String token = this.tokenUtil.generateToken(userDetails);
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
