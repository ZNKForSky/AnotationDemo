package com.luffy.reflect;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luffy.reflect.Type.TypeRefrence;

import java.lang.reflect.Type;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/26 14:34<p>
 * 描述：Json反序列化问题
 */
public class Deserialize {
    static class Response<T> {
        T data;
        int code;
        String message;

        public Response(T data, int code, String message) {
            this.data = data;
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "data=" + data +
                    ", code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    static class Data {
        String name;
        int age;
        String sex;

        public Data(String name, int age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Response<Data> dataResponse = new Response<>(new Data("luffy", 18, "male"), 200, "查询成功");
        Gson gson = new Gson();
        String result = gson.toJson(dataResponse);
        System.out.println("result ======= " + result);

        //反序列化
//        Response<Data> response = gson.fromJson(result, Response.class);
//        System.out.println("Data ======= " + response.data);
//        System.out.println("返回的泛型真实类型 ======= " + response.data.getClass());

        //解决上面反序列化出现的问题
//        Type type = new TypeToken<Response<Data>>() {
//        }.getType();
//        Response<Data> response = gson.fromJson(result, type);
//        System.out.println("Data ======= " + response.data);
//        System.out.println("返回的泛型真实类型 ======= " + response.data.getClass());

        //自己写一个 TypeRefrence 解决反序列化问题
        /**
         *  有花括号： 代表是匿名内部类，创建一个匿名内部类的实例对象
         *  没花括号：创建实例对象
         */
        Type type = new TypeRefrence<Response<Data>>() {
        }.getType();
        Response<Data> response = gson.fromJson(result, type);
        System.out.println("Data ======= " + response.data);
        System.out.println("返回的泛型真实类型 ======= " + response.data.getClass());
    }
}
