package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class jksj_activity extends AppCompatActivity {
    private WebView webview_weekly;
    private WebView webview2;
    private LinearLayout back_jksj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jksj);
        init();
        SleepThread();


    }
    private void init() {
        webview2=findViewById(R.id.webviewdaliy);
        webview_weekly=findViewById(R.id.webview_weekly);
        back_jksj=findViewById(R.id.jksj_back);

        webview2.getSettings().setJavaScriptEnabled(true);
        webview2.getSettings().setAllowFileAccess(true);
        webview2.loadUrl("file:///android_asset/dailyAnalysis.html");//调整路径
        webview_weekly.getSettings().setJavaScriptEnabled(true);
        webview_weekly.getSettings().setAllowFileAccess(true);
        webview_weekly.loadUrl("file:///android_asset/weekAnalysis.html");//调整路径

        back_jksj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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