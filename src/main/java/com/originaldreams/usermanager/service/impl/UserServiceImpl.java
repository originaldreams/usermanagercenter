package com.originaldreams.usermanager.service.impl;

import com.originaldreams.common.encryption.MyMD5Utils;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.common.router.MyRouter;
import com.originaldreams.common.util.ResponseUtils;
import com.originaldreams.usermanager.mapper.UserInfoMapper;
import com.originaldreams.usermanager.mapper.UserMapper;
import com.originaldreams.usermanager.service.UserService;
import com.originaldreams.usermanager.utils.LoginUtils;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-08-19 12:02:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 判断是否使用密码登录
     *  如果使用密码登录，看是用户名密码、手机号密码、邮箱密码的哪种组合
     *      如果密码校验无误，返回用户ID
     *      如果密码校验失败或不支持指定的密码登录组合，则返回相应的错误信息
     *  如果不是密码登录，看是否是微信登录
     * @param user
     * @return
     */
    @Override
    public MyServiceResponse login(User user)  {
        User checker = null;
        boolean checkPassword = false;
        Integer way = null;
        MyServiceResponse responseObject = new MyServiceResponse();
        RestTemplate restTemplate = new RestTemplate();
        if(user.getPassword() != null){
            //使用密码登录
            if(user.getUserName() != null){
                //用户名密码组合
                checker = userMapper.getByUserName(user);
                //判断是否允许用户使用用户名登录
                if(checker != null && checker.isPermitUserNameLogon()){
                    checkPassword = true;
                    way = LoginUtils.LOGON_WAY_USERNAME;
                }else{
                    responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
                    responseObject.setMessage("该用户不存在或不支持用户名登录");
                }
            }else if(user.getPhone() != null){
                //手机号密码组合
                checker = userMapper.getByPhone(user);
                //判断是否允许用户使用手机号登录
                if(checker != null && checker.isPermitPhoneLogon()){
                    checkPassword = true;
                    way = LoginUtils.LOGON_WAY_PHONE;
                }else{
                    responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
                    responseObject.setMessage("该用户不存在或不支持手机号登录");
                }
            }else if(user.getEmail() != null){
                //邮箱密码组合
                checker = userMapper.getByEmail(user);
                //判断是否允许用户使用邮箱登录
                if(checker != null && checker.isPermitEmailLogon()){
                    checkPassword = true;
                    way = LoginUtils.LOGON_WAY_EMAIL;
                }else{
                    responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
                    responseObject.setMessage("该用户不存在或不支持邮箱登录");
                }
            }
            try{
                /*
                 *  经过上面的检查，在这里
                 *  如果需要校验密码，则进入校验密码环节，
                 *  否则，说明前面的检查环节有问题，直接返回检查环节封装好的应答
                 */
                if(checkPassword){
                    if(MyMD5Utils.checkqual(user.getPassword(),checker.getPassword())){
                        responseObject.setData(checker.getId());

                        Map<String, Object> map = new HashMap<>();
                        map.put("userId",checker.getId());
                        map.put("type", LoginUtils.LOGON_TYPE_LOGON);
                        map.put("way", way);
                        map.put("ip","ddd");

                        //记录登录日志
                        ResponseEntity<String> responseEntity = restTemplate.postForEntity(MyRouter.LOG_LOGON_LOG_INSERT +
                                "?userId={userId}&type={type}&way={way}&ip={ip}",null,String.class,map);
                        logger.info("logonLog Ok: " + responseEntity.getBody());
                    }else {
                        responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_SUCCESS);
                        responseObject.setMessage("用户名密码错误");
                    }
                }else {
                    return responseObject;
                }

            }catch (Exception e){
                logger.error("校验密码异常 ",e);
                responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
                responseObject.setMessage("用户名密码错误");
            }


        }else{
            //TODO 微信登录
        }
        return responseObject;
    }

    /**
     *  注册时，只允许手机号或者邮箱注册
     *  检查用户名、手机号、邮箱是否已被注册
     *  保证用户名、手机号、邮箱的唯一
     * @param user
     * @return
     */
    @Override
    public MyServiceResponse registerByPhone(User user,String verificationCode) {
        User checker;
        MyServiceResponse responseObject = new MyServiceResponse();

        RestTemplate restTemplate = new RestTemplate();

        if(user.getPhone() == null || user.getPassword() == null){
            responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
            responseObject.setMessage("参数异常");
            return responseObject;
        }
        //手机号密码组合
        checker = userMapper.getByPhone(user);
        //检查手机号是否已存在
        if(checker != null){
            responseObject.setMessage("手机号已注册");
            responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
            return responseObject;
        }
        //TODO 去logCenter核对短信验证码发送记录
        Map<String, Object> map = new HashMap<>();
        map.put("phone",user.getPhone());
        map.put("codeStr",verificationCode);
        //验证短信验证码
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(MyRouter.LOG_SMSLOG_CHECK_AND_UPDATE_STATE +
                "?phone={phone}&codeStr={codeStr}",String.class,map);
        if(ResponseUtils.isSuccess(responseEntity)){
            return register(user);
        }else {
            responseObject.setMessage("验证码错误");
            responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
            return responseObject;
        }
    }
    private MyServiceResponse register (User user) {
        MyServiceResponse responseObject = new MyServiceResponse();
        String md5 = "";
        try {
             md5 = MyMD5Utils.EncoderByMd5(user.getPassword());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("encode md5 error, id = {}", user.getId());
        }

        user.setPassword(md5);
        userMapper.insert(user);
        UserInfo userInfo = new UserInfo(user.getId(),user.getPhone(),user.getEmail());
        userInfoMapper.insert(userInfo);
        responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_SUCCESS);
        responseObject.setData(user.getId());
        return responseObject;
    }

    @Override
    public MyServiceResponse registerByEmail(User user,String verificationCode) {
        User checker;
        MyServiceResponse responseObject = new MyServiceResponse();
        if(user.getEmail() == null || user.getPassword() == null) {
            responseObject.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
            responseObject.setMessage("参数异常");
            return responseObject;
        }
        //邮箱密码组合
        checker = userMapper.getByEmail(user);
        //检查邮箱是否已存在
        if(checker != null){
            responseObject.setMessage("邮箱已注册");
            return responseObject;
        }
        //TODO 去logCenter核对邮件发送记录
        return register(user);
    }
    /**
     * 根据角色查找用户
     * @param roleId
     * @return
     */
    public MyServiceResponse getUsersByRoleId(int roleId) {
        return new MyServiceResponse(userMapper.getUsersByRoleId(roleId));
    }

    public MyServiceResponse getAllUserNameAndRoleName() {
        return new MyServiceResponse(userMapper.getAllUserNameAndRoleName());
    }


    public User getById(Integer id){

        return userMapper.getById(id);
    }

    public List<User> getAll(){
        return userMapper.getAll();
    }

    public Integer insert(User user){
        return userMapper.insert(user);
    }

    public Integer deleteById(Integer id){
        return userMapper.deleteById(id);
    }

    public Integer update(User user){
        return userMapper.update(user);
    }


}
