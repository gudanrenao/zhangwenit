package com.zhangwen.learn.zhangwenit.security.service;

import com.zhangwen.learn.zhangwenit.security.dto.AuthRoleRedisInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @Description 用户角色关系
 * @Author ZWen
 * @Date 2018/12/5 3:54 PM
 * @Version 1.0
 **/
@Service
public class AuthUserRoleService {

    private final Logger logger = LoggerFactory.getLogger(AuthUserRoleService.class);

    private final EntityManager entityManager;

    public AuthUserRoleService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * 登录从redis中获取用户角色列表
     *
     * @param userId
     * @return
     */
    @Cacheable(key = "'userRole:findAllByUser'.concat(#userId.toString())", value = "userRole")
    public List<AuthRoleRedisInfo> findAllByUser(Integer userId) {
        String sql = "SELECT r.id,r.`name` FROM auth_user_role ur INNER JOIN auth_role r ON ur.role_id = r.id WHERE ur.user_id = " + userId;
        Query query = entityManager.createNativeQuery(sql);
        //设置返回每行数据格式为MAP
        List<AuthRoleRedisInfo> list = query.getResultList();
        logger.info("findAllByUser,id={},roles size is:{}", userId, list.size());
        return list;
    }
}