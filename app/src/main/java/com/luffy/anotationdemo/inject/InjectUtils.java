package com.luffy.anotationdemo.inject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/22 15:01<p>
 * 描述：注入工具类
 */
public class InjectUtils {
    private static final String TAG = "InjectUtils";

    /**
     * 绑定Activity，完成findViewById的操作
     *
     * @param activity 需要绑定的Activity实例对象
     */
    public static void bindView(@NonNull Activity activity) {
        if (activity == null) {
            return;
        }
        //1.获取Activity的class对象
        Class<? extends Activity> cls = activity.getClass();
        //2.获取所有属性（不包含父类）
        Field[] fields = cls.getDeclaredFields();
        //3.遍历属性
        for (Field field : fields) {
            //4.如果属性被InjectView注解标注，则进行findViewById操作
            if (field.isAnnotationPresent(InjectView.class)) {
                //5.如果是私有属性，需要设置属性可用
                field.setAccessible(true);
                //6.获取属性上面的注解对象
                InjectView injectView = field.getAnnotation(InjectView.class);
                //7.获取资源id
                int id = injectView.value();
                //8.findViewById
                View view = activity.findViewById(id);
                try {
                    //9.为属性赋值
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 绑定Activity，完成获取其他Activity传递过来的数据的操作
     *
     * @param activity 需要绑定的Activity实例对象
     */
    public static void bindAutoWired(@NonNull Activity activity) {
        if (activity == null) {
            return;
        }
        //1.获取Activity的class对象
        Class<? extends Activity> cls = activity.getClass();
        //2.获取该Activity关联的Intent对象,进而获取Bundle对象
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        //3.获取所有该类属性
        Field[] declaredFields = cls.getDeclaredFields();
        //4.遍历所有属性
        for (Field declaredField : declaredFields) {
            //5.如果属性被@AutoWired标注，则获取注解的值，注解值如果为"",则取属性的值作为key
            if (declaredField.isAnnotationPresent(AutoWired.class)) {
                AutoWired autoWired = declaredField.getAnnotation(AutoWired.class);
                //6.获取key
                String value = autoWired.value();
                String key = TextUtils.isEmpty(value) ? declaredField.getName() : value;
                if (extras.containsKey(key)) {
                    Object obj = extras.get(key);

                    Class<?> type = declaredField.getType();
                    Class<?> componentType = declaredField.getType().getComponentType();
                    //当前属性是数组并且是 Parcelable或其子类数组
                    if (declaredField.getType().isArray() &&
                            Parcelable.class.isAssignableFrom(componentType)) {
                        //创建对应类型的数组并由objs拷贝
                        Parcelable[] parcelableArray = extras.getParcelableArray(key);
                        Log.i(TAG, "bindAutoWired: obj === " + obj);
                        Log.i(TAG, "bindAutoWired: Arrays.toString((Object[]) obj)) === " + Arrays.toString((Object[]) obj));
//                        Object[] objs = (Object[]) obj;
//                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) declaredField.getType());
//                        obj = objects;
                        obj = parcelableArray;
                    }
                    declaredField.setAccessible(true);
                    try {
                        //8.给属性赋值
                        declaredField.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                //7.取出传过来的值
//                String stringExtra = intent.getStringExtra(key);
//                System.out.println("stringExtra ====== " + stringExtra);
//                declaredField.setAccessible(true);

            }
        }
    }
}
