package com.sinspark.site.model;

import com.sinspark.site.utils.ObjectUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/16.
 */
public class User implements Serializable{

    private int id;

    private String name;

    private int age;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
