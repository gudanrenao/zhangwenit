package com.zhangwen.learn.zhangwenit.transaction.service;

import com.zhangwen.learn.zhangwenit.transaction.entity.TestTransaction;
import com.zhangwen.learn.zhangwenit.transaction.repository.TestTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 被调用
 * @Author ZWen
 * @Date 2019/1/11 2:17 PM
 * @Version 1.0
 **/
@SuppressWarnings("all")
@Service
public class InnerTransactionService {

    private final TestTransactionRepository testTransactionRepository;

    public InnerTransactionService(TestTransactionRepository testTransactionRepository) {
        this.testTransactionRepository = testTransactionRepository;
    }

    /**
     * 默认 如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务
     */
    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)
    public void innerRequired() {
        TestTransaction test2 = testTransactionRepository.findById(2L).orElse(new TestTransaction());
        test2.setName("innerRequired");
        testTransactionRepository.save(test2);
        throw new RuntimeException("手动抛出异常 inner");
    }

    /**
     * 重新创建一个新的事务，如果当前存在事务，暂停当前的事务
     */
    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRES_NEW)
    public void innerRequiredNew() {
            TestTransaction test = testTransactionRepository.findById(2L).orElse(new TestTransaction());
            test.setName("innerRequiredNew");
            testTransactionRepository.save(test);
            throw new RuntimeException("手动抛出异常 inner");
    }
}