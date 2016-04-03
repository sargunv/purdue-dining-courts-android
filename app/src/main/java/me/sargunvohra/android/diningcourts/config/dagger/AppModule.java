package me.sargunvohra.android.diningcourts.config.dagger;

import com.karumi.rosie.repository.datasource.CacheDataSource;
import com.karumi.rosie.repository.datasource.InMemoryCacheDataSource;
import com.karumi.rosie.repository.datasource.ReadableDataSource;
import com.karumi.rosie.time.TimeProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.sargunvohra.android.diningcourts.data.source.DiningMenu;
import me.sargunvohra.android.diningcourts.data.source.DiningMenuDataSource;
import me.sargunvohra.android.diningcourts.main.MainApp;
import me.sargunvohra.lib.ktunits.TimeKt;

@Module(
        library = true,
        complete = false,
        injects = {MainApp.class}
)
public class AppModule {
    @Provides
    @Singleton
    ReadableDataSource<DiningMenu.Key, DiningMenu> provideDiningMenuDataSource() {
        return new DiningMenuDataSource();
    }

    @Provides
    @Singleton
    CacheDataSource<DiningMenu.Key, DiningMenu> provideDiningMenuCacheDataSource() {
        return new InMemoryCacheDataSource<>(new TimeProvider(), TimeKt.getDays(1).getToMilliseconds());
    }
}