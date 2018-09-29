package com.originaldreams.usermanager.service;

import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.usermanagercenter.entity.User;

public interface UserService {

    MyServiceResponse registerByPhone(User user, String verificationCode);

    MyServiceResponse registerByEmail(User user, String verificationCode);

    MyServiceResponse login(User user);

    MyServiceResponse getUsersByRoleId(int roleId);

    MyServiceResponse getAllUserNameAndRoleName();
}
