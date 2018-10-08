package com.originaldreams.usermanager.controller;

import com.originaldreams.usermanager.model.dto.LoginDTO;
import com.originaldreams.usermanager.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            logger.warn("login bad request {}", loginDTO);
            return ResponseEntity.badRequest().body("");
        }


        logger.info("login {}", loginDTO);
        return ResponseEntity.ok().body(loginService.login(loginDTO));
    }



}
