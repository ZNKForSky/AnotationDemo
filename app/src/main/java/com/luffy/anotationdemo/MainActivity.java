package com.luffy.anotationdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.luffy.anotationdemo.inject.InjectUtil;
import com.luffy.anotationdemo.inject.InjectView;

@ZnkAnnotation(id = 1)
public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.tv_hello)
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtil.bindView(this);
        mTv.setText("使用注解和反射完成findViewById,niubility!!!");
    }
}
