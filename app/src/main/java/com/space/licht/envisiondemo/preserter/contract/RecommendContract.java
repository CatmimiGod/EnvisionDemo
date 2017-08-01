package com.space.licht.envisiondemo.preserter.contract;


import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.base.BaseView;
import com.space.licht.envisiondemo.model.bean.VideoRes;

/**
 * Description: RecommendContract
 */
public interface RecommendContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showContent(VideoRes videoRes);

        void refreshFaild(String msg);

        void stopBanner(boolean stop);
    }

    interface Presenter extends BasePresenter {
        void onRefresh();
    }
}
