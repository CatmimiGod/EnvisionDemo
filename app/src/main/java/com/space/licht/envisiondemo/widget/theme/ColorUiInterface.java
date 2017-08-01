package com.space.licht.envisiondemo.widget.theme;

import android.content.res.Resources;
import android.view.View;

/**
 * 换肤接口
 * ColorUiInterface
 */
public interface ColorUiInterface {


    View getView();

    void setTheme(Resources.Theme themeId);
}
