package com.originaldreams.passport.model.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体类，去掉部分不需要的属性
 */
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String email;
//    private String authorities;
    private Date lastPasswordModified;

    /** account_non_expired */
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;



//    private List<String> roles;

    public User() {
    }


    public User(Long id, String username, String password, String email, Date lastPasswordModified, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastPasswordModified = lastPasswordModified;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public static User newInstance(Long id, String userName, String password, String authorities, String email, Date lastPasswordResetDate, List<String> roles) {
        User user = new User();
        user.id = id;
        user.username = userName;
        user.password = password;
//        user.authorities = authorities;
        user.email = email;
        user.lastPasswordModified = lastPasswordResetDate;
//        user.roles = roles;
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

//    public String getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(String authorities) {
//        this.authorities = authorities;
//    }

    public Date getLastPasswordModified() {
        return lastPasswordModified;
    }

    public void setLastPasswordModified(Date lastPasswordModified) {
        this.lastPasswordModified = lastPasswordModified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //    public List<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<String> roles) {
//        this.roles = roles;
//    }
//


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastPasswordModified=" + lastPasswordModified +
                '}';
    }
}
