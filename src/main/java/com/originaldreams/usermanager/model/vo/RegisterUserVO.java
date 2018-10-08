package com.originaldreams.usermanager.model.vo;

import com.originaldreams.usermanager.model.entity.User;
import org.springframework.beans.BeanUtils;

public class RegisterUserVO {

    private String username;
    private String message;

    public static RegisterUserVO newInstanceWithUser(User user, String message) {
        RegisterUserVO registerUserVO = new RegisterUserVO();
        BeanUtils.copyProperties(user, registerUserVO);
        registerUserVO.setMessage(message);
        return registerUserVO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
