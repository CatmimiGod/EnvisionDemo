package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.widget.PickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by licht
 * 2017/8/1 0001.
 */
public class EditActivity extends BaseActivity {


    @BindView(R.id.activity_edit_pickerview_day)
    PickerView mActivityEditPickerviewDay;
    @BindView(R.id.activity_edit_pickerview_hour)
    PickerView mActivityEditPickerviewHour;
    @BindView(R.id.activity_edit_pickerview_min)
    PickerView mActivityEditPickerviewMin;
    @BindView(R.id.activity_edit_pickerview_time)
    PickerView mActivityEditPickerviewTime;
    @BindView(R.id.activity_edit_delete)
    TextView mActivityEditDelete;
    @BindView(R.id.activity_edit_from_day)
    TextView mActivityEditFromDay;
    @BindView(R.id.activity_edit_from_time)
    TextView mActivityEditFromTime;
    @BindView(R.id.activity_edit_to_day)
    TextView mActivityEditToDay;
    @BindView(R.id.activity_edit_to_time)
    TextView mActivityEditToTime;
    private List<String> mWeek;
    private List<String> mtimeQuantum;
    private List<String> mMonth;
    private List<String> mMinute;
    private boolean isForm = true;
    private int hour = 7;
    private String min = "30";
    private String time = "AM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        initDate();
        mActivityEditPickerviewDay.setData(mWeek);
        mActivityEditPickerviewHour.setData(mMonth);
        mActivityEditPickerviewMin.setData(mMinute);
        mActivityEditPickerviewTime.setData(mtimeQuantum);


        mActivityEditPickerviewDay.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                if (isForm) {
                    mActivityEditFromDay.setText(text);
                } else {
                    mActivityEditToDay.setText(text);
                }
            }
        });
        mActivityEditPickerviewHour.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                if ("AM".equals(time)) {
                    hour = Integer.parseInt(text);
                } else {
                    hour = Integer.parseInt(text) + 12;
                }

                if (isForm) {
                    mActivityEditFromTime.setText(hour + ":" + min);
                } else {
                    mActivityEditToTime.setText(hour + ":" + min);
                }
            }
        });
        mActivityEditPickerviewMin.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                min = text;
                if (isForm) {
                    mActivityEditFromTime.setText(hour + ":" + min);
                } else {
                    mActivityEditToTime.setText(hour + ":" + min);
                }
            }
        });
        mActivityEditPickerviewTime.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                time = text;
                if ("AM".equals(time)) {
                    if (hour > 12)
                    hour = Integer.parseInt(text)-12;
                } else {
                    hour = Integer.parseInt(text) + 12;
                }

                if (isForm) {
                    mActivityEditFromTime.setText(hour + ":" + min);
                } else {
                    mActivityEditToTime.setText(hour + ":" + min);
                }
            }
        });
    }

    private void initDate() {
        mWeek = new ArrayList<String>();
        mtimeQuantum = new ArrayList<String>();
        mMonth = new ArrayList<String>();
        mMinute = new ArrayList<String>();
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] timeQuantum = {"AM", "PM"};
        for (int i = 0; i < week.length; i++) {
            mWeek.add(week[i]);
        }

        for (int i = 1; i < 13; i++) {
            mMonth.add(i + "");
        }
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                mMinute.add("0" + i);
            } else {
                mMinute.add(i + "");
            }

        }
        for (int i = 0; i < timeQuantum.length; i++) {
            mtimeQuantum.add(timeQuantum[i]);
        }


    }

    @OnClick({R.id.activity_edit_from_day, R.id.activity_edit_from_time, R.id.activity_edit_to_day, R.id.activity_edit_to_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_edit_from_day:
            case R.id.activity_edit_from_time:
                isForm = true;
                break;
            case R.id.activity_edit_to_day:
            case R.id.activity_edit_to_time:
                isForm = false;
                break;
        }
    }
}
