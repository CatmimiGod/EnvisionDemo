package com.space.licht.envisiondemo.preserter;

import android.support.annotation.NonNull;

import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.model.http.response.VideoHttpResponse;
import com.space.licht.envisiondemo.net.RetrofitHelper;
import com.space.licht.envisiondemo.preserter.contract.RecommendContract;
import com.space.licht.envisiondemo.utils.Preconditions;
import com.space.licht.envisiondemo.utils.RxUtil;
import com.space.licht.envisiondemo.utils.StringUtils;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: RecommendPresenter
 */
public class RecommendPresenter extends RxPresenter implements RecommendContract.Presenter {
    RecommendContract.View mView;
    int page = 0;

    public RecommendPresenter(@NonNull RecommendContract.View oneView  ) {
        mView = Preconditions.checkNotNull(oneView);
        mView.setPresenter(this);
    }

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        //Retrofit + RXjava 网络请求
        Subscription rxSubscription = RetrofitHelper
                //Retrofit请求数据
                .getVideoApi().getHomePage()
                //compose 整合  线程处理
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                //compose 整合  结果处理
                .compose(RxUtil.<VideoRes>handleResult())
                //返回bean类
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(final VideoRes res) {
                        if (res != null) {
                            if (mView.isActive()) {
                                mView.showContent(res);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

}
