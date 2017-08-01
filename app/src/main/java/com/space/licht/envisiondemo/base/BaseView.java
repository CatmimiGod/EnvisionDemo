package com.space.licht.envisiondemo.base;

/**
 * BaseView
 */
public interface BaseView<T> {
    void setPresenter(T presenter);

    void showError(String msg);
}
