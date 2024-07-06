package com.example.myapplication;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.myapplication.bean.ExerciseRecord;
import com.example.myapplication.db.ExerciseRecordHelper;
import java.util.List;
public class Fragment_Me extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LinearLayout homepage;
    private LinearLayout jksj;
    private LinearLayout ydjl;
    private LinearLayout txsz;
    private LinearLayout dkjl;
    private LinearLayout mbsd;
    private TextView me_exercise_time;
    private TextView usernameTV;
    private TextView recordDaysTextView;
    private TextView exit;
    private TextView insert_data;
    private String mParam1;
    private String mParam2;
    public Fragment_Me() {
    }
    public static Fragment_Me newInstance(String param1, String param2) {
        Fragment_Me fragment = new Fragment_Me();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me, null);
        findViewById(v);
        initView();
        return v;
    }
    public void findViewById(View v) {
        homepage = (LinearLayout) v.findViewById(R.id.me_homepage);
        jksj = (LinearLayout) v.findViewById(R.id.jksj_line);
        ydjl = (LinearLayout) v.findViewById(R.id.ydjl_line);
        txsz = (LinearLayout) v.findViewById(R.id.txsz_line);
        dkjl = (LinearLayout) v.findViewById(R.id.dkjl_line);
        mbsd = (LinearLayout) v.findViewById(R.id.mbsd_line);
        exit = (TextView) v.findViewById(R.id.me_item_exit);
        insert_data=(TextView) v.findViewById(R.id.insert_data);
        usernameTV = (TextView) v.findViewById(R.id.me_homepage_username);
        recordDaysTextView = (TextView) v.findViewById(R.id.me_record_days);
        me_exercise_time=(TextView) v.findViewById(R.id.me_exercise_time);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initView() {
        ExerciseRecordHelper exerciseRecordHelper=new ExerciseRecordHelper(getActivity());
        int time_sum=0;
        int day_sum=0;
        List<ExerciseRecord>list=exerciseRecordHelper.selectAllExerciseRecord();
        for (int i = 0; i < list.size(); i++) {
            time_sum+=list.get(i).getDuration();
            day_sum++;
        }
        me_exercise_time.setText(String.valueOf(time_sum));
        recordDaysTextView.setText(String.valueOf(day_sum));
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HomepageActivity.class);
                startActivity(intent);
            }
        });
        jksj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), jksj_activity.class);
                startActivity(intent);
            }
        });
        ydjl.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ydjl_activity.class);
                startActivity(intent);
            }
        });
        txsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), txsz_activity.class);
                startActivity(intent);
            }
        });
        dkjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), dkjl_activity.class);
                startActivity(intent);
            }
        });
        mbsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), mbsd_activity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        insert_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), create_table.class);
                startActivity(intent);
            }
        });
    }
}