package com.zhangwen.learn.zhangwenit.designmodel.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 枚举元素,提供一个高层接口，允许访问者访问元素
 * @Author ZWen
 * @Date 2020/4/7 6:52 PM
 * @Version 1.0
 **/
public class ObjectStructure {

    private List<Element> elements = new ArrayList<>();

    public void attach(Element element) {
        elements.add(element);
    }

    public void detach(Element element) {
        elements.remove(element);
    }

    public void accept(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}