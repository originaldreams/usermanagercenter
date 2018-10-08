package com.originaldreams.usermanager.controller;

import com.originaldreams.usermanager.model.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/user")
@RestController
public class UserController {

    @GetMapping
    public UserVO getUser() {
        // TODO 
    }

}
