package com.luffy.reflect;

import com.google.gson.Gson;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/26 14:34<p>
 * 描述：
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
        System.out.println("result ====== "+result);
        System.out.println("result的类型 ====== "+result.getClass());

        //反序列化
        Response<Data> response = gson.fromJson(result, Response.class);
        System.out.println(response.data.getClass());

    }
}
