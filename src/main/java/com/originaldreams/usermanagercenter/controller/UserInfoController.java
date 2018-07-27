package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private UserInfoService userInfoService;

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    ResponseEntity get(Integer id){
        if(id == null){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.getById(id));
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    ResponseEntity getAll(){
        List<UserInfo> result = userInfoService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.DELETE)
    ResponseEntity deleteById(Integer id){
        Integer result = userInfoService.deleteById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    ResponseEntity update(UserInfo userInfo){
        Integer result = userInfoService.update(userInfo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
