package com.space.licht.envisiondemo.utils;

import android.content.Context;
import android.content.Intent;

import com.space.licht.envisiondemo.ui.activitys.AddMemberActivity;
import com.space.licht.envisiondemo.ui.activitys.MainActivity;
import com.space.licht.envisiondemo.ui.activitys.WelcomeActivity;


/**
 * Description: JumpUtil
 * 跳转的统一管理类
 */
public class JumpUtil {


    public static void go2MainActivity(Context context) {
        jump(context, MainActivity.class);
        ((WelcomeActivity) context).finish();
    }

    public static void go2AddMemberActivity(Context context) {
        jump(context, AddMemberActivity.class);
    }

    public static void jump(Context a, Class<?> clazz) {
        Intent intent = new Intent(a, clazz);
        a.startActivity(intent);
    }
}
