package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.preserter.WelcomePresenter;
import com.space.licht.envisiondemo.ui.view.WelcomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_view)
    WelcomeView welcomeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //ButterKnife绑定view
        unbinder = ButterKnife.bind(this);
        //将view交给Presenter进行统一管理  MVP
        mPresenter = new WelcomePresenter(welcomeView);
    }
}