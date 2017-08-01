package com.space.licht.envisiondemo.preserter.contract;


import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.base.BaseView;

import java.util.List;

/**
 * Description: WelcomeContract
 * 将view和presenter的接口统一管理
 */
public interface WelcomeContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showContent(List<String> list);

        void jumpToMain();
    }

    interface Presenter extends BasePresenter {
        void getWelcomeData();
    }
}
