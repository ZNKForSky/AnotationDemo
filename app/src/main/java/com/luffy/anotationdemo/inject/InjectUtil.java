package com.luffy.anotationdemo.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/22 15:01<p>
 * 描述：
 */
public class InjectUtil {
    public static void bindView(Activity activity) {
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
}
