package me.sargunvohra.android.diningcourts.dagger;

import com.karumi.rosie.repository.datasource.CacheDataSource;
import com.karumi.rosie.repository.datasource.InMemoryCacheDataSource;
import com.karumi.rosie.repository.datasource.ReadableDataSource;
import com.karumi.rosie.time.TimeProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.sargunvohra.android.diningcourts.data.item.MenuItem;
import me.sargunvohra.android.diningcourts.data.item.MenuItemDataSource;
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu;
import me.sargunvohra.android.diningcourts.data.menu.DiningMenuDataSource;
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

    @Provides
    @Singleton
    ReadableDataSource<String, MenuItem> provideMenuItemDataSource() {
        return new MenuItemDataSource();
    }

    @Provides
    @Singleton
    CacheDataSource<String, MenuItem> provideMenuItemCacheDataSource() {
        return new InMemoryCacheDataSource<>(new TimeProvider(), TimeKt.getDays(1).getToMilliseconds());
    }
}