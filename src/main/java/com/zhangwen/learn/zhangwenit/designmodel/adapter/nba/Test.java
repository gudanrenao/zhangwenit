package com.zhangwen.learn.zhangwenit.designmodel.adapter.nba;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/10/4 12:07 PM
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        Player guard = new Guard("保罗");
        //保罗是外国人可以听懂教练的安排
        guard.attack();
        guard.defense();
        Player forward = new Forward("詹姆斯");
        //詹姆斯也是外国人可以听懂教练的安排
        forward.attack();
        forward.defense();

        Player center = new Center("韩德君");
        //韩德君是中国人不会英语，听不懂教练的安排
//        center.attack();
//        center.defense();

        //所以需要一个翻译者，这个翻译者能听懂教练的安排，听懂后翻译给韩德君听，韩德君就知道该怎么做了
        Player translator = new Translator("韩德君");
        translator.attack();
        translator.defense();
    }
}