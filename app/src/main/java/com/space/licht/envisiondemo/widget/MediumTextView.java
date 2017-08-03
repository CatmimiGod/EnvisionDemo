package com.space.licht.envisiondemo.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


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
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/PINGFANG MEDIUM_0.TTF");
        setTypeface(tf);
    }

}
