package com.luffy.anotationdemo.intdef;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/20 17:03<p>
 * 描述：源码级别注解的应用场景：限制入参，例子是IntDef注解
 */
public class Test {
    private static WeekDay mCurrentDay;
    @WeekDayAnnotation
    private static int mCurrentIntDay;

    private static final int SUNDAY = 0;
    private static final int MONDAY = 1;
    enum WeekDay{
        SUNDAY,MONDAY
    }

    //使用IntDef定义传入的参数
    @IntDef(value ={SUNDAY, MONDAY})
    @Target({ElementType.FIELD,ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface WeekDayAnnotation{}

    private static void setCurrentDay(WeekDay weekDay){
        mCurrentDay = weekDay;
    }

    private static void setCurrentDay(@WeekDayAnnotation int weekDay){
        mCurrentIntDay = weekDay;
    }

    private static void test(){
        //使用枚举固然可以解决限制方法入参的问题，但是枚举太费内存，枚举的本质是对象，
        //一个对象占用内存包含对象头的12个字节，成员占的内存和8字节对齐。
        setCurrentDay(WeekDay.SUNDAY);
        //使用IntDef
        setCurrentDay(SUNDAY);
//        setCurrentDay(1);
    }
}
