package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

/**
 * @Description 责任链-路径
 * @Author ZWen
 * @Date 2019/1/19 6:49 PM
 * @Version 1.0
 **/
public interface CustomFilterChain {

    /**
     * 链式执行
     * @param req
     * @param resp
     */
    void doFilter(CustomReq req, CustomResp resp);
}