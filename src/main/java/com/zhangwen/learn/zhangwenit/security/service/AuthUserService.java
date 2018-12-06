package com.zhangwen.learn.zhangwenit.security.service;

import com.zhangwen.learn.zhangwenit.security.entity.AuthUser;
import com.zhangwen.learn.zhangwenit.security.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

/**
 * @Description 账户
 * @Author ZWen
 * @Date 2018/12/5 3:33 PM
 * @Version 1.0
 **/
@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    public AuthUserService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public AuthUser findByName(String name) {
        return authUserRepository.findByName(name);
    }
}