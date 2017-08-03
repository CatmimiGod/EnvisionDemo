package com.space.licht.envisiondemo.widget.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class HeavyTextView extends TextView {

    public HeavyTextView(Context context) {
        super(context);

    }

    public HeavyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context);
    }

    public HeavyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        style(context);
    }

    private void style(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/PINGFANG HEAVY_0.TTF");
        setTypeface(tf);
    }

}
