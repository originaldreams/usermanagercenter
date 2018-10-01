package com.originaldreams.usermanager.model.entity;


import java.util.Date;

public class User {

    private static final Integer ENABLE = 0;
    private static final Integer DISABLE = 1;

    private Long id;
    private String userName;
    private String password;
    private String authorities;
    private Date passwordGmtModified;
    private Integer enable;

    public User() {
    }

    public User(Long id, String userName, String password, String authorities, Date passwordGmtModified, Integer enable) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.passwordGmtModified = passwordGmtModified;
        this.enable = enable;
    }

    public static User newInstance(Long id, String userName, String password, String authorities, Date passwordGmtModifity, Integer enable) {
        User user = new User();
        user.id = id;
        user.userName = userName;
        user.password = password;
        user.authorities = authorities;
        user.passwordGmtModified = passwordGmtModifity;
        user.enable = enable;
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Date getPasswordGmtModified() {
        return passwordGmtModified;
    }

    public void setPasswordGmtModified(Date passwordGmtModified) {
        this.passwordGmtModified = passwordGmtModified;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return this.getEnable().equals(ENABLE);
    }
}
