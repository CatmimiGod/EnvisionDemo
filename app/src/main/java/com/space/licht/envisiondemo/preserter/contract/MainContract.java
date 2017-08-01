package com.space.licht.envisiondemo.preserter.contract;


import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.base.BaseView;

/**
 * Description: MainContract
 * Creator: yxc
 * date: 2016/10/20 10:14
 */
public interface MainContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
    }
}
