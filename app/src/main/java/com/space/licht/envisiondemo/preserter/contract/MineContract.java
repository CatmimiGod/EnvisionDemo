package com.space.licht.envisiondemo.preserter.contract;


import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.base.BaseView;
import com.space.licht.envisiondemo.model.bean.VideoType;

import java.util.List;

/**
 * Description: CollectionContract
 * Creator: cp
 * date: 2016/9/29 12:19
 */
public interface MineContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showContent(List<VideoType> list);

    }

    interface Presenter extends BasePresenter {
        void getHistoryData();

        void delAllHistory();
    }
}
