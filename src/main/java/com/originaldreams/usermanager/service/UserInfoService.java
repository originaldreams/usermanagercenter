package com.originaldreams.usermanager.service;

import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.usermanagercenter.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    MyServiceResponse getById(Integer id);

    List<UserInfo> getAll();

    Integer update(UserInfo userInfo);
}
