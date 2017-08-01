
package com.space.licht.envisiondemo.preserter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.space.licht.envisiondemo.preserter.contract.WelcomeContract;
import com.space.licht.envisiondemo.utils.Preconditions;
import com.space.licht.envisiondemo.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: WelcomePresenter
 * Creator: yxc
 * date: 2016/9/22 13:17
 */
public class WelcomePresenter extends RxPresenter implements WelcomeContract.Presenter {
    private static final String TAG = "WelcomePresenter";
    WelcomeContract.View mView;
    private static final int COUNT_DOWN_TIME = 4200;

    public WelcomePresenter(@NonNull WelcomeContract.View oneView) {
        mView = Preconditions.checkNotNull(oneView);
        mView.setPresenter(this);
        getWelcomeData();
    }

    @Override
    public void getWelcomeData() {
        //将数据提交给view
        mView.showContent(getImgData());
        //开始计数  跳转至mainActivity
        startCountDown();
    }

    private void startCountDown() {
        Subscription rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, "时间到  跳转!!! ");
                        mView.jumpToMain();
                    }
                });
        addSubscribe(rxSubscription);
    }

    private List<String> getImgData() {
        List<String> imgs = new ArrayList<>();
        imgs.add("file:///android_asset/a.jpg");
        imgs.add("file:///android_asset/b.jpg");
        imgs.add("file:///android_asset/c.jpg");
        imgs.add("file:///android_asset/d.jpg");
        imgs.add("file:///android_asset/e.jpg");
        imgs.add("file:///android_asset/f.jpg");
        imgs.add("file:///android_asset/g.jpg");
        return imgs;
    }

}
