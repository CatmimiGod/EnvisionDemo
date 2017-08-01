package com.space.licht.envisiondemo.di.component;


import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.di.module.AppModule;
import com.space.licht.envisiondemo.di.module.HttpModule;
import com.space.licht.envisiondemo.model.DataManager;
import com.space.licht.envisiondemo.model.db.RealmHelper;
import com.space.licht.envisiondemo.model.http.RetrofitHelper1;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AppComponent
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper1 retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

}
