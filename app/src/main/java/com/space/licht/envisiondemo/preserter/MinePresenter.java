package com.space.licht.envisiondemo.preserter;

import android.support.annotation.NonNull;

import com.space.licht.envisiondemo.preserter.contract.MineContract;

/**
 * Description: CollectionPresenter
 */
public class MinePresenter extends RxPresenter implements MineContract.Presenter {
    public static final int maxSize = 30;

    public MinePresenter(@NonNull MineContract.View oneView) {
    }

    @Override
    public void getHistoryData() {
    }

    @Override
    public void delAllHistory() {
    }
}
