package com.originaldreams.usermanager.config;

import com.originaldreams.usermanager.auth.AuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author MAMIAN
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 需携带有效token
                .antMatchers("/auth").authenticated()
                //  // 需拥有 admin 这个权限
                .antMatchers("/admin").hasAnyAuthority("admin")
                // 需拥有 ADMIN 这个身份
                .antMatchers("/ADMIN").hasAnyAuthority("ADMIN")

                // 允许所有请求通过
                .anyRequest().permitAll()
                .and()
                // 禁用 Spring Security 自带的csrf处理
                .csrf().disable()
                // 定制我们自己的 session 策略
                .sessionManagement()
                // 调整为让 Spring Security 不创建和使用 session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }


}
