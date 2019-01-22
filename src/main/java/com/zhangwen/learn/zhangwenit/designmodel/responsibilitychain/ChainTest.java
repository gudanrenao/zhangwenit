package com.zhangwen.learn.zhangwenit.designmodel.responsibilitychain;

/**
 * @Description 责任链测试
 * @Author ZWen
 * @Date 2019/1/19 8:53 PM
 * @Version 1.0
 **/
public class ChainTest {

    /**
     * 单例责任链Filters
     */
    private static final CustomFilter ageCheckFilter = new AgeCheckFilter();
    private static final CustomFilter nameCheckFilter = new NameCheckFilter();

    public static void main(String[] args) {

        CustomFilterChain filterChain1 = new CheckFilterChain(new CustomFilter[]{ageCheckFilter, nameCheckFilter});
        CustomFilterChain filterChain2 = new CheckFilterChain(new CustomFilter[]{nameCheckFilter, ageCheckFilter});

        //请求 and 响应
        CustomReq req = new CustomReq("zw1", 180);
        CustomResp resp1 = new CustomResp();
        CustomResp resp2 = new CustomResp();

        //执行
        filterChain1.doFilter(req, resp1);
        //输出结果
        System.out.println("response1: " + resp1.toString());

        System.out.println("------------------------------------------------------");

        filterChain2.doFilter(req, resp2);
        System.out.println("response2: " + resp2.toString());
    }
}