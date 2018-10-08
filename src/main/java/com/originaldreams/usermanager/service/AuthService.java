package com.originaldreams.usermanager.service;

import com.originaldreams.usermanager.model.dto.LoginDTO;
import com.originaldreams.usermanager.model.dto.RegisterUserDTO;
import com.originaldreams.usermanager.model.vo.LoginVO;
import com.originaldreams.usermanager.model.vo.RegisterUserVO;

public interface AuthService {

    LoginVO login(LoginDTO loginDTO);
    RegisterUserVO register(RegisterUserDTO registerUserDTO);
}
