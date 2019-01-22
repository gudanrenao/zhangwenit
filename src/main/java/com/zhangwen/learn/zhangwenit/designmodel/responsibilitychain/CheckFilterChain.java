package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

/**
 * @Description 责任链-路径
 * @Author ZWen
 * @Date 2019/1/19 6:49 PM
 * @Version 1.0
 **/
public class CheckFilterChain implements CustomFilterChain {

    private CustomFilter[] filters;
    private int position = 0;

    public CheckFilterChain(CustomFilter[] filters) {
        this.filters = filters;
    }

    @Override
    public void doFilter(CustomReq req, CustomResp resp) {
        if (position == filters.length) {
            System.out.println("执行 servlet ...");
        } else {
            filters[position++].doFilter(req, resp, this);
        }
    }
}