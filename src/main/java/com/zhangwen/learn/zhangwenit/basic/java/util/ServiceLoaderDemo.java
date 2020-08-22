package com.zhangwen.learn.zhangwenit.basic.java.util;

import com.zhangwen.learn.zhangwenit.basic.java.dto.Animal;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Description
 * @Author ZWen
 * @Date 2020/8/22 7:06 PM
 * @Version 1.0
 **/
public class ServiceLoaderDemo {

    public static void main(String[] args) {
        final ServiceLoader<Animal> animals = ServiceLoader.load(Animal.class);

        final Iterator<Animal> animalIterator = animals.iterator();

        while (animalIterator.hasNext()) {
            final Animal animal = animalIterator.next();
            animal.printName();
        }
    }

}