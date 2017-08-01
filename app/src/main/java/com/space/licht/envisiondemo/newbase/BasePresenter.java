package com.space.licht.envisiondemo.newbase;

/**
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
