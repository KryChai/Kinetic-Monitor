package com.example.myapplication;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import com.example.myapplication.bean.UserInfo;
import com.example.myapplication.db.UserInfoHelper;
public class mbsd_activity extends AppCompatActivity {
    private Button save_mbsd;
    private LinearLayout back_mbsd;
    private NumberPicker mbsd_fwc, mbsd_ywqz, mbsd_dq;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbsd);
        save_mbsd=findViewById(R.id.save_mbsd);
        back_mbsd=findViewById(R.id.mbsd_back);
        mbsd_fwc = findViewById(R.id.npPushups);
        mbsd_ywqz = findViewById(R.id.npSitups);
        mbsd_dq = findViewById(R.id.npSquats);
        mbsd_fwc.setMinValue(0);
        mbsd_fwc.setMaxValue(100);
        mbsd_ywqz.setMinValue(0);
        mbsd_ywqz.setMaxValue(100);
        mbsd_dq.setMinValue(0);
        mbsd_dq.setMaxValue(100);
        back_mbsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save_mbsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num_fwc=mbsd_fwc.getValue();
                int num_ywqz=mbsd_ywqz.getValue();
                int num_dq=mbsd_dq.getValue();
                UserInfoHelper userInfoHelper=new UserInfoHelper(mbsd_activity.this);
                UserInfo userInfo=new UserInfo(1,"浅梦朋","user123","123456","17731098019",175,65,60,21,"男",num_fwc,num_dq,num_ywqz);
                System.out.println(userInfo.toString());
                String str=userInfoHelper.updateUserInfo(userInfo);
            }
        });
    }
}