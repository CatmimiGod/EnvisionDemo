package com.space.licht.envisiondemo.widget.circleprogress;

import android.animation.TimeInterpolator;

/**
 * EaseInOutCubicInterpolator
 */
public class EaseInOutCubicInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        if ((input *= 2) < 1.0f) {
            return 0.5f * input * input * input;
        }
        input -= 2;
        return 0.5f * input * input * input + 1;
    }

}
