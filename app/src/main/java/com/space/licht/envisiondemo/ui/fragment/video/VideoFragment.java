package com.space.licht.envisiondemo.ui.fragment.video;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.ui.activitys.ChartVideoActivity;
import com.space.licht.envisiondemo.ui.fragment.Store;
import com.space.licht.envisiondemo.ui.fragment.StoreAdapter;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.space.licht.envisiondemo.R.id.lv_store;


/**
 * Description: MemberFragment
 */
public class VideoFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "VideoFragment";
    @BindView(lv_store)
    ListView mLvStore;
    @BindView(R.id.video_fragment_btn)
    Button mVideoFragmentBtn;
    private ArrayList<Store> mList;
    private StoreAdapter mAdapter;

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
        initViewI();
    }


    /**
     * 初始化控件
     */
    private void initViewI() {
        mAdapter = new StoreAdapter(getContext(), mList);

        mLvStore.setAdapter(mAdapter);
        mLvStore.setOnItemClickListener(this);
        mLvStore.setEnabled(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @OnClick(R.id.video_fragment_btn)
    public void onClick() {
        JumpUtil.jump(getContext(), ChartVideoActivity.class);
    }
}
