package com.space.licht.envisiondemo.ui.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.RootView;
import com.space.licht.envisiondemo.model.bean.VideoType;
import com.space.licht.envisiondemo.preserter.VideoInfoPresenter;
import com.space.licht.envisiondemo.preserter.contract.MineContract;
import com.space.licht.envisiondemo.ui.adapter.MineHistoryVideoListAdapter;
import com.space.licht.envisiondemo.utils.EventUtil;
import com.space.licht.envisiondemo.utils.Preconditions;
import com.space.licht.envisiondemo.widget.theme.ColorTextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Description: MineView
 */
public class MineView extends RootView<MineContract.Presenter> implements MineContract.View {

    MineHistoryVideoListAdapter mAdapter;
    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public MineView(Context context) {
        super(context);
    }

    public MineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.fragment_mine_view, this);
    }

    @Override
    protected void initView() {
        ((AppCompatActivity) getContext()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        titleName.setText(getResources().getString(R.string.mine_title));

    }

    @Override
    protected void initEvent() {
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                videoInfo = BeanUtil.VideoType2VideoInfo(mAdapter.getItem(position), videoInfo);
//                JumpUtil.go2VideoInfoActivity(getContext(), videoInfo);
            }
        });
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void showContent(List<VideoType> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
        if (list.size() > 0) {
        } else {
        }
    }


    @OnClick({ R.id.img_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_setting:
                EventUtil.showToast(getContext(), "敬请期待");
//                getContext().startActivity(new Intent(mContext, SettingActivity.class));
                break;
        }
    }

    @Subscriber(tag = VideoInfoPresenter.Refresh_History_List)
    public void setData(String tag) {
        mPresenter.getHistoryData();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }
}
