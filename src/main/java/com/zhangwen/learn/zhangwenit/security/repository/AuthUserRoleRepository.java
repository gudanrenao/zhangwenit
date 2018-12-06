package com.zhangwen.learn.zhangwenit.security.repository;

import com.zhangwen.learn.zhangwenit.security.entity.AuthUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author ZWen
 * @Date 2018/12/5 3:29 PM
 * @Version 1.0
 **/
public interface AuthUserRoleRepository extends JpaRepository<AuthUserRole, Integer> {

    /**
     * 获取账号对应的角色列表
     *
     * @param userId
     * @return
     */
    List<AuthUserRole> findAllByUserId(Integer userId);
}
