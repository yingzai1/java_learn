package com.liyinghuang;

public class BigStar implements Task {
    public String name;
    public Integer age;
    public void Sing(String songName){
        System.out.println("该明星开始唱"+songName+"了~");
    }
    public void Dance(){
        System.out.println("该明星开始跳舞了");
    }
    public BigStar() {
    }

    public BigStar(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
        return "BigStar{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}