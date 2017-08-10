package com.space.licht.envisiondemo.ui.fragment.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.ui.activitys.BlackListActivity;
import com.space.licht.envisiondemo.ui.activitys.BlockCellularDataActivity;
import com.space.licht.envisiondemo.ui.activitys.CallOutActivity;
import com.space.licht.envisiondemo.ui.activitys.OneNumberActivity;
import com.space.licht.envisiondemo.ui.activitys.WhiteListActivity;
import com.space.licht.envisiondemo.utils.JumpUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description: SettingcontrolFragment
 */
public class SettingcontrolFragment extends Fragment {

    private static final String TAG = "SettingcontrolFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View mView = inflater.inflate(R.layout.fragment_setting_control, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @OnClick({R.id.setting_block_receive_call, R.id.setting_call_out, R.id.setting_one_number, R.id.setting_Whitelist, R.id.setting_Blacklist, R.id.setting_Block_cellular_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_block_receive_call:
                break;
            case R.id.setting_call_out:
                JumpUtil.jump(getContext(),CallOutActivity.class);
                break;
            case R.id.setting_one_number:
                //1号转接
                JumpUtil.jump(getContext(),OneNumberActivity.class);
                break;
            case R.id.setting_Whitelist:
                //白名单
                JumpUtil.jump(getContext(),WhiteListActivity.class);
                break;
            case R.id.setting_Blacklist:
                //黑名单
                JumpUtil.jump(getContext(),BlackListActivity.class);
                break;
            case R.id.setting_Block_cellular_data:
                //断网开关
                JumpUtil.jump(getContext(),BlockCellularDataActivity.class);
                break;
        }
    }
}
