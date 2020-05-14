package com.xframework.model;

import java.io.Serializable;

public class User implements Serializable {
    private String age;

    private String name;

    public String getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User [name=");
        stringBuilder.append(this.name);
        stringBuilder.append(", age=");
        stringBuilder.append(this.age);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
