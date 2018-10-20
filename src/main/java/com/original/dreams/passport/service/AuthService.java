package com.original.dreams.passport.service;

import com.original.dreams.passport.model.dto.LoginDTO;
import com.original.dreams.passport.model.dto.RegisterUserDTO;
import com.original.dreams.passport.model.vo.LoginVO;
import com.original.dreams.passport.model.vo.RegisterUserVO;

public interface AuthService {

    LoginVO login(LoginDTO loginDTO);
    RegisterUserVO register(RegisterUserDTO registerUserDTO);
}
