package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentExecisePage extends Fragment {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;

    private List<String> mTitleList;
    private List<Fragment> mFragmentList;

    public FragmentExecisePage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_execise_page, container, false);

        mViewPager = view.findViewById(R.id.viewPager);
//        mTabLayout = view.findViewById(R.id.tabLayout);
        mRadioGroup = view.findViewById(R.id.radioGroup);


        // 初始化标题和 Fragment
        mTitleList = new ArrayList<>();
        mTitleList.add("俯卧撑");
        mTitleList.add("蹲起");
        mTitleList.add("引体向上");

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new FWCFragment());
        mFragmentList.add(new DQFragment());
        mFragmentList.add(new YTXSFragment());
        mViewPager.setAdapter(new MyAdapter(getFragmentManager()));
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(i);
            final int finalI = i;
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(finalI);
                }
            });
        }

        return view;
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
