package com.zhangwen.learn.zhangwenit.algorithm.sorting_basic.selection_sort_using_comparable;

/**
 * @author zhangwen at 2018-06-16 23:57
 **/
public class Student implements Comparable<Student> {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        if (this.score < o.score) {
            return -1;
        } else if (this.score > o.score) {
            return 1;
        }
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
