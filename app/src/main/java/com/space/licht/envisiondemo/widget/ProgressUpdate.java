package com.space.licht.envisiondemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by licht
 * 2017/8/8 0008.
 */

public class ProgressUpdate extends ProgressBar {
    private ProgresChangeListener listener;

    public ProgressUpdate(Context context) {
        super(context);
    }

    public ProgressUpdate(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        listener.ProgressChange();
    }

    public void setListener(ProgresChangeListener listener) {
        this.listener = listener;
    }

    public interface ProgresChangeListener {
        void ProgressChange();
    }

}
