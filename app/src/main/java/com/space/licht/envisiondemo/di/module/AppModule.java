package com.space.licht.envisiondemo.di.module;


import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.model.DataManager;
import com.space.licht.envisiondemo.model.db.DBHelper;
import com.space.licht.envisiondemo.model.db.RealmHelper;
import com.space.licht.envisiondemo.model.http.HttpHelper;
import com.space.licht.envisiondemo.model.http.RetrofitHelper1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * desciption: 应用核心类管理
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper1 retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper) {
        return new DataManager(httpHelper, DBHelper);
    }
}
