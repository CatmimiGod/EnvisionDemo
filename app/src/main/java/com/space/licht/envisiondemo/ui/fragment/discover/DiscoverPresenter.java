package com.space.licht.envisiondemo.ui.fragment.discover;


import com.space.licht.envisiondemo.model.DataManager;
import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.model.http.response.VideoHttpResponse;
import com.space.licht.envisiondemo.preserter.RxPresenter;
import com.space.licht.envisiondemo.utils.RxUtil;
import com.space.licht.envisiondemo.utils.StringUtils;
import com.space.licht.envisiondemo.utils.SystemUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Description: DiscoverPresenter
 */
public class DiscoverPresenter extends RxPresenter<DiscoverContract.View> implements DiscoverContract.Presenter {
    final String catalogId = "402834815584e463015584e53843000b";

    int max = 90;
    int min = 1;

    private DataManager mDataManager;

    @Inject
    public DiscoverPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getData() {
        getNextVideos();
    }

    private void getNextVideos() {
        Subscription rxSubscription = mDataManager.fetchVideoList(catalogId, getNextPage() + "")
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                               @Override
                               public void call(final VideoRes res) {
                                   if (res != null) {
                                       mView.showContent(res);
                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {

                                   mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                               }
                           }, new Action0() {
                               @Override
                               public void call() {
                                   mView.hidLoading();
                               }
                           }
                );

        addSubscribe(rxSubscription);
    }


    private int getNextPage() {
        int page = mView.getLastPage();
        if (SystemUtils.isNetworkConnected()) {
            page = StringUtils.getRandomNumber(min, max);
            mView.setLastPage(page);
        }
        return page;
    }


}
