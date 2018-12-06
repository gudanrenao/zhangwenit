package com.zhangwen.learn.zhangwenit.api.system.service;

import com.zhangwen.learn.zhangwenit.api.system.entity.User;
import com.zhangwen.learn.zhangwenit.api.system.repository.ManageUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangwen at 2018-08-15 22:55
 **/
@Service
public class ManageUserService {

    private final ManageUserRepository manageUserRepository;

    public ManageUserService(ManageUserRepository manageUserRepository) {
        this.manageUserRepository = manageUserRepository;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void save(User user) {
        manageUserRepository.save(user);
    }

    public User findByName(String name) {
        return manageUserRepository.findByName(name);
    }
}
