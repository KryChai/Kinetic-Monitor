package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.bean.ExerciseRecord;
import com.example.myapplication.bean.ExerciseType;
import com.example.myapplication.bean.UserInfo;
import com.example.myapplication.db.ExerciseRecordHelper;
import com.example.myapplication.db.ExerciseTypeHelper;
import com.example.myapplication.db.UserInfoHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class create_table extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table);



        button1=findViewById(R.id.button_User);
        button2=findViewById(R.id.button_exeType);
        button3=findViewById(R.id.button_exeRecord);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserInfoHelper userInfoHelper=new UserInfoHelper(create_table.this);
                UserInfo userInfo=new UserInfo(1,"浅梦朋","user123","123456","17731098019",175,65,60,21,"男",10,10,10);
                System.out.println(userInfo.toString());
                String str=userInfoHelper.insertUserInfo(userInfo);
                Toast.makeText(create_table.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExerciseType pushUps = new ExerciseType(1, "俯卧撑");
                ExerciseType sitUps = new ExerciseType(2, "蹲起");
                ExerciseType squats = new ExerciseType(3, "引体向上");

                ExerciseTypeHelper exerciseTypeHelper=new ExerciseTypeHelper(create_table.this);
                exerciseTypeHelper.insertExerciseType(pushUps);
                exerciseTypeHelper.insertExerciseType(sitUps);
                exerciseTypeHelper.insertExerciseType(squats);
                Toast.makeText(create_table.this, "添加成功", Toast.LENGTH_SHORT).show();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // 定义日期时间格式
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime exerciseDatetime1 = LocalDateTime.parse("2024-04-05T10:30",formatter);
                LocalDateTime exerciseDatetime2 = LocalDateTime.parse("2024-04-08T10:30",formatter);
                LocalDateTime exerciseDatetime3 = LocalDateTime.parse("2024-04-12T10:30",formatter);
                LocalDateTime exerciseDatetime0 = LocalDateTime.parse("2024-04-13T10:30",formatter);
                LocalDateTime exerciseDatetime4 = LocalDateTime.parse("2024-04-15T10:32",formatter);

                ExerciseRecord exerciseRecord1=new ExerciseRecord(10,1,1,exerciseDatetime1,60,21);
                ExerciseRecord exerciseRecord2=new ExerciseRecord(11,1,2,exerciseDatetime2,45,33);
                ExerciseRecord exerciseRecord3=new ExerciseRecord(12,1,3,exerciseDatetime3,10,7);
                ExerciseRecord exerciseRecord4=new ExerciseRecord(13,1,1,exerciseDatetime0,36,12);
                ExerciseRecord exerciseRecord5=new ExerciseRecord(14,1,2,exerciseDatetime4,136,60);
                ExerciseRecord exerciseRecord6=new ExerciseRecord(15,1,3,exerciseDatetime4,33,4);
                ExerciseRecord exerciseRecord7=new ExerciseRecord(16,1,1,exerciseDatetime4,55,10);

                ExerciseRecordHelper exerciseRecordHelper=new ExerciseRecordHelper(create_table.this);
                exerciseRecordHelper.insertExerciseRecord(exerciseRecord1);
                exerciseRecordHelper.insertExerciseRecord(exerciseRecord2);
                exerciseRecordHelper.insertExerciseRecord(exerciseRecord3);
                exerciseRecordHelper.insertExerciseRecord(exerciseRecord4);
                exerciseRecordHelper.insertExerciseRecord(exerciseRecord5);
                exerciseRecordHelper.insertExerciseRecord(exerciseRecord6);
                exerciseRecordHelper.insertExerciseRecord(exerciseRecord7);
                Toast.makeText(create_table.this, "添加成功", Toast.LENGTH_SHORT).show();

            }
        });


    }
}