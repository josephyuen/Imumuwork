package com.nzf.mvpframe.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Map;

/**
 * APPLICATION
 */
public class BaseApplication extends Application {

    private static BaseApplication baseApplication;
       public static Map<String,Long> mTime;
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        Fresco.initialize(this);
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
