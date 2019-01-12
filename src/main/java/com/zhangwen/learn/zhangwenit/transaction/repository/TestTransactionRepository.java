package com.zhangwen.learn.zhangwenit.transaction.repository;

import com.zhangwen.learn.zhangwenit.transaction.entity.TestTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description crud
 * @Author ZWen
 * @Date 2019/1/11 2:08 PM
 * @Version 1.0
 **/
public interface TestTransactionRepository extends JpaRepository<TestTransaction,Long> {
}
