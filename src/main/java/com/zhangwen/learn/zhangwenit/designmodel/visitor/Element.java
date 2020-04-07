package com.zhangwen.learn.zhangwenit.designmodel.visitor;

/**
 * @Description 元素（元素数量一般固定）
 * @Author ZWen
 * @Date 2020/4/7 6:36 PM
 * @Version 1.0
 **/
public interface Element {

    /**
     * 以访问者为参数
     *
     * @param visitor
     */
    void accept(Visitor visitor);
}
