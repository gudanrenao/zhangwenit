package com.zhangwen.learn.zhangwenit.api.system.repository;

import com.zhangwen.learn.zhangwenit.api.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author by zhangwen
 */
public interface ManageUserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
