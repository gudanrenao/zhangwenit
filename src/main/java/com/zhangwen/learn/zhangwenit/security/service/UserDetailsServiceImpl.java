package com.zhangwen.learn.zhangwenit.security.service;

import com.zhangwen.learn.zhangwenit.enums.ExceptionEnum;
import com.zhangwen.learn.zhangwenit.exception.CustomSecurityException;
import com.zhangwen.learn.zhangwenit.security.dto.AuthRoleRedisInfo;
import com.zhangwen.learn.zhangwenit.security.dto.AuthUserInfo;
import com.zhangwen.learn.zhangwenit.security.entity.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 用户名验证
 * @Author ZWen
 * @Date 2018/12/5 3:28 PM
 * @Version 1.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthUserService authUserService;
    private final AuthUserRoleService authUserRoleService;

    public UserDetailsServiceImpl(AuthUserService authUserService, AuthUserRoleService authUserRoleService) {
        this.authUserService = authUserService;
        this.authUserRoleService = authUserRoleService;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser authUser = authUserService.findByName(s);
        if (authUser == null) {
            throw new CustomSecurityException(ExceptionEnum.LOGIN_NAME_ERROR);
        }
        //获取对应的角色列表
        List<AuthRoleRedisInfo> roleList = authUserRoleService.findAllByUser(authUser.getId());
        if (roleList == null || roleList.size() == 0) {
            throw new CustomSecurityException(ExceptionEnum.LOGIN_NO_ROLE_ERROR);
        }
        return new AuthUserInfo(authUser, roleList);
    }
}