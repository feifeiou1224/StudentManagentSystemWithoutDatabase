package com.oyf.bean;

import java.io.Serializable;

/**
 * Create Time: 2019年01月25日 16:13
 * Create Author: 欧阳飞
 **/

public class Student implements Serializable {

    private static int id;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String date;
    private String img = "_null.png" ;

    public Student(){
        id = ++id;
    }


    public Student(String username, String password, String name, String sex) {
        id = ++id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
    }



    public Student(String username, String password, String name, String sex, String img) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", 用户名='" + username + '\'' +
                ", 密码='" + password + '\'' +
                ", 姓名='" + name + '\'' +
                ", 性别='" + sex + '\'' +
                ", 上次登录='" + date + '\'' +
                '}';
    }
}
