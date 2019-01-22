package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

import org.springframework.util.StringUtils;

/**
 * @Description 年龄校验过滤器
 * @Author ZWen
 * @Date 2019/1/19 6:47 PM
 * @Version 1.0
 **/
public class NameCheckFilter implements CustomFilter {

    /**
     * 过滤器
     *
     * @param req
     * @param resp
     * @param chain
     */
    @Override
    public void doFilter(CustomReq req, CustomResp resp, CustomFilterChain chain) {
        System.out.println("name(姓名) filter begin ...");
        if (!check(req, resp)) {
            return;
        }
        chain.doFilter(req, resp);
        System.out.println("name(姓名) filter end ...");
    }

    private boolean check(CustomReq req, CustomResp resp) {
        resp.setName(req.getName());
        if (StringUtils.isEmpty(req.getName()) || req.getName().contains("屌丝")) {
            resp.setCode(1002);
            return false;
        } else {
            resp.setCode(0);
            return true;
        }
    }
}