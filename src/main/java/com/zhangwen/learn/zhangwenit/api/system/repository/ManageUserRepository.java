package com.zhangwen.learn.zhangwenit.api.system.repository;

import com.zhangwen.learn.zhangwenit.api.system.entity.ManageUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author by zhangwen
 */
public interface ManageUserRepository extends JpaRepository<ManageUser, Long> {

    ManageUser findByName(String name);
}
