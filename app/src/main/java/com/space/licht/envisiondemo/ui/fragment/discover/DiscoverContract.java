package com.space.licht.envisiondemo.ui.fragment.discover;


import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.newbase.BaseView;

/**
 * Description: RecommendContract
 */
public interface DiscoverContract {

    interface View extends BaseView {


        void showContent(VideoRes videoRes);

        void refreshFaild(String msg);

        void hidLoading();

        int getLastPage();

        void setLastPage(int page);
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
