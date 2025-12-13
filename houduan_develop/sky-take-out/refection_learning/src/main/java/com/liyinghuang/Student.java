package com.liyinghuang;

public class Student {
    public String name;
    public int age;

    public Student() {
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void sing (String songName){
        System.out.println(this.name+"会唱"+songName);
    }
    public void preference(String name){
        System.out.println("该同学喜欢和"+name+"一起玩");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
