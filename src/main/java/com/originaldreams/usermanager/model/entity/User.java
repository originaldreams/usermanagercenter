package com.originaldreams.usermanager.model.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String authorities;
    private Date lastPasswordResetDate;
    private List<String> roles;

    public User() {
    }

    public User(Long id, String username, String password, String email, String authorities, Date lastPasswordResetDate, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.roles = roles;
    }

    public static User newInstance(Long id, String userName, String password, String authorities, String email, Date lastPasswordResetDate, List<String> roles) {
        User user = new User();
        user.id = id;
        user.username = userName;
        user.password = password;
        user.authorities = authorities;
        user.email = email;
        user.lastPasswordResetDate = lastPasswordResetDate;
        user.roles = roles;
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", authorities='" + authorities + '\'' +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                ", roles=" + roles +
                '}';
    }
}
