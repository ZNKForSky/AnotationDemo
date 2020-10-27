package com.luffy.check;


import java.io.IOException;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/26 17:07<p>
 * 描述：测试Check框架
 */
public class TestCheck {
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        CheckUtil.checkException(calculator);
    }
}

class Calculator {
    //加法
    @Check
    public void add() {
        String str = null;
        str.toString();
        System.out.println("1 + 0 =" + (1 + 0));
    }

    //减法
    @Check
    public void sub() {
        System.out.println("1 - 0 =" + (1 - 0));
    }

    //乘法
    @Check
    public void mul() {
        System.out.println("1 * 0 =" + (1 * 0));
    }

    //除法
    @Check
    public void div() {
        System.out.println("1 / 0 =" + (1 / 0));
    }


    public void show() {
        System.out.println("我不用加Check注解，因为我的代码永无bug~");
    }
}