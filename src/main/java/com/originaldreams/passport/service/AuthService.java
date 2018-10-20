package com.originaldreams.passport.service;

import com.originaldreams.passport.model.dto.LoginDTO;
import com.originaldreams.passport.model.dto.RegisterUserDTO;
import com.originaldreams.passport.model.vo.LoginVO;
import com.originaldreams.passport.model.vo.RegisterUserVO;

public interface AuthService {

    LoginVO login(LoginDTO loginDTO);
    RegisterUserVO register(RegisterUserDTO registerUserDTO);
}
