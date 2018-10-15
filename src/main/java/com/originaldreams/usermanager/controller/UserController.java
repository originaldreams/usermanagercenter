package com.originaldreams.usermanager.controller;

import com.originaldreams.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/user")
@PreAuthorize("hasRole('USER')")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        // TODO
        return ResponseEntity.ok().body("");

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.userService.listUsers());
    }


}
