package com.space.licht.envisiondemo.ui.fragment.classification;


import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.newbase.BaseView;

/**
 * Description: ClassificationContract
 */
public interface ClassificationContract {

    interface View extends BaseView {

        boolean isActive();

        void showContent(VideoRes videoRes);

        void refreshFaild(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();
    }
}
