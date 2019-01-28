package com.zhangwen.learn.zhangwenit.synchronous.service;

import com.zhangwen.learn.zhangwenit.exception.BaseException;
import com.zhangwen.learn.zhangwenit.transaction.entity.TestTransaction;
import com.zhangwen.learn.zhangwenit.transaction.repository.TestTransactionRepository;
import org.springframework.stereotype.Service;

/**
 * @Description 重复提交问题
 * @Author ZWen
 * @Date 2019/1/28 1:37 PM
 * @Version 1.0
 **/
@Service
public class SynchronousTestService {

    private final TestTransactionRepository testTransactionRepository;

    private final Object insertLock = new Object();

    public SynchronousTestService(TestTransactionRepository testTransactionRepository) {
        this.testTransactionRepository = testTransactionRepository;
    }

    /**
     * 重复提交 解决方案
     *
     * @param name
     * @return
     */
    public TestTransaction insertTest(String name) {
        TestTransaction testTransaction = new TestTransaction();
        testTransaction.setName(name);
        synchronized (this.insertLock) {
            long count = testTransactionRepository.countByName(name);
            if (count > 0) {
                throw new BaseException("已存在，不可插入");
            }
            TestTransaction save = testTransactionRepository.save(testTransaction);
            System.out.println(save);
            return save;
        }
//        try {
//            Thread.sleep(new Random().nextInt(1000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        long count = testTransactionRepository.countByName(name);
//        if (count > 0) {
//            throw new BaseException("已存在，不可插入");
//        }
//        TestTransaction save = testTransactionRepository.saveAndFlush(testTransaction);
//
//        System.out.println(save);
//        return save;
    }
}