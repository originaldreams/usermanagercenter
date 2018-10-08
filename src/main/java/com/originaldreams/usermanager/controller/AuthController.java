package com.originaldreams.usermanager.controller;

import com.originaldreams.usermanager.model.dto.LoginDTO;
import com.originaldreams.usermanager.model.dto.RegisterUserDTO;
import com.originaldreams.usermanager.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/api/auth")
@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            logger.warn("login bad request {}", loginDTO);
            return ResponseEntity.badRequest().body("");
        }


        logger.info("login {}", loginDTO);
        return ResponseEntity.ok().body(authService.login(loginDTO));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("");
        }

        return ResponseEntity.ok().body(authService.register(registerUserDTO));
    }
}
