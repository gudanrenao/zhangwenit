package com.zhangwen.learn.zhangwenit.temp.repository;

import com.zhangwen.learn.zhangwenit.temp.entities.TempOpenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author by zhangwen
 */
public interface TempOpenIdRepository extends JpaRepository<TempOpenId, Long>, JpaSpecificationExecutor<TempOpenId> {

    List<TempOpenId> findAllByOpenId(String openId);
}
