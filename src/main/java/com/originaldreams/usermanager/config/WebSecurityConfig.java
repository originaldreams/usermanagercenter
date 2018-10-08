package com.originaldreams.usermanager.config;

import com.originaldreams.usermanager.auth.AuthenticationTokenFilter;
import com.originaldreams.usermanager.auth.EntryPointUnauthorizedHandler;
import com.originaldreams.usermanager.auth.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    /**
     * 注册 401 处理器
     */
    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    /**
     * 注册 403 处理器
     */
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/auth/**").permitAll()
                // 需携带有效token
                .antMatchers("/auth").authenticated()
                //  // 需拥有 admin 这个权限
                .antMatchers("/admin").hasAnyAuthority("admin")
                // 需拥有 ADMIN 这个身份
                .antMatchers("/ADMIN").hasAnyAuthority("ADMIN")

                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()

                // 其他请求
                .anyRequest().authenticated()
                .and()
                // 禁用 Spring Security 自带的csrf处理
                .csrf().disable()
                // 定制我们自己的 session 策略
                .sessionManagement()
                // 调整为让 Spring Security 不创建和使用 session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 配置被拦截时的处理

        http
                .exceptionHandling()
                // 添加 token 无效或者没有携带 token 时的处理
                .authenticationEntryPoint(this.unauthorizedHandler)
                //添加无权限时的处理
                .accessDeniedHandler(this.accessDeniedHandler);

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
