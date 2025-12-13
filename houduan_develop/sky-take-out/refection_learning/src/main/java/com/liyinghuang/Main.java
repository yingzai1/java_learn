/*
* 反射的核心是：在运行时动态地获取类的信息并操作类的成员，而无需在编译时知道具体的类结构。*/

package com.liyinghuang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
* 进行反射的步骤
* 1、获取到对象的字节码文件
* 2、通过类的字节码文件访问其中的Constructor(构造器)、Field(成员变量)、Method(方法)
* 3、使用相应的方法对其进行访问和使用*/
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        //获取类的字节编码的三种方式
//        //1.第一种
//        Class clazz1=Class.forName("com.liyinghuang.Student");
//        //2.第二种
//        Class clazz2=Student.class;
//        //3.第三种(通过一个实例对象得到对应类的字节编码)
//        Student clazz3 = new Student();
//        Class clazz33 = clazz3.getClass();
//        System.out.println(clazz33.equals(clazz2));
//        System.out.println(clazz33.equals(clazz1));
        Class clazz=Class.forName("com.liyinghuang.Student");

        //使用反射调用 Student 类的 preference 方法
        Method preference = clazz.getMethod("preference", String.class);
        Student student = new Student();
        //invoke中的参数介绍
        /*
        * 参数一：调用方法的对象
        * 参数二：调用方法传递的参数*/
        preference.invoke(student,"李洪");
    }
}