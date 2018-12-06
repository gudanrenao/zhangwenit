package com.zhangwen.learn.zhangwenit.security.repository;

import com.zhangwen.learn.zhangwenit.security.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author ZWen
 * @Date 2018/12/5 3:29 PM
 * @Version 1.0
 **/
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

    /**
     * 获取账号
     * @param name
     * @return
     */
    AuthUser findByName(String name);
}
