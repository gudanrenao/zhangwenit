package com.zhangwen.learn.zhangwenit.asynchronous.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 通用数据
 * @Author ZWen
 * @Date 2018/12/6 4:57 PM
 * @Version 1.0
 **/
public interface CommonConstant {

    /**
     * value 状态 null=无 -1=进行中 -2=进行失败，大于-1表示上次同步成功数量
     */
    ConcurrentHashMap<Integer, Integer> EXEC_STATUS_MAP = new ConcurrentHashMap<>(4);
}
