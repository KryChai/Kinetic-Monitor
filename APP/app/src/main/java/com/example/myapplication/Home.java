package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    private Button ExitLogin;

    private Button PersonInf;

    private Button Exercise;

    private Button DataAnalysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ExitLogin=findViewById(R.id.Exit);
        PersonInf=findViewById(R.id.Personal_Information);
        Exercise=findViewById(R.id.Exercise);
        DataAnalysis=findViewById(R.id.Data_analysis);

        ExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });





        DataAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, DataAnalysis.class);
                startActivity(intent);
            }
        });
    }
}