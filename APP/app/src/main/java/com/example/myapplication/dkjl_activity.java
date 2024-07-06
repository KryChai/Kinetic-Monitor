package com.example.myapplication;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class dkjl_activity extends AppCompatActivity {
    private LinearLayout back_dkjl;
    private TextView textView_showNum;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dkjl);
        textView_showNum=findViewById(R.id.dkjl_num);
        back_dkjl=findViewById(R.id.dkjl_back);
        GridView calendarGridView = findViewById(R.id.calendarGridView);
        CalendarAdapter adapter = new CalendarAdapter(this,textView_showNum);
        calendarGridView.setAdapter(adapter);
        back_dkjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}