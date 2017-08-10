package com.space.licht.envisiondemo.app;


import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatDelegate;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.di.component.AppComponent;
import com.space.licht.envisiondemo.di.component.DaggerAppComponent;
import com.space.licht.envisiondemo.di.module.AppModule;
import com.space.licht.envisiondemo.di.module.HttpModule;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.model.db.RealmHelper;
import com.space.licht.envisiondemo.ui.fragment.setting.TimeBean;

import java.util.ArrayList;
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

    public  static List<Collection> sData;
    public  static List<TimeBean> sTimeData = new ArrayList<>();
    public  static List<Collection> sWhitelistData = new ArrayList<>();
    public  static List<Collection> sOneNumberData = new ArrayList<>();

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

        initdata();
        initFonts();
    }

    private void initdata() {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        sData = RealmHelper.getInstance().getCollectionList();
        if (sData.size() == 0) {
            //初始化假数据

            Collection bean6 = new Collection();
            bean6.setNamed("Unused");
            bean6.setId("Unused");
            bean6.setVoice(24);
            bean6.setDataTime(34);
            bean6.setUsedColor(0xff00a2ff);
            RealmHelper.getInstance().insertCollection(bean6);


            Collection bean5 = new Collection();
            bean5.setNamed("ipad");
            bean5.setId("ipad");
            bean5.setHeadImg(R.drawable.ipad);
            bean5.setTel("628-458-5869");
            bean5.setVoice(9);
            bean5.setDataTime(7);

            bean5.setTotalColor(0xfff2f7dd);
            bean5.setUsedColor(0xFFBDD757);
            bean5.setDataUsed(200);
            bean5.setVoiceUsed(320);
            RealmHelper.getInstance().insertCollection(bean5);


            Collection bean4 = new Collection();
            bean4.setNamed("Daughter");
            bean4.setId("Daughter");
            bean4.setHeadImg(R.drawable.daughter);
            bean4.setTel("628-458-2896");
            bean4.setVoice(12);
            bean4.setDataTime(13);
            bean4.setTotalColor(0xfffcf9de);
            bean4.setUsedColor(0xFFF1E15A);
            bean4.setDataUsed(220);
            bean4.setVoiceUsed(350);
            RealmHelper.getInstance().insertCollection(bean4);

            Collection bean3 = new Collection();
            bean3.setNamed("Son");
            bean3.setId("Son");
            bean3.setHeadImg(R.drawable.son);
            bean3.setTel("628-458-4826");
            bean3.setVoice(10);
            bean3.setDataTime(10);
            bean3.setTotalColor(0xfffff6de);
            bean3.setUsedColor(0xFFFBD05A);
            bean3.setDataUsed(200);
            bean3.setVoiceUsed(310);
            RealmHelper.getInstance().insertCollection(bean3);

            Collection bean = new Collection();
            bean.setNamed("Mother");
            bean.setId("Mother");
            bean.setHeadImg(R.drawable.mother);
            bean.setTel("628-458-5869");
            bean.setVoice(16);
            bean.setDataTime(16);

            bean.setTotalColor(0xfffeefdf);
            bean.setUsedColor(0xfffcad5d);
            bean.setDataUsed(380);
            bean.setVoiceUsed(420);
            RealmHelper.getInstance().insertCollection(bean);


            Collection bean2 = new Collection();
            bean2.setNamed("Father");
            bean2.setId("Father");
            bean2.setHeadImg(R.drawable.father);
            bean2.setTel("628-458-5876");
            bean2.setVoice(20);
            bean2.setDataTime(20);
            bean2.setTotalColor(0xfffee6de);
            bean2.setUsedColor(0xFFFA8358);
            bean2.setDataUsed(400);
            bean2.setVoiceUsed(700);
            RealmHelper.getInstance().insertCollection(bean2);
            sData = RealmHelper.getInstance().getCollectionList();
        }
        if (sTimeData.size()==0){
            TimeBean bean1 = new TimeBean();
            bean1.setsStartDay(week[0]);
            bean1.setsStartHour("11");
            bean1.setsStartMins("00");
            bean1.setsStartAMorPM("PM");
            bean1.setsStopDay(week[4]);
            bean1.setsStopHour("06");
            bean1.setsStopMins("00");
            bean1.setsStopAMorPM("AM");
            sTimeData.add(bean1);
         TimeBean bean2 = new TimeBean();
            bean2.setsStartDay(week[0]);
            bean2.setsStartHour("12");
            bean2.setsStartMins("00");
            bean2.setsStartAMorPM("PM");
            bean2.setsStopDay(week[0]);
            bean2.setsStopHour("08");
            bean2.setsStopMins("00");
            bean2.setsStopAMorPM("AM");
            sTimeData.add(bean2);
         TimeBean bean3 = new TimeBean();
            bean3.setsStartDay(week[0]);
            bean3.setsStartHour("01");
            bean3.setsStartMins("00");
            bean3.setsStartAMorPM("AM");
            bean3.setsStopDay(week[4]);
            bean3.setsStopHour("10");
            bean3.setsStopMins("00");
            bean3.setsStopAMorPM("AM");
            sTimeData.add(bean3);
         TimeBean bean4 = new TimeBean();
            bean4.setsStartDay(week[1]);
            bean4.setsStartHour("11");
            bean4.setsStartMins("00");
            bean4.setsStartAMorPM("PM");
            bean4.setsStopDay(week[3]);
            bean4.setsStopHour("07");
            bean4.setsStopMins("00");
            bean4.setsStopAMorPM("AM");
            sTimeData.add(bean4);
         TimeBean bean5 = new TimeBean();
            bean5.setsStartDay(week[0]);
            bean5.setsStartHour("10");
            bean5.setsStartMins("00");
            bean5.setsStartAMorPM("PM");
            bean5.setsStopDay(week[4]);
            bean5.setsStopHour("09");
            bean5.setsStopMins("00");
            bean5.setsStopAMorPM("AM");
            sTimeData.add(bean5);
         TimeBean bean6 = new TimeBean();
            bean6.setsStartDay(week[0]);
            bean6.setsStartHour("02");
            bean6.setsStartMins("00");
            bean6.setsStartAMorPM("AM");
            bean6.setsStopDay(week[4]);
            bean6.setsStopHour("06");
            bean6.setsStopMins("00");
            bean6.setsStopAMorPM("AM");
            sTimeData.add(bean6);
        }

        if (sWhitelistData.size() == 0){
            Collection bean6 = new Collection();
            bean6.setNamed("Unused");
            bean6.setId("Unused");
            bean6.setVoice(24);
            bean6.setDataTime(34);
            bean6.setUsedColor(0xff00a2ff);
            sWhitelistData.add(bean6);

            Collection bean5 = new Collection();
            bean5.setNamed("ipad");
            bean5.setId("ipad");
            bean5.setHeadImg(R.drawable.ipad);
            bean5.setTel("628-458-5869");
            bean5.setVoice(9);
            bean5.setDataTime(7);
            bean5.setTotalColor(0xfff2f7dd);
            bean5.setUsedColor(0xFFBDD757);
            bean5.setDataUsed(200);
            bean5.setVoiceUsed(320);
            sWhitelistData.add(bean5);

            Collection bean4 = new Collection();
            bean4.setNamed("Daughter");
            bean4.setId("Daughter");
            bean4.setHeadImg(R.drawable.daughter);
            bean4.setTel("628-458-2896");
            bean4.setVoice(12);
            bean4.setDataTime(13);
            bean4.setTotalColor(0xfffcf9de);
            bean4.setUsedColor(0xFFF1E15A);
            bean4.setDataUsed(220);
            bean4.setVoiceUsed(350);
            sWhitelistData.add(bean4);

            Collection bean3 = new Collection();
            bean3.setNamed("Son");
            bean3.setId("Son");
            bean3.setHeadImg(R.drawable.son);
            bean3.setTel("628-458-4826");
            bean3.setVoice(10);
            bean3.setDataTime(10);
            bean3.setTotalColor(0xfffff6de);
            bean3.setUsedColor(0xFFFBD05A);
            bean3.setDataUsed(200);
            bean3.setVoiceUsed(310);
            sWhitelistData.add(bean3);

            Collection bean = new Collection();
            bean.setNamed("Mother");
            bean.setId("Mother");
            bean.setHeadImg(R.drawable.mother);
            bean.setTel("628-458-5869");
            bean.setVoice(16);
            bean.setDataTime(16);

            bean.setTotalColor(0xfffeefdf);
            bean.setUsedColor(0xfffcad5d);
            bean.setDataUsed(380);
            bean.setVoiceUsed(420);
            sWhitelistData.add(bean);


            Collection bean2 = new Collection();
            bean2.setNamed("Father");
            bean2.setId("Father");
            bean2.setHeadImg(R.drawable.father);
            bean2.setTel("628-458-5876");
            bean2.setVoice(20);
            bean2.setDataTime(20);
            bean2.setTotalColor(0xfffee6de);
            bean2.setUsedColor(0xFFFA8358);
            bean2.setDataUsed(400);
            bean2.setVoiceUsed(700);
            sWhitelistData.add(bean2);
        }

        if (sOneNumberData.size() == 0){
            Collection bean6 = new Collection();
            bean6.setNamed("My Phone");
            bean6.setTel("068-458-3269");
            bean6.setHeadImg(R.mipmap.one_number_content_my_phone);
            sOneNumberData.add(bean6);

            Collection bean5 = new Collection();
            bean5.setNamed("Office");
            bean5.setHeadImg(R.mipmap.one_number_content_office);
            bean5.setTel("068-434-8945");
            sOneNumberData.add(bean5);

            Collection bean4 = new Collection();
            bean4.setNamed("Home");
            bean4.setHeadImg(R.mipmap.one_number_content_home);
            bean4.setTel("068-562-8651");
            sOneNumberData.add(bean4);
        }


    }

    private void initFonts() {
        mRegularTf = Typeface.createFromAsset(getAssets(), "fonts/PINGFANG REGULAR_0.TTF");
        mMediumTf = Typeface.createFromAsset(getAssets(), "fonts/PINGFANG MEDIUM_0.TTF");
        mBoldTf = Typeface.createFromAsset(getAssets(), "fonts/pingfang_bold_0.ttf");
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