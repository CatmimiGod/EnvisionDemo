package com.space.licht.envisiondemo.ui.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.RootView;
import com.space.licht.envisiondemo.preserter.contract.MainContract;
import com.space.licht.envisiondemo.ui.activitys.MainActivity;
import com.space.licht.envisiondemo.ui.adapter.ContentPagerAdapter;
import com.space.licht.envisiondemo.ui.fragment.ChartFragment;
import com.space.licht.envisiondemo.ui.fragment.CommunityFragment;
import com.space.licht.envisiondemo.ui.fragment.SettingFragment;
import com.space.licht.envisiondemo.ui.fragment.VideoFragment;
import com.space.licht.envisiondemo.ui.fragment.classification.MemberFragment;
import com.space.licht.envisiondemo.utils.EventUtil;
import com.space.licht.envisiondemo.utils.Preconditions;
import com.space.licht.envisiondemo.widget.UnScrollViewPager;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.space.licht.envisiondemo.ui.activitys.MainActivity.Banner_Stop;


/**
 * Description: MainView
 */
public class MainView extends RootView<MainContract.Presenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener {

    final int WAIT_TIME = 200;
    @BindView(R.id.tab_rg_menu)
    RadioGroup tabRgMenu;
    @BindView(R.id.vp_content)
    UnScrollViewPager vpContent;
    ContentPagerAdapter mPagerAdapter;
    MainActivity mActivity;

    public MainView(Context context) {
        super(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_main_view, this);
    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) mContext;
        //获取fragment的集合
        List<Fragment> fragments = initFragments();
        //UnScrollViewPager 自定义的viewpage
        vpContent.setScrollable(false);
        mPagerAdapter = new ContentPagerAdapter(mActivity.getSupportFragmentManager(), fragments);
        vpContent.setAdapter(mPagerAdapter);
        // Set the number of pages that should be retained to either side of
        // the current page in the view hierarchy in an idle state
        vpContent.setOffscreenPageLimit(fragments.size());
    }

    @Override
    protected void initEvent() {
        tabRgMenu.setOnCheckedChangeListener(this);
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) tabRgMenu.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    private void postBannerState(final boolean stop) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(stop, Banner_Stop);
            }
        }, WAIT_TIME);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.tab_rb_1:
                vpContent.setCurrentItem(0, false);
                break;
            case R.id.tab_rb_2:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.tab_rb_3:
                vpContent.setCurrentItem(2, false);
                break;
            case R.id.tab_rb_4:
                vpContent.setCurrentItem(3, false);
                break;
            case R.id.tab_rb_5:
                vpContent.setCurrentItem(4, false);
                break;
        }
    }

    //初始化4个fragment
    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment3 = new CommunityFragment();
        Fragment fragment2 = new MemberFragment();
        Fragment fragment1 = new ChartFragment();
        Fragment fragment4 = new SettingFragment();
        Fragment fragment5 = new VideoFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        return fragments;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }
}
