package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.widget.PickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by licht
 * 2017/8/1 0001.
 */
public class EditActivity extends BaseActivity {
    @BindView(R.id.activity_edit_pickerviewone)
    PickerView mActivityEditPickerviewone;

    @BindView(R.id.activity_edit_delete)
    TextView mActivityEditDelete;
    @BindView(R.id.activity_edit_pickerviewtwo)
    PickerView mActivityEditPickerviewtwo;
    @BindView(R.id.activity_edit_pickerviewthree)
    PickerView mActivityEditPickerviewthree;
    @BindView(R.id.activity_edit_pickerviewfour)
    PickerView mActivityEditPickerviewfour;
    private List<String> mWeek;
    private List<String> mtimeQuantum;
    private List<String> mMonth;
    private List<String> mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        initDate();

        mActivityEditPickerviewone.setData(mWeek);
        mActivityEditPickerviewtwo.setData(mMonth);
        mActivityEditPickerviewone.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                Toast.makeText(EditActivity.this, "选择了 " + text,
                        Toast.LENGTH_SHORT).show();
            }
        });
        mActivityEditPickerviewthree.setData(mMinute);
        mActivityEditPickerviewfour.setData(mtimeQuantum);
        mActivityEditPickerviewthree.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                Toast.makeText(EditActivity.this, "选择了 " + text + " 秒",
                        Toast.LENGTH_SHORT).show();
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
        for (int i = 1; i < timeQuantum.length; i++) {
            mtimeQuantum.add(timeQuantum[i]);
        }


    }
}
