package me.sargunvohra.android.purduediningcourts;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import me.sargunvohra.android.purduediningcourts.page.location.dining.DiningItemActivity;
import me.sargunvohra.android.purduediningcourts.page.location.dining.DiningItemPresenter;
import me.sargunvohra.android.purduediningcourts.page.location.dining.DiningLocationListPresenter;
import me.sargunvohra.android.purduediningcourts.page.location.dining.DiningMenuActivity;
import me.sargunvohra.android.purduediningcourts.page.location.dining.DiningMenuPresenter;
import me.sargunvohra.android.purduediningcourts.page.location.retail.RetailInfoActivity;
import me.sargunvohra.android.purduediningcourts.page.location.retail.RetailLocationListPresenter;
import me.sargunvohra.android.purduediningcourts.page.today.TodayPresenter;
import me.sargunvohra.android.purduediningcourts.service.DiningService;
import me.sargunvohra.android.purduediningcourts.service.DiningServiceHelper;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@Module(
        injects = {
                MainActivity.class,
                DiningMenuActivity.class,
                DiningItemActivity.class,
                RetailInfoActivity.class,
                DiningLocationListPresenter.class,
                RetailLocationListPresenter.class,
                DiningMenuPresenter.class,
                DiningItemPresenter.class,
                TodayPresenter.class,
        }
)
public class DaggerModule {

    private static ObjectGraph graph;

    public static ObjectGraph getObjectGraph() {
        if (graph == null) {
            graph = ObjectGraph.create(new DaggerModule());
        }
        return graph;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    @Provides
    @Singleton
    Converter provideConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Converter converter) {
        return new RestAdapter.Builder()
                .setEndpoint(DiningServiceHelper.getEndpoint())
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setConverter(converter)
                .build();
    }

    @Provides
    @Singleton
    DiningService provideDiningService(RestAdapter adapter) {
        return adapter.create(DiningService.class);
    }

    @Provides
    Handler provideHandler() {
        return new Handler();
    }
}