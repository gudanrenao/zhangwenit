package com.zhangwen.learn.zhangwenit.api.system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * 登录用户
 *
 * @author zhangwen at 2018-08-15 22:52
 **/
@Entity
public class User implements Comparable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        System.out.println("a.id:" + this.getId() + "---b.id:" + user.getId());
        if (this.getId() > user.getId()) {
            return 1;
        } else {
            return -1;
        }
    }

    public static boolean partitioningBy(User user) {
        if (user.getId() < 4) {
            return true;
        } else {
            return false;
        }
    }

    public String groupBy() {
        if (this.id < 3) {
            return "0-2";
        } else if (this.id < 7) {
            return "3-6";
        }
        return "大于7";
    }
}
