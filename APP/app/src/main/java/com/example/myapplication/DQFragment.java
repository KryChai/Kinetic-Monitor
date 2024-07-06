package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DQFragment extends Fragment {

    private Button beginExercise;
    private FragmentExecisePage outerFragment; // 假设的外部 Fragment 实例

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DQFragment newInstance(String param1, String param2, FragmentExecisePage outerFragment) {
        DQFragment fragment = new DQFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.outerFragment = outerFragment; // 传递外部 Fragment 实例
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context=getContext();
        View view = inflater.inflate(R.layout.fragment_d_q, container, false);

        beginExercise=view.findViewById(R.id.dq_begin);
        beginExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                outerFragment.startActivity(new Intent(getActivity(), camera2.class));
                Intent intent=new Intent(context, CameraForDQ.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
