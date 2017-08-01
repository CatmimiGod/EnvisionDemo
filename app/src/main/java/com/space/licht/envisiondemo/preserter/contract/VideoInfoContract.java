package com.space.licht.envisiondemo.preserter.contract;


import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.base.BaseView;
import com.space.licht.envisiondemo.model.bean.VideoRes;

public interface VideoInfoContract {

    interface View extends BaseView<Presenter> {

        void showContent(VideoRes videoRes);

        boolean isActive();

        void hidLoading();

        void collected();

        void disCollect();
    }

    interface Presenter extends BasePresenter {
        void getDetailData(String dataId);

        void collect();

        void insertRecord();

    }
}
