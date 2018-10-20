package com.originaldreams.passport.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    /**
     * 未登录或无权限时触发的操作
     * 返回  {"code":401,"message":"小弟弟，你没有携带 token 或者 token 无效！","data":""}
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回json形式的错误信息

        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        // TODO
        httpServletResponse.getWriter().println("{\"code\":401,\"message\":\"你没有携带 token 或者 token 无效！\"}");
        httpServletResponse.getWriter().flush();
    }

}
