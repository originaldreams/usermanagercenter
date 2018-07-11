package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.usermanagercenter.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * http连接测试类
 * 该类用于组件间http通信测试
 */
@RestController
@RequestMapping("/http")
public class HttpController {
    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(HttpController.class);

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity get(Integer id,String name){
        Map<String,Object> result = new HashMap<>();
        result.put("id",id);
        result.put("name",name);
        return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
    @RequestMapping(value = "/testGetToLogCenter" ,method = RequestMethod.GET)
    public ResponseEntity<String> testGetToLogCenter(Integer id,String name){
        ResponseEntity<String> responseEntity;
        if(id == null || name == null){ //不带参数的get请求
            responseEntity = restTemplate.getForEntity(
                    "http://LogCenter/http/get",String.class);
        } else{ //带参数的get请求
            Map<String ,Object> map = new HashMap<>();
            map.put("id",id);
            map.put("name",name);
            responseEntity = restTemplate.getForEntity(
                    "http://LogCenter/http/get?id={id}&name={name}",String.class,map);
        }
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        logger.info("test========" + result.toString());
        return responseEntity;
    }

    @RequestMapping(value = "/testPostToLogCenter" ,method = RequestMethod.GET)
    public ResponseEntity<String> testPostToLogCenter(Integer id,String name){
        ResponseEntity<String> responseEntity = null;
        try{
            if(id == null || name == null){ //不带参数的post请求
                responseEntity = restTemplate.postForEntity(
                        "http://LogCenter/http/post",null,String.class);
            } else{ //带map参数的post请求
                Map<String ,Object> map = new HashMap<>();
                map.put("id",id);
                map.put("name",name);
                responseEntity = restTemplate.postForEntity(
                        "http://LogCenter/http/post?id={id}&name={name}",null,String.class,map);
            }
            String body = responseEntity.getBody().toString();
            HttpStatus statusCode = responseEntity.getStatusCode();
            int statusCodeValue = responseEntity.getStatusCodeValue();
            HttpHeaders headers = responseEntity.getHeaders();
            StringBuffer result = new StringBuffer();
            result.append("responseEntity.getBody()：").append(body).append("<hr>")
                    .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                    .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                    .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
            logger.info("test========" + result.toString());
        }catch (HttpClientErrorException e){
            logger.error("HttpClientErrorException:" + e.getStatusCode());
        }
        return responseEntity;
    }
    @RequestMapping(value = "/testPostObjectToLogCenter" ,method = RequestMethod.GET)
    public ResponseEntity<User> testPostObjectToLogCenter(){
        ResponseEntity<User> responseEntity = null;
        try{
             User user = new User();
             user.setId(100);
             user.setCreateTime(new Date());
             user.setPhone("1212");
             user.setPassword("1212");
             user.setType("sadfa");
             responseEntity = restTemplate.postForEntity(
                     "http://LogCenter/http/postObject",user,User.class);
            User body = responseEntity.getBody();
            HttpStatus statusCode = responseEntity.getStatusCode();
            int statusCodeValue = responseEntity.getStatusCodeValue();
            HttpHeaders headers = responseEntity.getHeaders();
            StringBuffer result = new StringBuffer();
            result.append("responseEntity.getBody()：").append(body).append("<hr>")
                    .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                    .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                    .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
            logger.info("test========" + result.toString());
        }catch (HttpClientErrorException e){
            logger.error("HttpClientErrorException:" + e.getStatusCode());
        }
        return responseEntity;
    }
}
