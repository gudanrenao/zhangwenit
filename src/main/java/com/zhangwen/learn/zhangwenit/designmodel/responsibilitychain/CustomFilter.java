package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

/**
 * @Description 过滤器（责任链模式）
 * @Author ZWen
 * @Date 2019/1/19 6:48 PM
 * @Version 1.0
 **/
public interface CustomFilter {

    /**
     * 过滤器
     *
     * @param req
     * @param resp
     * @param chain
     */
    void doFilter(CustomReq req, CustomResp resp, CustomFilterChain chain);
}
