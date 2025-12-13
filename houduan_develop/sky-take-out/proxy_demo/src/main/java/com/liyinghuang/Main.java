
package com.liyinghuang;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BigStar bigStar = new BigStar();
        Task task = ProxyUtil.CreateProxy(bigStar);
        task.Sing("普通朋友");
    }
}