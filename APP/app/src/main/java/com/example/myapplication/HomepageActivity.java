package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.bean.UserInfo;
import com.example.myapplication.db.UserInfoHelper;
import java.util.List;
public class HomepageActivity extends AppCompatActivity  {
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private EditText height;
    private EditText weight;
    private EditText targetWeight;
    private EditText age;
    private Button update;
    private String heightStr;
    private String weightStr;
    private String targetWeightStr;
    private String ageStr;
    private RelativeLayout back_wdzy;
    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_homepage);
        update = findViewById(R.id.homepage_btn_update);
        back_wdzy=findViewById(R.id.wdzy_back);
        height=findViewById(R.id.homepage_et_height);
        weight=findViewById(R.id.homepage_et_weight);
        targetWeight=findViewById(R.id.homepage_et_target_weight);
        age=findViewById(R.id.homepage_et_age);
        maleRadio=findViewById(R.id.homepage_rd_male);
        femaleRadio=findViewById(R.id.homepage_rd_female);
        UserInfoHelper userInfoHelper=new UserInfoHelper(HomepageActivity.this);
        List<UserInfo>list=userInfoHelper.selectAllUserInfo();
        UserInfo userInfo=list.get(0);
        height.setText(String.valueOf(userInfo.getHeight()));
        weight.setText(String.valueOf(userInfo.getWeight()));
        targetWeight.setText(String.valueOf(userInfo.getTargetWeight()));
        age.setText(String.valueOf(userInfo.getAge()));
        if(userInfo.getGender().equals("男")){
            maleRadio.setChecked(true);
            femaleRadio.setChecked(false);
        }
        else{
            maleRadio.setChecked(false);
            femaleRadio.setChecked(true);
        }
        back_wdzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightStr= String.valueOf(height.getText());
                weightStr= String.valueOf(weight.getText());
                targetWeightStr= String.valueOf(targetWeight.getText());
                ageStr= String.valueOf(age.getText());
                String gender="";
                if(maleRadio.isChecked()){
                    gender="男";}
                else{
                    gender="女";
                }
                UserInfoHelper userInfoHelper=new UserInfoHelper(HomepageActivity.this);
                UserInfo userInfo=new UserInfo(1,"浅梦朋","user123","123456","17731098019",Integer.parseInt(heightStr),Integer.parseInt(weightStr),Integer.parseInt(targetWeightStr),Integer.parseInt(ageStr),gender,10,10,10);
                System.out.println(userInfo.toString());
                String str=userInfoHelper.insertUserInfo(userInfo);
                Toast.makeText(HomepageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
