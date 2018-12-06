package com.zhangwen.learn.zhangwenit.security.dto;

import com.zhangwen.learn.zhangwenit.security.entity.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 登录信息
 * @Author ZWen
 * @Date 2018/12/5 3:07 PM
 * @Version 1.0
 **/
public class AuthUserInfo implements UserDetails {

    private Integer id;

    private String name;

    private String password;

    /**
     * 账户状态，1=可用，0=禁用
     */
    private int status;

    private List<AuthRoleRedisInfo> roleList;

    public AuthUserInfo() {
    }

    public AuthUserInfo(AuthUser authUser, List<AuthRoleRedisInfo> roleList) {
        this.id = authUser.getId();
        this.name = authUser.getName();
        this.password = authUser.getPassword();
        this.status = authUser.getStatus();
        this.roleList = roleList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList.stream().map(e -> new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AuthRoleRedisInfo> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<AuthRoleRedisInfo> roleList) {
        this.roleList = roleList;
    }
}