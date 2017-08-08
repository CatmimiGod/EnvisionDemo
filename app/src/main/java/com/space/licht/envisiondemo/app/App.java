package com.space.licht.envisiondemo.app;


import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatDelegate;

import com.space.licht.envisiondemo.di.component.AppComponent;
import com.space.licht.envisiondemo.di.component.DaggerAppComponent;
import com.space.licht.envisiondemo.di.module.AppModule;
import com.space.licht.envisiondemo.di.module.HttpModule;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.model.db.RealmHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;

/**
 * Description: application
 */

public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;
    public static Typeface mRegularTf;
    public static Typeface mBoldTf;
    public static Typeface mMediumTf;
//    public static Typeface mAKRegularTf;

    public  static List<Collection> sData;

    public static App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(getApplicationContext());
        sData = RealmHelper.getInstance().getCollectionList();
        initFonts();
    }

    private void initFonts() {
        mRegularTf = Typeface.createFromAsset(getAssets(), "fonts/PINGFANG REGULAR_0.TTF");
        mMediumTf = Typeface.createFromAsset(getAssets(), "fonts/PINGFANG MEDIUM_0.TTF");
        mBoldTf = Typeface.createFromAsset(getAssets(), "fonts/pingfang_bold_0.ttf");
//        mAKRegularTf = Typeface.createFromAsset(getAssets(), "AkkuratPro-Regular.otf");
    }

    public void registerActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void unregisterActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    if (act != null && !act.isFinishing())
                        act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}