package me.sargunvohra.android.diningcourts.main;

import javax.inject.Singleton;

import dagger.Component;
import me.sargunvohra.android.diningcourts.module.ApiModule;
import me.sargunvohra.android.diningcourts.module.AppModule;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
