package com.luffy.anotationdemo;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luffy.anotationdemo.entity.Cat;
import com.luffy.anotationdemo.entity.Person;
import com.luffy.anotationdemo.inject.InjectUtils;
import com.luffy.anotationdemo.inject.InjectView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.tv_hello)
    private TextView mTv;
    @InjectView(R.id.btn_next)
    private Button mBtnNext;

    private byte b = 6;
    private short s = 8;
    private int i = 66;
    private long l = 666L;
    private float f = 666.0f;
    private double d = 68.00;
    private boolean bool = true;
    private char c = 'a';
    private String info = "做我小弟，以后大哥罩着你！";
    private int[] intArray = {1, 2, 3, 4, 5, 6};
    private Parcelable[] parcelables = {new Person("luffy",18,"male"),new Person("qq",17,"famale")};
    private ArrayList<CharSequence> charSequenceArrayList = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.bindView(this);
        mTv.setText("使用注解和反射完成findViewById,niubility!!!");
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("byte", b);
                intent.putExtra("short", s);
                intent.putExtra("int", i);
                intent.putExtra("long", l);
                intent.putExtra("float", f);
                intent.putExtra("double", d);
                intent.putExtra("boolean", bool);
                intent.putExtra("char", c);
                intent.putExtra("info", info);
                intent.putExtra("intArray", intArray);
                intent.putExtra("bundle",savedInstanceState);
                intent.putExtra("parcelable",new Person("luffy",18,"male"));
                intent.putExtra("parcelableArray",parcelables);
                intent.putExtra("serializable",new Cat("球球"));
                charSequenceArrayList.add("借两盏薄酒");
                charSequenceArrayList.add("便敢论天下");
                intent.putCharSequenceArrayListExtra("charSequenceArrayList",charSequenceArrayList);

                startActivity(intent);
            }
        });
    }
}
