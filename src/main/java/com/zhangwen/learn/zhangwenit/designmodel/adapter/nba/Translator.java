package com.zhangwen.learn.zhangwenit.designmodel.adapter.nba;

/**
 * @Description 翻译
 * @Author ZWen
 * @Date 2019/10/4 12:11 PM
 * @Version 1.0
 **/
public class Translator extends Player {

    //持有一个中国球员，翻译给他听
    private final ChinaCenter chinaCenter;

    public Translator(String name) {
        super(name);
        this.chinaCenter = new ChinaCenter(name);
    }

    @Override
    public void attack() {
        chinaCenter.进攻();
    }

    @Override
    public void defense() {
        chinaCenter.防守();
    }


}