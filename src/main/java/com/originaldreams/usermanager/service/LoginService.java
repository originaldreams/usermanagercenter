package com.originaldreams.usermanager.service;

import com.originaldreams.usermanager.model.dto.LoginDTO;
import com.originaldreams.usermanager.model.vo.LoginVO;

public interface LoginService {

    LoginVO login(LoginDTO loginDTO);
}
