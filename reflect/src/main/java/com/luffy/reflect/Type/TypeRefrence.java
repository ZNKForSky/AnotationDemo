package com.luffy.reflect.Type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/27 15:51<p>
 * 描述：参考TypeToken 解决Json反序列化问题
 */
public class TypeRefrence<T> {
    Type type;
    T t;

    protected TypeRefrence() {
        //1.获得泛型类型
        Type genericSuperclass = getClass().getGenericSuperclass();
        System.out.println("genericSuperclass的类型是： " + genericSuperclass.getClass());
        System.out.println("genericSuperclass ====== " + (genericSuperclass instanceof Class));
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        //2.因为泛型类型可以定义多个： MainActivity<T,E,K...> 所以返回是一个数组
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        type = actualTypeArguments[0];
    }

    public Type getType() {
        return type;
    }
}
