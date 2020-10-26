package com.luffy.check;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/26 17:00<p>
 * 描述：检查代码异常的工具类
 */
public class CheckUtil {
    public static void checkException(Object object) throws IOException {
        //1.获取类的字节码文件对象
        Class<?> cls = object.getClass();
        //2.通过字节码文件对象获取类的所有方法
        Method[] declaredMethods = cls.getDeclaredMethods();

        //3.定义记录异常出现次数的变量
        int exceptionCount = 0;
        //4.使用流输出异常报告到“bug.txt”中
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));
        //5.遍历类中的所有方法，检查哪些方法被 Check注解标注
        for (Method declaredMethod : declaredMethods) {
            //6.如果被 Check标注，则执行方法
            if (declaredMethod.isAnnotationPresent(Check.class)) {
                try {
                    declaredMethod.invoke(object);

                } catch (Exception e) {
                    //7.捕捉异常，记录异常次数
                    exceptionCount++;
                    bw.write(declaredMethod.getName() + " 方法出异常了");
                    bw.newLine();
                    bw.write("异常的名称:" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常的原因:" + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("--------------------------");
                    bw.newLine();
                    e.printStackTrace();
                }
            }
        }
        bw.write(object.getClass().getSimpleName() + "存在" + exceptionCount + "个异常");
        bw.newLine();
        //8.刷新并关闭流
        bw.flush();
        bw.close();

    }
}

