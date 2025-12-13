package com.liyinghuang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* 类的作用：
*   创建一个代理
*
* */
public class ProxyUtil {
    /*
    * 方法的作用：给一个明星对象，创建一个代理
    * 形参：被代理的明星对象
    * 返回值：给明星创建的代理
    * */
    public static Task CreateProxy(BigStar bigstar){
        Task task=(Task)Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),//参数一：用以指明用哪个类加载器，去加载生成的代理类
                new Class[]{Task.class},//参数二：指明接口，这些接口用于指定生成的代理长什么样子，也就是有哪些方法
                //参数三：用来指定生成的代理对象要干什么
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.getName().equals("Sing")){
                            System.out.println("为大明星准备话筒");
                        }
                        else if(method.getName().equals("Dance")){
                            System.out.println("为大明星准备场地");
                        }
                        return method.invoke(bigstar,args);
                    }
                }
        );
        return task;
    }
}
