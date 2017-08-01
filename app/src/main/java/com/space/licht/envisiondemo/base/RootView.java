package com.space.licht.envisiondemo.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 继承自Linearlayout的基本类
 */
public abstract class RootView<T extends BasePresenter> extends LinearLayout {
    protected boolean mActive;//是否被销毁 用于查看view的状态
    protected Context mContext;
    protected Unbinder unbinder;
    protected T mPresenter;

    public RootView(Context context) {
        super(context);
        init();
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected abstract void getLayout();

    protected abstract void initView();

    protected abstract void initEvent();

    protected void init() {
        mContext = getContext();
        getLayout();
        unbinder = ButterKnife.bind(this);
        mActive = true;
        initView();
        initEvent();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPresenter != null)
            mPresenter.attachView(this);
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mPresenter != null)
            mPresenter.detachView();
        mActive = false;
        unbinder.unbind();
        mContext = null;
    }
}
