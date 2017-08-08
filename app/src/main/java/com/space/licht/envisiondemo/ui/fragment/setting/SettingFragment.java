package com.space.licht.envisiondemo.ui.fragment.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.ui.activitys.AddMemberActivity;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description: MemberFragment
 */
public class SettingFragment extends Fragment {

    private static final String TAG = "classfication";
    @BindView(R.id.setting_lv)
    ListView mSettingLv;
    @BindView(R.id.setting_back)
    ImageView mSettingBack;
    @BindView(R.id.setting_add_manage)
    ImageView mSettingAddManage;
    @BindView(R.id.setting_fl)
    FrameLayout mSettingFl;

    private SettingAdapter mSgAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View mView = inflater.inflate(R.layout.fragment_setting_view, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataI();
        initViewI();
    }

    /**
     * 初始化数据
     */
    private void initDataI() {

    }

    /**
     * 初始化控件
     */
    private void initViewI() {
        final List<Collection> data = App.sData;
        mSgAdapter = new SettingAdapter(getContext(), data);
        mSettingLv.setAdapter(mSgAdapter);

        mSettingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemClick: " + view + i);
                Collection dataBean = data.get(i);
                //切换fragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                // 将 fragment_container View 中的内容替换为此 Fragment ，
                // 然后将该事务添加到返回堆栈，以便用户可以向后导航
                getActivity().findViewById(R.id.setting_fl).setVisibility(View.VISIBLE);
                transaction.replace(R.id.setting_fl, new SettingcontrolFragment());
                transaction.addToBackStack(null);

                // 执行事务
                transaction.commit();
                mSettingFl.setVisibility(View.VISIBLE);
                mSettingBack.setVisibility(View.VISIBLE);
                mSettingAddManage.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.setting_back, R.id.setting_add_manage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                mSettingBack.setVisibility(View.GONE);
                mSettingAddManage.setVisibility(View.VISIBLE);
                mSettingFl.setVisibility(View.GONE);
                break;
            case R.id.setting_add_manage:
                //TODO 跳转至增加页面
                JumpUtil.jump(getContext(), AddMemberActivity.class);
                break;
        }
    }
}
