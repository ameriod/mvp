package me.ameriod.lib.mvp.app;

import android.app.Application;

import timber.log.Timber;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
