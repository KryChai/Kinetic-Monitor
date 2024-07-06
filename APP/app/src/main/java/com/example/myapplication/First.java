package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        WebView webView0=findViewById(R.id.webView00);

        webView0.getSettings().setJavaScriptEnabled(true);
        webView0.getSettings().setAllowFileAccess(true);
        webView0.getSettings().setUseWideViewPort(true);
        webView0.getSettings().setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        webView0.loadUrl("file:///android_asset/earth2/index.html");//调整路径

        // 使用Handler来延迟跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 创建一个Intent来启动LoginActivity
                Intent intent = new Intent(First.this, MainActivity.class);
                startActivity(intent);
                // 关闭当前Activity
                finish();
            }
        }, 3000); // 2000毫秒后执行上述代码


    }
}