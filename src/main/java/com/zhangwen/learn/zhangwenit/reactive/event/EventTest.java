package com.zhangwen.learn.zhangwenit.reactive.event;

import java.beans.PropertyChangeSupport;

/**
 * @Description 事件监听模式
 * @Author ZWen
 * @Date 2019/3/1 11:50 AM
 * @Version 1.0
 **/
public class EventTest {

    public static void main(String[] args) {

        Person person = new Person();
        person.setName("zhangwen");
        PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(person);

        propertyChangeSupport.addPropertyChangeListener("nameChange", evt -> {
            System.out.printf("change event Person[%s],propertyName:[%s],old value:[%s],new value:[%s]\n",
                    evt, evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
        });

        person.setName("zhaoxin");

        propertyChangeSupport.fireIndexedPropertyChange("nameChange",0,"zhaoxin","zhangwen");

        System.out.println(person);
    }

    private static class Person {

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}