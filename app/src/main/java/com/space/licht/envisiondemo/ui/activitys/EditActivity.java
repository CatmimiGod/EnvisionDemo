package com.space.licht.envisiondemo.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.ui.fragment.setting.TimeBean;
import com.space.licht.envisiondemo.utils.JumpUtil;
import com.space.licht.envisiondemo.utils.PreUtils;
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
    @BindView(R.id.activity_edit_cancel)
    TextView mActivityEditCancel;
    @BindView(R.id.activity_edit_save)
    TextView mActivityEditSave;
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
        Intent intent = getIntent();
        String selected = intent.getStringExtra("selected");



        mActivityEditPickerviewDay.setData(mWeek);
        mActivityEditPickerviewHour.setData(mMonth);
        mActivityEditPickerviewMin.setData(mMinute);
        mActivityEditPickerviewTime.setData(mtimeQuantum);


        if (!selected.isEmpty()){
            int selecte = Integer.parseInt(selected);
            List<TimeBean> timeData = App.sTimeData;
            TimeBean timeBean = timeData.get(selecte);
            initView(timeBean);
        }

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
                        hour = hour - 12;
                } else {
                    hour = hour + 12;
                }

                if (isForm) {
                    mActivityEditFromTime.setText(hour + ":" + min);
                } else {
                    mActivityEditToTime.setText(hour + ":" + min);
                }
            }
        });
    }

    private void initView(TimeBean timeBean) {
        mActivityEditFromDay.setText(timeBean.getsStartDay());
        mActivityEditToDay.setText(timeBean.getsStopDay());
        if ("AM".equals(timeBean.getsStartAMorPM())){
            mActivityEditFromTime.setText(timeBean.getsStartHour()+":"+timeBean.getsStartMins());
            mActivityEditToTime.setText(timeBean.getsStartHour()+":"+timeBean.getsStartMins());
        }else{
            mActivityEditFromTime.setText((Integer.parseInt(timeBean.getsStartHour())+12)+":"+timeBean.getsStartMins());
            mActivityEditToTime.setText((Integer.parseInt(timeBean.getsStopHour())+12)+":"+timeBean.getsStopMins());
        }
        mActivityEditPickerviewDay.setSelected(timeBean.getsStartDay());
        mActivityEditPickerviewHour.setSelected(Integer.parseInt(timeBean.getsStartHour())+"");
        mActivityEditPickerviewMin.setSelected(timeBean.getsStartMins());
        mActivityEditPickerviewTime.setSelected(timeBean.getsStartAMorPM());
    }

    private void initDate() {
        mWeek = new ArrayList<String>();
        mtimeQuantum = new ArrayList<String>();
        mMonth = new ArrayList<String>();
        mMinute = new ArrayList<String>();
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] timeQuantum = {"PM", "AM"};
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

    @OnClick({R.id.activity_edit_from_day, R.id.activity_edit_delete, R.id.activity_edit_from_time, R.id.activity_edit_to_day, R.id.activity_edit_to_time, R.id.activity_edit_cancel, R.id.activity_edit_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_edit_from_day:
            case R.id.activity_edit_from_time:
                mActivityEditPickerviewDay.setSelected((String) mActivityEditFromDay.getText());
                isForm = true;
                break;
            case R.id.activity_edit_to_day:
            case R.id.activity_edit_to_time:
                mActivityEditPickerviewDay.setSelected((String) mActivityEditToDay.getText());
                isForm = false;
                break;

            case R.id.activity_edit_cancel:
                finish();
                break;
            case R.id.activity_edit_delete:
                finish();
                break;
            case R.id.activity_edit_save:
                //使用share来模拟
                String data = mActivityEditFromDay.getText() + "-" + mActivityEditFromTime.getText() + "-" + mActivityEditToDay.getText() + "-" + mActivityEditToTime.getText();
                PreUtils.putString(this, "time", data);
                JumpUtil.jump(this,CallContrlVideoActivity.class);
                finish();
                break;
        }
    }
}
