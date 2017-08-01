package com.space.licht.envisiondemo.base;

/**
 * Description: BasePresenter
 */
public interface BasePresenter<T> {
    void attachView(T view);

    void detachView();
}
