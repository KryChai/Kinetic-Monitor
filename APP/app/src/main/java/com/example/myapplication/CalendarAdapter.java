package com.example.myapplication;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.myapplication.bean.ExerciseRecord;
import com.example.myapplication.db.ExerciseRecordHelper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class CalendarAdapter  extends BaseAdapter {
    private Context mContext;
    private List<Integer> mDaysOfMonth;
    private List<Boolean> mPunchedDates;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public CalendarAdapter(Context context,TextView textView_showNum) {
        mContext = context;
        mDaysOfMonth = new ArrayList<>();
        mPunchedDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysInMonth; i++) {
            mDaysOfMonth.add(i);
            mPunchedDates.add(false);
        }
        int num=0;
        ExerciseRecordHelper exerciseRecordHelper=new ExerciseRecordHelper(mContext);
        List<ExerciseRecord>list=exerciseRecordHelper.selectAllExerciseRecord();
        for (int i = 0; i < list.size(); i++) {
            LocalDateTime date=list.get(i).getExerciseDatetime();
            int month = date.getMonthValue();
            int dayOfMonth = date.getDayOfMonth();
            if(month==LocalDateTime.now().getMonthValue()&& !mPunchedDates.get(dayOfMonth - 1)){
                mPunchedDates.set(dayOfMonth-1, true);
                num++;
            }
        }
        textView_showNum.setText("本月总计打卡天数："+String.valueOf(num)+"天");
    }
    @Override
    public int getCount() {
        return mDaysOfMonth.size();
    }
    @Override
    public Object getItem(int position) {
        return mDaysOfMonth.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(mContext);
            textView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            textView.setPadding(5, 5, 5, 5);
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER);
        } else {
            textView = (TextView) convertView;
        }
        int dayOfMonth = mDaysOfMonth.get(position);
        textView.setText(String.valueOf(dayOfMonth));
        if (mPunchedDates.get(position)) {
            Drawable background = mContext.getResources().getDrawable(R.drawable.rounded_corner_background);
            textView.setBackground(background);
        } else {
            textView.setBackground(null);
        }
        return textView;
    }
}
