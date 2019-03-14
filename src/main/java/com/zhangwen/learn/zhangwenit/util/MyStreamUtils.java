package com.zhangwen.learn.zhangwenit.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 输入流工具类
 * @Author ZWen
 * @Date 2019/3/11 3:17 PM
 * @Version 1.0
 **/
public class MyStreamUtils {


    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            byte[] copyToByteArray = StreamUtils.copyToByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}