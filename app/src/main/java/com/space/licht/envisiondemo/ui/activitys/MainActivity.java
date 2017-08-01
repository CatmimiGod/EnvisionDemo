package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.ui.view.MainView;
import com.space.licht.envisiondemo.utils.EventUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 程序主界面
 */

public class MainActivity extends BaseActivity  {

    public static final String Set_Theme_Color = "Set_Theme_Color";
    public final static String Banner_Stop = "Banner_Stop";
    private Long firstTime = 0L;
    @BindView(R.id.main_view)
    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1500) {
            EventUtil.showToast(this, "再按一次退出");
            firstTime = secondTime;
        } else {
            App.getInstance().exitApp();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}