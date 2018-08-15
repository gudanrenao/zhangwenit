package com.zhangwen.learn.zhangwenit.api.merchant.repository;

import com.zhangwen.learn.zhangwenit.api.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author by zhangwen
 */
public interface MerchantRepository extends JpaRepository<Merchant, Long>,JpaSpecificationExecutor<Merchant> {

}
