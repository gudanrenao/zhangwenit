package com.zhangwen.learn.zhangwenit.transaction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class TestTransactionServiceTest {

    @Autowired
    private TestTransactionService testTransactionService;

    @Test
    public void requiredAndInnerRequired() {
        testTransactionService.requiredAndInnerRequired();
    }

    @Test
    public void requiredAndInnerRequiredNew() {
        testTransactionService.requiredAndInnerRequiredNew();
    }


    @Test
    public void nonTransactionAndInnerRequired() {
        testTransactionService.nonTransactionAndInnerRequired();
    }
}