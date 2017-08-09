package com.space.licht.envisiondemo.ui.fragment.video;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.ui.activitys.ChartVideoActivity;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description: MemberFragment
 */
public class VideoFragment extends Fragment {

    private static final String TAG = "VideoFragment";
    @BindView(R.id.video_fragment_btn)
    Button mVideoFragmentBtn;
    @BindView(R.id.cb_select)
    CheckBox mCbSelect;
    @BindView(R.id.cb_select_mother)
    CheckBox mCbSelectMother;
    @BindView(R.id.cb_select_son)
    CheckBox mCbSelectSon;
    @BindView(R.id.cb_select_Daughter)
    CheckBox mCbSelectDaughter;
    @BindView(R.id.cb_select_Nephew)
    CheckBox mCbSelectNephew;
    @BindView(R.id.cb_select_pad)
    CheckBox mCbSelectPad;

    private List<Boolean> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View mView = inflater.inflate(R.layout.fragment_video_view, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < 5; i++) {
            list.add(false);
        }
        initEvent();
    }

    private void initEvent() {
        mCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(TAG, "onCheckedChanged: "+isChecked );
                if (isChecked) {
                    for (int i = 0; i < 5; i++) {
                        list.add(i,true);
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        list.add(i,false);
                    }
                }
                updataState();
            }
        });
        mCbSelectMother.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(0, true);
                } else {
                    list.add(0, false);
                }
                isAllCheck();
            }
        });
        mCbSelectSon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(1, true);
                } else {
                    list.add(1, false);
                }
                isAllCheck();
            }
        });
        mCbSelectDaughter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(2, true);
                } else {
                    list.add(2, false);
                }
                isAllCheck();
            }
        });
        mCbSelectNephew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(3, true);
                } else {
                    list.add(3, false);
                }
                isAllCheck();
            }
        });
        mCbSelectPad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(4, true);
                } else {
                    list.add(4, false);
                }
                isAllCheck();
            }
        });
    }

    private void updataState() {
        for (int i = 0; i < 5; i++) {
            Log.e(TAG, "updataState: " + list.get(i));
        }

        mCbSelectMother.setChecked(list.get(0));
        mCbSelectSon.setChecked(list.get(1));
        mCbSelectDaughter.setChecked(list.get(2));
        mCbSelectNephew.setChecked(list.get(3));
        mCbSelectPad.setChecked(list.get(4));
    }


    @OnClick(R.id.video_fragment_btn)
    public void onClick() {
        JumpUtil.jump(getContext(), ChartVideoActivity.class);
    }

    public void isAllCheck() {
        boolean isAll = true;
        for (int i = 0; i < 5; i++) {
            isAll = isAll && list.get(i);
            Log.e(TAG, "isAllCheck: " + list.get(i));
        }
        if (isAll) {
            mCbSelect.setChecked(true);
        } else {
            mCbSelect.setChecked(false);
        }

    }
}
