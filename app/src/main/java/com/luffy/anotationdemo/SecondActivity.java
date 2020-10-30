package com.luffy.anotationdemo;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.luffy.anotationdemo.inject.AutoWired;
import com.luffy.anotationdemo.inject.InjectUtils;
import com.luffy.anotationdemo.inject.InjectView;

import java.util.Arrays;

/**
 * 作者：<a href="https://blog.csdn.net/qq_35101450">张宁科CSDN主页</a><p>
 * 创建时间：2020/10/28 16:49<p>
 * 描述：
 */
public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    @InjectView(R.id.tv_main_acticity_info)
    TextView mTv;
    @AutoWired("byte")
    private byte b;
    @AutoWired("short")
    private short s;
    @AutoWired("int")
    private int i;
    @AutoWired("long")
    private long l;
    @AutoWired("float")
    private float f;
    @AutoWired("double")
    private double d;
    @AutoWired("boolean")
    private boolean bool;
    @AutoWired("char")
    private char c;
    @AutoWired
    private String info;
    @AutoWired
    private int[] intArray;
    @AutoWired
    private Bundle bundle;
    @AutoWired
    private Parcelable parcelable;
    @AutoWired
    private Parcelable[] parcelableArray;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        InjectUtils.bindView(this);
        InjectUtils.bindAutoWired(this);
        mTv.setText(info);
        Log.i(TAG, "onCreate: b ======= " + b);
        Log.i(TAG, "onCreate: s ======= " + s);
        Log.i(TAG, "onCreate: i ======= " + i);
        Log.i(TAG, "onCreate: l ======= " + l);
        Log.i(TAG, "onCreate: f ======= " + f);
        Log.i(TAG, "onCreate: d ======= " + d);
        Log.i(TAG, "onCreate: bool ======= " + bool);
        Log.i(TAG, "onCreate: char ======= " + c);
        Log.i(TAG, "onCreate: intArray ======= " + Arrays.toString(intArray));
        Log.i(TAG, "onCreate: bundle ======= " + bundle);
        Log.i(TAG, "onCreate: parcelable ======= " +parcelable);
        Log.i(TAG, "onCreate: parcelableArray ======= " + Arrays.toString(parcelableArray));
    }
}
