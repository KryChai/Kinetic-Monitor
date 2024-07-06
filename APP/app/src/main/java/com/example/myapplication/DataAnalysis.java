package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;



public class DataAnalysis extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);
        init();
        SleepThread();
    }


    private void init() {
        webview = findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.loadUrl("file:///android_asset/echart.html");//调整路径
    }

    private void SleepThread(){
        /**
         * 原文作者提到：js方法的调用必须在html页面加载完成之后才能调用。
         *     用webview加载html还是需要耗时间的，必须等待加载完，在执行代用js方法的代码。
         * 我个人还想提醒:检查html和js是否都放在asset目录下
         */
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}