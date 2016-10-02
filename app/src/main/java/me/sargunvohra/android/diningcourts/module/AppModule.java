package me.sargunvohra.android.diningcourts.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.sargunvohra.android.diningcourts.main.MainApp;

@Module
public class AppModule {

    private MainApp app;

    public AppModule(MainApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    MainApp provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}

