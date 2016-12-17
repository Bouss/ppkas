package com.tp1.bousseaud.ppkas.app;

import com.activeandroid.ActiveAndroid;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
