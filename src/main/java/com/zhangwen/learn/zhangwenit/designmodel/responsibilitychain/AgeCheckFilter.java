package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

/**
 * @Description 年龄校验过滤器
 * @Author ZWen
 * @Date 2019/1/19 6:47 PM
 * @Version 1.0
 **/
public class AgeCheckFilter implements CustomFilter {

    /**
     * 过滤器
     *
     * @param req
     * @param resp
     * @param chain
     */
    @Override
    public void doFilter(CustomReq req, CustomResp resp, CustomFilterChain chain) {
        System.out.println("age(年龄) filter begin ...");
        if (!check(req, resp)) {
            return;
        }
        chain.doFilter(req, resp);
        System.out.println("age(年龄) filter end ...");
    }

    private boolean check(CustomReq req, CustomResp resp) {
        resp.setAge(req.getAge());
        if (req.getAge() < 0 || req.getAge() > 120) {
            resp.setCode(1001);
            return false;
        } else {
            resp.setCode(0);
            return true;
        }
    }
}