package com.originaldreams.usermanager.service.impl;

import com.originaldreams.usermanager.model.dto.LoginDTO;
import com.originaldreams.usermanager.model.mapper.UserMapper;
import com.originaldreams.usermanager.model.vo.LoginVO;
import com.originaldreams.usermanager.service.LoginService;
import com.originaldreams.usermanager.util.TokenDetail;
import com.originaldreams.usermanager.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        LoginVO loginVO = new LoginVO();
        userMapper.getUserByUserName(loginDTO.getUsername());

        TokenDetail tokenDetail = new TokenDetail(loginDTO.getUsername());

        loginVO.setMessage("成功");

        String token = tokenUtil.generateToken(tokenDetail);

        loginVO.setToken(token);

        return loginVO;
    }
}
