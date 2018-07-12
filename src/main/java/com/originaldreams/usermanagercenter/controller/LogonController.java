package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/logon")
public class LogonController {
    private Logger logger = LoggerFactory.getLogger(LogonController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    ResponseEntity logon(String userName,String phone,String wxId,String email,String password){
        try{
            if(userName == null && phone == null && email == null && password == null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(userService.logon( userName, phone, wxId, email, password));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON).body("Error:请求参数异常");
            }
        }catch (Exception e){
            logger.error("logonError :" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON).body("Error:请求参数异常");
        }

    }
}
