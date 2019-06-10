package com.zhangwen.learn.zhangwenit.basic.polymorphic.sub_return_type;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/5/31 2:34 PM
 * @Version 1.0
 **/
public class Sub extends Super {

    @Override
    SubReturn demo() {
        return new SubReturn();
    }

}