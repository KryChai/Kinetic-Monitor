package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Outline;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.bean.UserInfo;
import com.example.myapplication.db.UserInfoHelper;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private Button logBtn;
    private EditText editText_username;
    private EditText editText_password;
    public String username_glabel;
    public String password_glabel;

    private MediaRecorder mediaRecorder;

    private boolean isRecording = false;

    // 定义请求码
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    // 创建一个用于存储拍摄照片的文件路径
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaRecorder=new MediaRecorder();
        logBtn=findViewById(R.id.button_log);

        editText_password=findViewById(R.id.log_password);
        editText_username=findViewById(R.id.log_username);


        UserInfoHelper userInfoHelper=new UserInfoHelper(MainActivity.this);

        List<UserInfo>userInfos = userInfoHelper.selectAllUserInfo();

        for (int i = 0; i < userInfos.size(); i++) System.out.println(userInfos.get(i).toString());


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoHelper userInfoHelper=new UserInfoHelper(MainActivity.this);
                username_glabel=editText_username.getText().toString();
                password_glabel=editText_password.getText().toString();
                List<UserInfo>userInfos = userInfoHelper.selectAllUserInfo();

                ImageView imageView = findViewById(R.id.logo_app);
                imageView.setClipToOutline(true);
                imageView.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20); // 这里的 20 是圆角的半径
                    }
                });
                Log.i("TAG",String.valueOf(userInfos.size()));
                for (int i = 0; i < userInfos.size(); i++) {
                    System.out.println(userInfos.get(i).toString());
                }

                int flag=0;
                for (int i = 0; i < userInfos.size(); i++) {
                    if(userInfos.get(i).getUsername().equals(username_glabel))
                    {
                        if(userInfos.get(i).getPassword().equals(password_glabel)) {
                            flag=1;
                            Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, TabStyleActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                }
                if(flag==0)
                {
                    Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}