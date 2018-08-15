package com.zhangwen.learn.zhangwenit.api.system.service;

import com.zhangwen.learn.zhangwenit.api.system.entity.ManageUser;
import com.zhangwen.learn.zhangwenit.api.system.repository.ManageUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @author zhangwen at 2018-08-16 1:11
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ManageUserRepository manageUserRepository;

    public UserDetailsServiceImpl(ManageUserRepository manageUserRepository) {
        this.manageUserRepository = manageUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        ManageUser user = manageUserRepository.findByName(name);
        if(user == null){
            throw new UsernameNotFoundException(name);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), emptyList());

    }
}
