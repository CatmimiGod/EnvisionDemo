package com.space.licht.envisiondemo.ui.fragment;


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
import com.space.licht.envisiondemo.ui.fragment.classification.DataBean;

import java.util.ArrayList;

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

    private ArrayList<DataBean> mSGDatas;
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
        mSGDatas = new ArrayList<DataBean>();
        DataBean sgbean1 = new DataBean();
        sgbean1.setSgName("刘备");
        sgbean1.setSgPetName("玄德");
        sgbean1.setSgHeadBp(R.drawable.father);
        sgbean1.setSgDescribe("刘备");
        DataBean sgbean2 = new DataBean();
        sgbean2.setSgName("关羽");
        sgbean2.setSgPetName("云长");
        sgbean2.setSgHeadBp(R.drawable.father_large);
        sgbean2.setSgDescribe("关羽");
        DataBean sgbean3 = new DataBean();
        sgbean3.setSgName("张飞");
        sgbean3.setSgPetName("翼德");
        sgbean3.setSgHeadBp(R.drawable.mother);
        sgbean3.setSgDescribe("张飞");
        DataBean sgbean4 = new DataBean();
        sgbean4.setSgName("赵云");
        sgbean4.setSgPetName("子龙");
        sgbean4.setSgHeadBp(R.drawable.son);
        sgbean4.setSgDescribe("赵云");
        DataBean sgbean5 = new DataBean();
        sgbean5.setSgName("马超");
        sgbean5.setSgPetName("孟起");
        sgbean5.setSgHeadBp(R.drawable.daughter);
        sgbean5.setSgDescribe("马超");
        mSGDatas.add(sgbean1);
        mSGDatas.add(sgbean2);
        mSGDatas.add(sgbean3);
        mSGDatas.add(sgbean4);
        mSGDatas.add(sgbean5);
    }

    /**
     * 初始化控件
     */
    private void initViewI() {
        mSgAdapter = new SettingAdapter(getContext(), mSGDatas);
        mSettingLv.setAdapter(mSgAdapter);

        mSettingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemClick: " + view + i);
                DataBean dataBean = mSGDatas.get(i);
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
                break;
        }
    }
}
