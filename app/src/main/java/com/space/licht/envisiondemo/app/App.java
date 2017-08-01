package com.space.licht.envisiondemo.app;


import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.space.licht.envisiondemo.di.component.AppComponent;
import com.space.licht.envisiondemo.di.component.DaggerAppComponent;
import com.space.licht.envisiondemo.di.module.AppModule;
import com.space.licht.envisiondemo.di.module.HttpModule;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;

/**
 * Description: application
 * */

public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;

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