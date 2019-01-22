package com.zhangwen.learn.zhangwenit.transaction.service;

import com.zhangwen.learn.zhangwenit.transaction.entity.TestTransaction;
import com.zhangwen.learn.zhangwenit.transaction.repository.TestTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 测试事务
 * @Author ZWen
 * @Date 2019/1/11 2:14 PM
 * @Version 1.0
 **/
@SuppressWarnings("all")
@Service
public class TestTransactionService {

    private final TestTransactionRepository testTransactionRepository;
    private final InnerTransactionService innerTransactionService;

    public TestTransactionService(TestTransactionRepository testTransactionRepository, InnerTransactionService innerTransactionService) {
        this.testTransactionRepository = testTransactionRepository;
        this.innerTransactionService = innerTransactionService;
    }

    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public void requiredAndInnerRequired() {
        test2();
        TestTransaction test1 = testTransactionRepository.findById(1L).orElse(new TestTransaction());
        test1.setName("required");
        testTransactionRepository.save(test1);
//        innerTransactionService.innerRequired();
//        throw new RuntimeException("手动抛出异常");
    }

    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public void test2() {
        TestTransaction test1 = testTransactionRepository.findById(2L).orElse(new TestTransaction());
        test1.setName("required222222");
        testTransactionRepository.save(test1);
//        throw new RuntimeException("手动抛出异常");
    }

    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRES_NEW)
    public void requiredAndInnerRequiredNew() {
        TestTransaction test1 = testTransactionRepository.findById(1L).orElse(new TestTransaction());
        test1.setName("required");
        testTransactionRepository.save(test1);
        try {
            innerTransactionService.innerRequiredNew();
        }catch (Exception e){

        }
//        innerTransactionService.innerRequiredNew();
//        throw new RuntimeException("手动抛出异常");
    }

    public void nonTransactionAndInnerRequired() {
        TestTransaction test1 = testTransactionRepository.findById(1L).orElse(new TestTransaction());
        test1.setName("nonTransaction");
        testTransactionRepository.save(test1);
        innerTransactionService.innerRequired();
        throw new RuntimeException("手动抛出异常");
    }
}