package com.luffy.anotationdemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/29 17:22<p>
 * 描述：
 */
public class Person implements Parcelable {
    private String nama;
    private int age;
    private String sex;

    public Person(String nama, int age, String sex) {
        this.nama = nama;
        this.age = age;
        this.sex = sex;
    }

    protected Person(Parcel in) {
        nama = in.readString();
        age = in.readInt();
        sex = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nama='" + nama + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeInt(age);
        dest.writeString(sex);
    }
}
