package com.bawei.dianshangjinweek03.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        getSupportActionBar().hide();
        initData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        initDestroy();
        unbinder.unbind();
    }
    //方法封装
    protected abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initDestroy();
}
