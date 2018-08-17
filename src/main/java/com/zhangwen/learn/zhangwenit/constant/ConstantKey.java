package com.zhangwen.learn.zhangwenit.constant;

/**
 * @author zhangwen at 2018-08-16 0:19
 **/
public class ConstantKey {

    /**
     * 签名key
     */
    public static final String SIGNING_KEY = "spring-security-@Jwt!&Secret^#";
    /**
     * token前缀
     */
    public static final String SIGNING_PREFIX = "Bearer ";
    /**
     * token有效期
     */
    public static final long SIGNING_EFFECTIVE_TIME = 30  * 1000;


}
