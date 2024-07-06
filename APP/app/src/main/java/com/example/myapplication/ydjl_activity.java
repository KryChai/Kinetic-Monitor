package com.example.myapplication;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.bean.ExerciseRecord;
import com.example.myapplication.db.ExerciseRecordHelper;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class ydjl_activity extends AppCompatActivity {
    private Spinner dateFilterSpinner;
    private String[] filterOptions = {"近一周", "近一月"};
    private LinearLayout mainLinearLayout;
    private LinearLayout back_linear;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydjl);
        ExerciseRecordHelper exerciseRecordHelper=new ExerciseRecordHelper(ydjl_activity.this);
        List<ExerciseRecord>exerciseRecords=exerciseRecordHelper.selectAllExerciseRecord();
        dateFilterSpinner = findViewById(R.id.dateFilterSpinner);
        // 获取主线性布局
        mainLinearLayout = findViewById(R.id.main_linearlaout_Record);
        back_linear=findViewById(R.id.ydjl_back);
        back_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filterOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateFilterSpinner.setAdapter(adapter);
        dateFilterSpinner.setSelection(0);
        dateFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = filterOptions[position];
                if (selectedOption.equals("近一周")) {
                    List<ExerciseRecord>exerciseRecords_new=new ArrayList<>();
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    String TimeNow = currentDateTime.format(formatter);
                    LocalDateTime dateTime2 = LocalDateTime.parse(TimeNow, formatter);
                    for (int i = 0; i < exerciseRecords.size(); i++) {
                        Duration duration = Duration.between(exerciseRecords.get(i).getExerciseDatetime(), dateTime2);
                        if(duration.toDays()<=7)
                        {
                            exerciseRecords_new.add(exerciseRecords.get(i));
                        }
                    }
                    if (exerciseRecords_new != null) {
                        show_weeklylist(exerciseRecords_new);
                    }
                    else {
                        Toast.makeText(ydjl_activity.this, "最近一周懒惰了，没有锻炼哟", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (selectedOption.equals("近一月")) {
                    List<ExerciseRecord>exerciseRecords_new=new ArrayList<>();
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    String TimeNow = currentDateTime.format(formatter);
                    LocalDateTime dateTime2 = LocalDateTime.parse(TimeNow, formatter);
                    for (int i = 0; i < exerciseRecords.size(); i++) {
                        LocalDateTime readTime1=exerciseRecords.get(i).getExerciseDatetime();
                        String dateTime0 = readTime1.format(formatter);
                        LocalDateTime dateTime1 = LocalDateTime.parse(dateTime0, formatter);
                        Period period = Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());
                        if(period.getMonths()<=0&&period.getYears()<=0||period.getMonths()==1&&period.getYears()==0&&Duration.between(dateTime1, dateTime2).toDays()<=30)
                        {
                            exerciseRecords_new.add(exerciseRecords.get(i));
                        }
                    }
                    if (exerciseRecords_new != null) {
                        show_weeklylist(exerciseRecords_new);
                    }
                    else {
                        Toast.makeText(ydjl_activity.this, "最近一个月懒惰了，没有锻炼哟", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void show_weeklylist(List<ExerciseRecord>exerciseRecords)
    {
        mainLinearLayout.removeAllViews();
        for (ExerciseRecord record : exerciseRecords) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View listItem = inflater.inflate(R.layout.list_item_layout, null);
            TextView textView_1 = listItem.findViewById(R.id.list_item_text_1);
            TextView textView_2=listItem.findViewById(R.id.list_item_text_2);
            TextView textView_3=listItem.findViewById(R.id.list_item_text_3);
            ImageView imageView=listItem.findViewById(R.id.list_item_image);
            String string1="";
            String []item={"俯卧撑","蹲起","仰卧起坐"};
            string1="运动类型："+item[record.getExerciseId()-1]+"   运动数量："+String.valueOf(record.getQuantity());
            textView_1.setText(string1);
            String string2="";
            string2="运动时间："+String.valueOf(record.getDuration())+" s";
            textView_2.setText(string2);
            String string3="";
            string3="开始时间："+record.getExerciseDatetime().toString();
            textView_3.setText(string3);
            if(record.getExerciseId()==1)
            {
                imageView.setImageResource(R.drawable.fwc_label);
            }
            else if(record.getExerciseId()==2)
            {
                imageView.setImageResource(R.drawable.dq_label);
            }
            else if (record.getExerciseId()==3) {
                imageView.setImageResource(R.drawable.ywqz_label);
            }
            mainLinearLayout.addView(listItem);
        }
    }
}