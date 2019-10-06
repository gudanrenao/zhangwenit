package com.zhangwen.learn.zhangwenit.designmodel.strategy.first;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/10/6 4:30 PM
 * @Version 1.0
 **/
public class StrategyContext {

    private Compute compute;

    public StrategyContext(String strategy) {
        switch (strategy) {
            case "SUB":
                compute = new Sub();
                break;
            case "PLUS":
                compute = new Plus();
                break;
            default:
                throw new RuntimeException("不支持当前类型的计算");
        }
    }

    public void selectRule() {
        compute.rule();
    }
}