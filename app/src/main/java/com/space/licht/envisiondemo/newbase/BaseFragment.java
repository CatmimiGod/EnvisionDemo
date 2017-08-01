package com.space.licht.envisiondemo.newbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.base.BasePresenter;
import com.space.licht.envisiondemo.di.component.DaggerFragmentComponent;
import com.space.licht.envisiondemo.di.component.FragmentComponent;
import com.space.licht.envisiondemo.di.module.FragmentModule;

import javax.inject.Inject;


/**
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}