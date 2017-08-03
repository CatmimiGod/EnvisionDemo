package com.space.licht.envisiondemo.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class BoldTextView extends TextView {

    public BoldTextView(Context context) {
        super(context);

    }

    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context);
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        style(context);
    }

    private void style(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/pingfang_bold_0.ttf");
        setTypeface(tf);
    }

}
