package com.space.licht.envisiondemo.widget.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.space.licht.envisiondemo.app.App;


public class MediumTextView extends TextView {

    public MediumTextView(Context context) {
        super(context);

    }

    public MediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context);
    }

    public MediumTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        style(context);
    }

    private void style(Context context) {

        setTypeface(App.mMediumTf);
    }

}
