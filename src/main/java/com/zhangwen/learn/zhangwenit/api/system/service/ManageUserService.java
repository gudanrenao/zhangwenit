package com.zhangwen.learn.zhangwenit.api.system.service;

import com.zhangwen.learn.zhangwenit.api.system.entity.ManageUser;
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
    public void save(ManageUser user) {
        manageUserRepository.save(user);
    }

    public ManageUser findByName(String name) {
        return manageUserRepository.findByName(name);
    }
}
