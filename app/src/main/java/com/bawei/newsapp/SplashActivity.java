package com.bawei.newsapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import global.APPConstants;
import utils.SpUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        Boolean ifFirstopen = SpUtils.getBoolean(this, APPConstants.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!ifFirstopen){
            Intent intent = new Intent(this,WelecomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);

        //执行完该页面则跳转到主界面，通过线程实现延迟2秒进入
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();



            }
        },2000);


    }
}
