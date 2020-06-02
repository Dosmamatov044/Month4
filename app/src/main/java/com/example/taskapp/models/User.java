package com.example.taskapp.models;

public class User {


    private  String name;
private String age;
private  String avatar;





    public User() {
    }

    public User(String name, String age,String avatar) {
        this.name = name;
        this.age = age;
        this.avatar=avatar;
    }

    public User(String name) {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
