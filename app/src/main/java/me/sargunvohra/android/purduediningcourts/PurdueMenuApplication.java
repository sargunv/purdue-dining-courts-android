package me.sargunvohra.android.purduediningcourts;

import android.app.Application;

import timber.log.Timber;

public class PurdueMenuApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // setup logging
        Timber.uprootAll();
        Timber.plant(new Timber.DebugTree());
    }
}