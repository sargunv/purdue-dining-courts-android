package me.sargunvohra.android.diningcourts.main;

import android.app.Application;

import me.sargunvohra.android.diningcourts.module.ApiModule;
import me.sargunvohra.android.diningcourts.module.AppModule;

public class MainApp extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("http://api.hfs.purdue.edu/menus/v2/"))
                .build();
    }
}
