package com.space.licht.envisiondemo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.RootView;
import com.space.licht.envisiondemo.component.ImageLoader;
import com.space.licht.envisiondemo.preserter.contract.WelcomeContract;
import com.space.licht.envisiondemo.ui.activitys.WelcomeActivity;
import com.space.licht.envisiondemo.utils.EventUtil;
import com.space.licht.envisiondemo.utils.JumpUtil;
import com.space.licht.envisiondemo.utils.Preconditions;
import com.space.licht.envisiondemo.utils.StringUtils;

import java.util.List;

import butterknife.BindView;


/**
 * Description: WelcomeView
 * 欢迎界面的设计
 */
public class WelcomeView extends RootView<WelcomeContract.Presenter> implements WelcomeContract.View {


    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;

    public WelcomeView(Context context) {
        super(context);
    }

    public WelcomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void getLayout() {
        inflate(mContext, R.layout.activity_welcome_view, this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
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
    public void showContent(List<String> list) {
        if (list != null) {
            int page = StringUtils.getRandomNumber(0, list.size() - 1);
            ImageLoader.load(mContext, list.get(page), ivWelcomeBg);
            ivWelcomeBg.animate().scaleX(2.12f).scaleY(2.12f).setDuration(4000).setStartDelay(100).start();
        }

    }

    @Override
    public void jumpToMain() {
        JumpUtil.go2MainActivity(mContext);

        //fade_in是第二个activity进入时的动画    fade_out是第一个activity退出时的动画；  系统自带淡入淡出的动画
        ((WelcomeActivity) mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
